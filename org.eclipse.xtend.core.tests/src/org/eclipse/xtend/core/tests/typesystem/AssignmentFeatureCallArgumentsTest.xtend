/*******************************************************************************
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.typesystem

import com.google.inject.Inject
import org.eclipse.xtend.core.jvmmodel.IXtendJvmAssociations
import org.eclipse.xtext.xbase.XBlockExpression
import org.eclipse.xtext.xbase.XNullLiteral
import org.eclipse.xtext.xbase.XStringLiteral
import org.eclipse.xtext.xbase.typesystem.arguments.AssignmentFeatureCallArguments
import org.junit.Test

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
class AssignmentFeatureCallArgumentsTest extends AbstractTestingTypeReferenceOwner {
	
	@Inject extension IXtendJvmAssociations 
	
	@Test
	def void test_01() {
		val arguments = ''.toArguments('null')
		assertTrue(arguments.hasUnprocessedArguments)
		val singleSlot = arguments.nextUnprocessedArgumentSlot
		assertNull(singleSlot.declaredType)
		assertFalse(singleSlot.varArg)
		assertFalse(singleSlot.superfluous)
		assertTrue(singleSlot.argumentExpression instanceof XNullLiteral)
		singleSlot.markProcessed
		assertFalse(arguments.hasUnprocessedArguments)
	}
	
	@Test
	def void test_02() {
		val arguments = 'String s'.toArguments('""')
		assertTrue(arguments.hasUnprocessedArguments)
		assertEquals(1, arguments.argumentCount)
		val singleSlot = arguments.nextUnprocessedArgumentSlot
		assertEquals("String", singleSlot.declaredType.simpleName)
		assertFalse(singleSlot.varArg)
		assertFalse(singleSlot.superfluous)
		assertTrue(singleSlot.argumentExpression instanceof XStringLiteral)
		singleSlot.markProcessed
		assertFalse(arguments.hasUnprocessedArguments)
	}
	
	protected def toArguments(String type, String expression) {
		val String functionString = '''
			def void m(�type�) {
				�expression�
			}
		'''
		val function = function(functionString)
		val body = function.expression as XBlockExpression
		val value = body.expressions.head
		val declaredType = function.directlyInferredOperation.parameters.head?.parameterType
		if (declaredType !== null) {
			return new AssignmentFeatureCallArguments(value, toLightweightTypeReference(declaredType))		
		} else {
			return new AssignmentFeatureCallArguments(value, null)
		}
	}
	
}