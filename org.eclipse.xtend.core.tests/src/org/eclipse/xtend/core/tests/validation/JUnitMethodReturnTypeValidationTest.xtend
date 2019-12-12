/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.validation

import javax.inject.Inject
import org.eclipse.emf.ecore.EClass
import org.eclipse.xtend.core.tests.AbstractXtendTestCase
import org.eclipse.xtend.core.xtend.XtendFile
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.FixMethodOrder
import org.junit.Test

import static org.eclipse.xtend.core.validation.IssueCodes.INVALID_RETURN_TYPE_IN_CASE_OF_JUNIT_ANNOTATION
import static org.eclipse.xtend.core.xtend.XtendPackage.Literals.*
import static org.eclipse.xtext.diagnostics.Diagnostic.LINKING_DIAGNOSTIC
import static org.eclipse.xtext.xbase.XbasePackage.Literals.*

@FixMethodOrder(JVM)
class JUnitMethodReturnTypeValidationTest extends AbstractXtendTestCase {

	@Inject extension ValidationTestHelper

	@Test def test001() {
		'''
			class Foo {
				
				def test() {
					1
				}
			}
		'''.hasNoValidationIssue
	}

	@Test def test002() {
		'''
			class Foo {
				
				def int test() {
					1
				}
			}
		'''.hasNoValidationIssue
	}

	@Test def test003() {
		'''
			import org.junit.Test
			
			class Foo {
				
				@Test def void test() {}
			}
		'''.hasNoValidationIssue
	}

	@Test def test004() {
		'''
			import org.junit.Test
			
			class Foo {
				
				@Test def test() {
					return
				}
			}
		'''.hasNoValidationIssue
	}
	
	@Test def test005() {
		'''
			import org.junit.Before
			
			class Foo {
				
				@Before def void before() {}
			}
		'''.hasNoValidationIssue
	}

	@Test def test006() {
		'''
			import org.junit.Before
			
			class Foo {
				
				@Before def before() {
					return
				}
			}
		'''.hasNoValidationIssue
	}
	
	@Test def test007() {
		'''
			import org.junit.After
			
			class Foo {
				
				@After def void after() {}
			}
		'''.hasNoValidationIssue
	}

	@Test def test008() {
		'''
			import org.junit.After
			
			class Foo {
				
				@After def after() {
					return
				}
			}
		'''.hasNoValidationIssue
	}
	
	@Test def test009() {
		'''
			import org.junit.BeforeClass
			
			class Foo {
				
				@BeforeClass def static void beforeClass() {}
			}
		'''.hasNoValidationIssue
	}

	@Test def test010() {
		'''
			import org.junit.BeforeClass
			
			class Foo {
				
				@BeforeClass def static beforeClass() {
					return
				}
			}
		'''.hasNoValidationIssue
	}
	
	@Test def test011() {
		'''
			import org.junit.AfterClass
			
			class Foo {
				
				@AfterClass def static void afterClass() {}
			}
		'''.hasNoValidationIssue
	}

	@Test def test012() {
		'''
			import org.junit.AfterClass
			
			class Foo {
				
				@AfterClass def static afterClass() {
					return
				}
			}
		'''.hasNoValidationIssue
	}

