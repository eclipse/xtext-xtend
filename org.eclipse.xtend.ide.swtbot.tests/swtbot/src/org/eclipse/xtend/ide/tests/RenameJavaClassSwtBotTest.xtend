/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.ide.tests

import java.util.Collection
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized.Parameters

import static org.junit.Assert.*

import static extension org.eclipse.xtend.ide.tests.SwtBotProjectHelper.*

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
@RunWith(ParameterizedSWTBotRunner)
@Ignore
class RenameJavaClassSwtBotTest extends AbstractRefactoringSwtBotTest {

	@Parameters(name="{0}")
	def static Collection<Object[]> data() {
		#[ 
			#[ new RefactoringTestParameters => [
				useInlineRefactoring = true
		  		usePreview = false
			]]
			// Due to JDT bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=345342
			// we cannot detect when a JDT refactoring has finished
//		  	#[ new RefactoringTestParameters => [
//				useInlineRefactoring = true
//		  		usePreview = true
//			]],
//			#[ new RefactoringTestParameters => [
//				useInlineRefactoring = false
//		  		usePreview = false
//		  	]],
//			#[ new RefactoringTestParameters => [
//				useInlineRefactoring = false
//		  		usePreview = true
//		  	]]
	  	]
	}

	new(RefactoringTestParameters testParams) {
		super(testParams)
	}

	@Test 
	def void renameJavaClass() {
		val xtendEditor = bot.newXtendEditor('XtendClass', 'test', '''
			package test
			class XtendClass extends JavaClass {
			}
		''')
		val javaEditor = bot.newJavaEditor('JavaClass', 'test', '''
			package test;
			public class ?JavaClass? {}
		''')
		javaEditor.renameInJavaEditor('NewJavaClass', 'Rename Type')
		assertEquals('''
			package test
			class XtendClass extends NewJavaClass {
			}
		''', xtendEditor.text)
		assertFalse(xtendEditor.dirty)
		assertEquals('''
			package test;
			public class NewJavaClass {}
		''', javaEditor.text)
		assertFalse(javaEditor.dirty)
		assertEquals('NewJavaClass.java', javaEditor.title)
	}
	
	@Test 
	def void renameJavaClassXtendReference() {
		val javaEditor = bot.newJavaEditor('JavaClass', 'test', '''
			package test;
			public class JavaClass {}
		''')
		val xtendEditor = bot.newXtendEditor('XtendClass', 'test', '''
			package test
			class XtendClass extends ?JavaClass? {
			}
		''')
		xtendEditor.renameInXtendEditor('NewJavaClass', 'Rename Type')
		assertEquals('''
			package test
			class XtendClass extends NewJavaClass {
			}
		''', xtendEditor.text)
		assertFalse(xtendEditor.dirty)
		assertEquals('''
			package test;
			public class NewJavaClass {}
		''', javaEditor.text)
		assertFalse(javaEditor.dirty)
		assertEquals('NewJavaClass.java', javaEditor.title)
	}
	
}