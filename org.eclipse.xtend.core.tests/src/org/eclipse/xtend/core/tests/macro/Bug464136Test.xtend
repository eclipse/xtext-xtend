/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.macro

import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import org.junit.Test

class Bug464136Test extends AbstractActiveAnnotationTest {
	
	@Test def void testThrownErrorInPreValidation() {
		'''
			@org.eclipse.xtend.core.tests.macro.Bug464136 class C {}
		'''.compile [
			val problems = allProblems
			assertEquals(problems.map[message].toString, 1, problems.size)
			val messageJava = '''
				Error during annotation processing:
				java.lang.LinkageError: Just a test :-/
					at org.eclipse.xtend.core.tests.macro.Bug464136Processor.lambda$doTransform$0(Bug464136Processor.java:21)
			'''.toString
			
			val messageEclipse = '''
				Error during annotation processing:
				java.lang.LinkageError: Just a test :-/
					at org.eclipse.xtend.core.tests.macro.Bug464136Processor.lambda$0(Bug464136Processor.java:21)
			'''.toString
			if (messageJava != problems.head.message && messageEclipse != problems.head.message) {
				fail('''
				Expected one of the following problem messages:
				
				�messageJava�
				
				�messageEclipse�
				
				But got:
				
				�problems.head.message�
				''')
			}
		]
	}
	
}


@Active(typeof(Bug464136Processor))
annotation Bug464136 {}
class Bug464136Processor extends AbstractClassProcessor {
	
	override doTransform(MutableClassDeclaration annotatedClass, extension TransformationContext context) {
		validateLater [
			throw new LinkageError("Just a test :-/")
		]
	}
		
}