	@Test def test013() {
		'''
			import org.junit.Test
			
			class Foo {
				
				@Test def int test() {
					1
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is int.")
	}

	@Test def test014() {
		'''
			import org.junit.Test
			
			class Foo {
				
				@Test def m() {
					1
				}
			}
		'''.hasOneValidationIssue("JUnit method m() must be void but is int.")
	}

	@Test def test015() {
		'''
			import org.junit.Test
			
			class Foo {
				
				@Test def Object test() {
					"foo"
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test016() {
		'''
			import org.junit.Test
			
			class Foo {
				
				@Test def test() {
					
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}
	
	@Test def test017() {
		'''
			import org.junit.Before
			
			class Foo {
				
				@Before def int before() {
					1
				}
			}
		'''.hasOneValidationIssue("JUnit method before() must be void but is int.")
	}

	@Test def test018() {
		'''
			import org.junit.Before
			
			class Foo {
				
				@Before def before() {
					1
				}
			}
		'''.hasOneValidationIssue("JUnit method before() must be void but is int.")
	}

	@Test def test019() {
		'''
			import org.junit.Before
			
			class Foo {
				
				@Before def Object before() {
					"foo"
				}
			}
		'''.hasOneValidationIssue("JUnit method before() must be void but is Object.")
	}

	@Test def test020() {
		'''
			import org.junit.Before
			
			class Foo {
				
				@Before def before() {
					
				}
			}
		'''.hasOneValidationIssue("JUnit method before() must be void but is Object.")
	}

	@Test def test021() {
		'''
			import org.junit.After
			
			class Foo {
				
				@After def int after() {
					1
				}
			}
		'''.hasOneValidationIssue("JUnit method after() must be void but is int.")
	}

	@Test def test022() {
		'''
			import org.junit.After
			
			class Foo {
				
				@After def after() {
					1
				}
			}
		'''.hasOneValidationIssue("JUnit method after() must be void but is int.")
	}

	@Test def test023() {
		'''
			import org.junit.After
			
			class Foo {
				
				@After def Object after() {
					"foo"
				}
			}
		'''.hasOneValidationIssue("JUnit method after() must be void but is Object.")
	}

	@Test def test024() {
		'''
			import org.junit.After
			
			class Foo {
				
				@After def after() {
					
				}
			}
		'''.hasOneValidationIssue("JUnit method after() must be void but is Object.")
	}

	@Test def test025() {
		'''
			import org.junit.BeforeClass
			
			class Foo {
				
				@BeforeClass def static int beforeClass() {
					1
				}
			}
		'''.hasOneValidationIssue("JUnit method beforeClass() must be void but is int.")
	}

	@Test def test026() {
		'''
			import org.junit.BeforeClass
			
			class Foo {
				
				@BeforeClass def static beforeClass() {
					1
				}
			}
		'''.hasOneValidationIssue("JUnit method beforeClass() must be void but is int.")
	}

	@Test def test027() {
		'''
			import org.junit.BeforeClass
			
			class Foo {
				
				@BeforeClass def static Object beforeClass() {
					"foo"
				}
			}
		'''.hasOneValidationIssue("JUnit method beforeClass() must be void but is Object.")
	}

	@Test def test028() {
		'''
			import org.junit.BeforeClass
			
			class Foo {
				
				@BeforeClass def static beforeClass() {
					
				}
			}
		'''.hasOneValidationIssue("JUnit method beforeClass() must be void but is Object.")
	}

	@Test def test029() {
		'''
			import org.junit.AfterClass
			
			class Foo {
				
				@AfterClass def static int afterClass() {
					1
				}
			}
		'''.hasOneValidationIssue("JUnit method afterClass() must be void but is int.")
	}

	@Test def test030() {
		'''
			import org.junit.AfterClass
			
			class Foo {
				
				@AfterClass def static afterClass() {
					1
				}
			}
		'''.hasOneValidationIssue("JUnit method afterClass() must be void but is int.")
	}

	@Test def test031() {
		'''
			import org.junit.AfterClass
			
			class Foo {
				
				@AfterClass def static Object afterClass() {
					"foo"
				}
			}
		'''.hasOneValidationIssue("JUnit method afterClass() must be void but is Object.")
	}

	@Test def test032() {
		'''
			import org.junit.AfterClass
			
			class Foo {
				
				@AfterClass def static afterClass() {
					
				}
			}
		'''.hasOneValidationIssue("JUnit method afterClass() must be void but is Object.")
	}
	
	@Test def test033() {
		/**
		 * Ensure that the 'JUnit Method Return Type Validation Check'
		 * does not report a follow up issue of an unknown return type.
		 */
		'''
			import org.junit.Test
			
			class Foo {
				
				@Test def test() {
					foo
				}
			}
		'''.hasOneValidationIssue(XFEATURE_CALL, LINKING_DIAGNOSTIC, "The method or field foo is undefined")
	}

	@Test def test040_RunWith_Test() {
		'''
			import org.junit.runner.RunWith
			import org.junit.Test
			
			@RunWith(org.junit.runners.Parameterized)
			class Foo {
				
				@Test def test() {
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test041_RunWith_Test() {
		'''
			import org.junit.runner.RunWith
			import org.junit.runners.Parameterized
			import org.junit.Test
			
			@RunWith(Parameterized)
			class Foo {
				
				@Test def test() {
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test042_RunWith_Test() {
		'''
			import org.junit.runner.RunWith
			import org.junit.Test
			
			@RunWith(org.eclipse.xtext.testing.XtextRunner)
			class Foo {
				
				@Test def test() {
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test043_RunWith_Test() {
		'''
			import org.eclipse.xtext.testing.XtextRunner
			import org.junit.runner.RunWith
			import org.junit.Test
			
			@RunWith(XtextRunner)
			class Foo {
				
				@Test def test() {
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test044_RunWith_Test() {
		'''
			import org.junit.runner.RunWith
			import org.junit.Test
			
			@RunWith(com.foo.bar.MyRunner)
			class Foo {
				
				@Test def test() {
				}
			}
		'''.withRunner('''
			package com.foo.bar

			class MyRunner extends org.eclipse.xtext.testing.XtextRunner {
				new(Class<?> klass) throws org.junit.runners.model.InitializationError {
					super(klass);
				}
			}
		''').hasNoValidationIssue()
	}

	@Test def test045_RunWith_Test() {
		'''
			import com.foo.bar.MyRunner
			import org.junit.runner.RunWith
			import org.junit.Test
			
			@RunWith(MyRunner)
			class Foo {
				
				@Test def test() {
				}
			}
		'''.withRunner('''
			package com.foo.bar

			class MyRunner extends org.eclipse.xtext.testing.XtextRunner {
				new(Class<?> klass) throws org.junit.runners.model.InitializationError {
					super(klass);
				}
			}
		''').hasNoValidationIssue()
	}

	@Test def test046_RunWith_Before() {
		'''
			import org.junit.runner.RunWith
			import org.junit.Before
			
			@RunWith(org.junit.runners.Parameterized)
			class Foo {
				
				@Before def test() {
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test047_RunWith_Before() {
		'''
			import org.junit.runner.RunWith
			import org.junit.runners.Parameterized
			import org.junit.Before
			
			@RunWith(Parameterized)
			class Foo {
				
				@Before def test() {
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test048_RunWith_Before() {
		'''
			import org.junit.runner.RunWith
			import org.junit.Before
			
			@RunWith(org.eclipse.xtext.testing.XtextRunner)
			class Foo {
				
				@Before def test() {
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test049_RunWith_Before() {
		'''
			import org.eclipse.xtext.testing.XtextRunner
			import org.junit.runner.RunWith
			import org.junit.Before
			
			@RunWith(XtextRunner)
			class Foo {
				
				@Before def test() {
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test050_RunWith_Before() {
		'''
			import org.junit.runner.RunWith
			import org.junit.Before
			
			@RunWith(com.foo.bar.MyRunner)
			class Foo {
				
				@Before def test() {
				}
			}
		'''.withRunner('''
			package com.foo.bar

			class MyRunner extends org.eclipse.xtext.testing.XtextRunner {
				new(Class<?> klass) throws org.junit.runners.model.InitializationError {
					super(klass);
				}
			}
		''').hasNoValidationIssue()
	}

	@Test def test051_RunWith_Before() {
		'''
			import com.foo.bar.MyRunner
			import org.junit.runner.RunWith
			import org.junit.Before
			
			@RunWith(MyRunner)
			class Foo {
				
				@Before def test() {
				}
			}
		'''.withRunner('''
			package com.foo.bar

			class MyRunner extends org.eclipse.xtext.testing.XtextRunner {
				new(Class<?> klass) throws org.junit.runners.model.InitializationError {
					super(klass);
				}
			}
		''').hasNoValidationIssue()
	}

	@Test def test052_RunWith_After() {
		'''
			import org.junit.runner.RunWith
			import org.junit.After
			
			@RunWith(org.junit.runners.Parameterized)
			class Foo {
				
				@After def test() {
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}
	
	@Test def test053_RunWith_After() {
		'''
			import org.junit.runner.RunWith
			import org.junit.runners.Parameterized
			import org.junit.After
			
			@RunWith(Parameterized)
			class Foo {
				
				@After def test() {
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test054_RunWith_After() {
		'''
			import org.junit.runner.RunWith
			import org.junit.After
			
			@RunWith(org.eclipse.xtext.testing.XtextRunner)
			class Foo {
				
				@After def test() {
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test055_RunWith_After() {
		'''
			import org.eclipse.xtext.testing.XtextRunner
			import org.junit.runner.RunWith
			import org.junit.After
			
			@RunWith(XtextRunner)
			class Foo {
				
				@After def test() {
				}
			}
		'''.hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test056_RunWith_After() {
		'''
			import org.junit.runner.RunWith
			import org.junit.After
			
			@RunWith(com.foo.bar.MyRunner)
			class Foo {
				
				@After def test() {
				}
			}
		'''.withRunner('''
			package com.foo.bar

			import org.eclipse.xtext.testing.XtextRunner
			
			class MyRunner extends XtextRunner {
				new(Class<?> klass) throws org.junit.runners.model.InitializationError {
					super(klass);
				}
			}
		''').hasNoValidationIssue()
	}

	@Test def test057_RunWith_After() {
		'''
			import com.foo.bar.MyRunner
			import org.junit.runner.RunWith
			import org.junit.After
			
			@RunWith(MyRunner)
			class Foo {
				
				@After def test() {
				}
			}
		'''.withRunner('''
			package com.foo.bar

			import org.eclipse.xtext.testing.XtextRunner
			
			class MyRunner extends XtextRunner {
				new(Class<?> klass) throws org.junit.runners.model.InitializationError {
					super(klass);
				}
			}
		''').hasNoValidationIssue()
	}

	@Test def test060_RunWith_withSuperClass() {
		'''
			import org.junit.Test
			
			class Foo extends Bar {
				
				@Test def test() {
				}
			}
		'''.withSuperClass('''
			import org.junit.runner.RunWith
			
			@RunWith(org.junit.runners.Parameterized)
			class Bar {
			}
		''').hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test061_RunWith_withSuperClass() {
		'''
			import org.junit.Test
			
			class Foo extends Bar {
				
				@Test def test() {
				}
			}
		'''.withSuperClass('''
			import org.junit.runner.RunWith
			
			@RunWith(org.eclipse.xtext.testing.XtextRunner)
			class Bar {
			}
		''').hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test062_RunWith_withSuperClass() {
		'''
			import org.junit.Test
			
			class Foo extends Bar {
				
				@Test def test() {
				}
			}
		'''.withSuperClassAndRunner('''
			import org.junit.runner.RunWith
			
			@RunWith(com.foo.bar.MyRunner)
			class Bar {
			}
		''', '''
			package com.foo.bar

			class MyRunner extends org.eclipse.xtext.testing.XtextRunner {
				new(Class<?> klass) throws org.junit.runners.model.InitializationError {
					super(null);
				}
			}
		''').hasNoValidationIssue()
	}

	@Test def test063_RunWith_withSuperClass() {
		'''
			import org.junit.runner.RunWith
			import org.junit.Test
			
			@RunWith(org.junit.runners.Parameterized)
			class Foo extends Bar {
				
				@Test def test() {
				}
			}
		'''.withSuperClassAndRunner('''
			import org.junit.runner.RunWith
			
			@RunWith(com.foo.bar.MyRunner)
			class Bar {
			}
		''', '''
			package com.foo.bar

			class MyRunner extends org.eclipse.xtext.testing.XtextRunner {
				new(Class<?> klass) throws org.junit.runners.model.InitializationError {
					super(null);
				}
			}
		''').hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test064_RunWith_withSuperClass() {
		'''
			import org.junit.runner.RunWith
			import org.junit.Test
			
			@RunWith(org.eclipse.xtext.testing.XtextRunner)
			class Foo extends Bar {
				
				@Test def test() {
				}
			}
		'''.withSuperClassAndRunner('''
			import org.junit.runner.RunWith
			
			@RunWith(com.foo.bar.MyRunner)
			class Bar {
			}
		''', '''
			package com.foo.bar

			class MyRunner extends org.eclipse.xtext.testing.XtextRunner {
				new(Class<?> klass) throws org.junit.runners.model.InitializationError {
					super(null);
				}
			}
		''').hasOneValidationIssue("JUnit method test() must be void but is Object.")
	}

	@Test def test065_RunWith_withSuperClass() {
		'''
			import org.junit.runner.RunWith
			import org.junit.Test
			
			@RunWith(com.foo.bar.MyRunner)
			class Foo extends Bar {
				
				@Test def test() {
				}
			}
		'''.withSuperClassAndRunner('''
			import org.junit.runner.RunWith
			
			@RunWith(org.junit.runners.Parameterized)
			class Bar {
			}
		''', '''
			package com.foo.bar

			class MyRunner extends org.eclipse.xtext.testing.XtextRunner {
				new(Class<?> klass) throws org.junit.runners.model.InitializationError {
					super(null);
				}
			}
		''').hasNoValidationIssue()
	}

	@Test def test066_RunWith_withSuperClass() {
		'''
			import org.junit.runner.RunWith
			import org.junit.Test
			
			@RunWith(com.foo.bar.MyRunner)
			class Foo extends Bar {
				
				@Test def test() {
				}
			}
		'''.withSuperClassAndRunner('''
			import org.junit.runner.RunWith
			
			@RunWith(org.eclipse.xtext.testing.XtextRunner)
			class Bar {
			}
		''', '''
			package com.foo.bar

			class MyRunner extends org.eclipse.xtext.testing.XtextRunner {
				new(Class<?> klass) throws org.junit.runners.model.InitializationError {
					super(null);
				}
			}
		''').hasNoValidationIssue()
	}


	private def void hasNoValidationIssue(CharSequence it) {
		assertNumberOfValidationIssues(0)
	}

	private def hasOneValidationIssue(CharSequence it, String message) {
		hasOneValidationIssue(XTEND_FUNCTION, INVALID_RETURN_TYPE_IN_CASE_OF_JUNIT_ANNOTATION, message)
	}

	private def hasOneValidationIssue(CharSequence it, EClass objectType, String issueCode, String message) {
		toString.file.hasOneValidationIssue(objectType, issueCode, message)
	}

	private def assertNumberOfValidationIssues(CharSequence it, int expectedNumberOfIssues) {
		toString.file.assertNumberOfValidationIssues(expectedNumberOfIssues)
	}

	private def withRunner(CharSequence it, CharSequence runnerDef) {
		val files = files(false, runnerDef.toString, it.toString).iterator
		val runnerIssues = files.next.validate
		assertEquals('Runner class has issues:\n' + runnerIssues.join('\n'), 0, runnerIssues.size)
		return files.next
	}

	private def withSuperClass(CharSequence it, CharSequence superClazzDef) {
		val files = files(false, superClazzDef.toString, it.toString).iterator
		val superIssues = files.next.validate
		assertEquals('Super class has issues:\n' + superIssues.join('\n'), 0, superIssues.size)
		return files.next
	}

	private def withSuperClassAndRunner(CharSequence it, CharSequence superClazzDef, CharSequence runnerDef) {
		val files = files(false, runnerDef.toString, superClazzDef.toString, it.toString).iterator
		val runnerIssues = files.next.validate
		assertEquals('Runner class has issues:\n' + runnerIssues.join('\n'), 0, runnerIssues.size)
		val superIssues = files.next.validate
		assertEquals('Super class has issues:\n' + superIssues.join('\n'), 0, superIssues.size)
		return files.next
	}

	private def void hasNoValidationIssue(XtendFile it) {
		assertNumberOfValidationIssues(0)
	}

	private def hasOneValidationIssue(XtendFile it, String message) {
		hasOneValidationIssue(XTEND_FUNCTION, INVALID_RETURN_TYPE_IN_CASE_OF_JUNIT_ANNOTATION, message)
	}

	private def hasOneValidationIssue(XtendFile it, EClass objectType, String issueCode, String message) {
		assertNumberOfValidationIssues(1)
		assertError(objectType, issueCode, message)
	}

	private def assertNumberOfValidationIssues(XtendFile it, int expectedNumberOfIssues) {
		val issues = validate
		assertEquals(
			if (!issues.empty) 'Issues:\n' + issues.join('\n'),
			expectedNumberOfIssues, issues.size
		)
		it
	}	
}