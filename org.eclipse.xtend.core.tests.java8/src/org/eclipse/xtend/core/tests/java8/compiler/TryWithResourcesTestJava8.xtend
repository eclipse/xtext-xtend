/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.java8.compiler

import com.google.inject.Inject
import org.eclipse.xtend.core.tests.compiler.AbstractXtendCompilerTest
import org.eclipse.xtend.core.tests.java8.Java8RuntimeInjectorProvider
import org.eclipse.xtend.core.xtend.XtendFile
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.eclipse.xtext.xbase.XbasePackage
import org.eclipse.xtext.xbase.validation.IssueCodes
import org.junit.Test

/**
 * @author Eva Poell - Initial contribution and API
 */
@InjectWith(Java8RuntimeInjectorProvider)
class TryWithResourcesTestJava8 extends AbstractXtendCompilerTest {

	@Inject extension ValidationTestHelper
	@Inject extension ParseHelper<XtendFile>

	// Validation Tests
	//------------------

	@Test
	def void test_noIssues() {
		'''try (val a = new StringReader(s); 
		) 
			a.read'''.buildXtendInput(true, false).parse.assertNoIssues
	}
	
	@Test
	def void test_Error_nullResource() {
		'''try (val a = new StringReader(s); 
			val b = ""
		) 
			a.read'''.buildXtendInput(true, false).parse.assertError(XbasePackage.Literals.XVARIABLE_DECLARATION,
			IssueCodes.INVALID_TRY_RESOURCE_TYPE, "implement java.lang.AutoCloseable")
	}
	
	@Test
	def void test_Error_NotAutoClosable() {
		'''try (val a = #[1,2,3]) {}'''.buildXtendInput(true, false).parse.assertError(XbasePackage.Literals.XVARIABLE_DECLARATION,
			IssueCodes.INVALID_TRY_RESOURCE_TYPE, "implement java.lang.AutoCloseable")
	}
	
	
	// Compilation Tests
	//-------------------
	
	@Test
	def void test_normalTryWithoutResource() {
		'''val br = new BufferedReader(new StringReader(s));
		try 
			br.readLine()
		finally
			if(br !== null)
				br.close()'''.test2('''      StringReader _stringReader = new StringReader(this.s);
      final BufferedReader br = new BufferedReader(_stringReader);
      try {
        br.readLine();
      } finally {
        if ((br != null)) {
          br.close();
        }
      }
      ''')
	}
	
	@Test
	def void test_easyResource() {
		'''try (val a = new StringReader(s))
			a.read'''.test1('''      try (final StringReader a = new StringReader(this.s)) {
        a.read();
      }
	  ''')
	}
	
	@Test
	def test_assumeTypeAutoClosable() {
		'''try (val someCloseable = [  System.out.println("Closing") ]) {
		}'''.test0('''      try (final AutoCloseable someCloseable = ((AutoCloseable) () -> {
        System.out.println("Closing");
      })) {
      }
	  ''')
	}
	
	@Test
	def void test_twoResources() {
		'''try (val sr = new StringReader(s); val buffy = new BufferedReader(sr)) {
			buffy.read
		}'''.test2('''      try (final StringReader sr = new StringReader(this.s); final BufferedReader buffy = new BufferedReader(sr)) {
        buffy.read();
      }
      ''')
	}
	
	@Test
	def void test_twoNestedResources() {
		'''try (val br = new BufferedReader(new StringReader(s));) 
					br.readLine()'''.test2('''      try (final BufferedReader br = new BufferedReader(new StringReader(this.s))) {
        br.readLine();
      }
      ''')
	}
	
	@Test
	def void test_twoNestedResourcesOneDefinedOutside() {
		'''val sr = new StringReader(s)
		try (val br = new BufferedReader(sr);) 
					br.readLine()'''.test2('''      final StringReader sr = new StringReader(this.s);
      try (final BufferedReader br = new BufferedReader(sr)) {
        br.readLine();
      }
      ''')
	}
	
	@Test
	def void test_nestedIf1() {
		'''try (val fr = new StringReader(if (true) s+"1" else s+"2"); val br = new BufferedReader(fr)) {
			br.read
		}'''.test_f2('''      try (final StringReader fr = new Function0<StringReader>() {
        public StringReader apply() {
          String _xifexpression = null;
          if (true) {
            _xifexpression = (FooClass.this.s + "1");
          } else {
            _xifexpression = (FooClass.this.s + "2");
          }
          return new StringReader(_xifexpression);
        }
      }.apply(); final BufferedReader br = new BufferedReader(fr)) {
        br.read();
      }
      ''')
	}
	
	@Test
	def void test_nestedIf2() {
		'''try (val sr = if (true) new StringReader(s+"1") else new StringReader(s+"2") ) {
			sr.read
		}'''.test_f1('''      try (final StringReader sr = new Function0<StringReader>() {
        public StringReader apply() {
          StringReader _xifexpression = null;
          if (true) {
            _xifexpression = new StringReader((FooClass.this.s + "1"));
          } else {
            _xifexpression = new StringReader((FooClass.this.s + "2"));
          }
          return _xifexpression;
        }
      }.apply()) {
        sr.read();
      }
      ''')
	}
	
	@Test
	def void test_tryWithLambda () {
		'''try (var r = [System.out.println("Closing")]) {
		}'''.test0('''      try (AutoCloseable r = ((AutoCloseable) () -> {
        System.out.println("Closing");
      })) {
      }
	  ''')
	}
	
	// Helpers
	//---------
	
	def private void test0(CharSequence input, CharSequence expected) {
		(input.buildXtendInput(false, false)).assertCompilesTo(expected.buildJavaOutput(false, false, false))
	}
	
	def private void test1(CharSequence input, CharSequence expected) {
		(input.buildXtendInput(true, false)).assertCompilesTo(expected.buildJavaOutput(true, false, false))
	}
	
	def private void test2(CharSequence input, CharSequence expected) {
		(input.buildXtendInput(true, true)).assertCompilesTo(expected.buildJavaOutput(true, true, false))
	}
	
	def private void test_f1(CharSequence input, CharSequence expected) {
		(input.buildXtendInput(true, false)).assertCompilesTo(expected.buildJavaOutput(true, false, true))
	}
	
	def private void test_f2(CharSequence input, CharSequence expected) {
		(input.buildXtendInput(true, true)).assertCompilesTo(expected.buildJavaOutput(true, true, true))
	}

	def private CharSequence buildXtendInput(CharSequence input, boolean needsStringReader, boolean needsBufferedReader) {
		val start = '''
			package sample
							
			«IF needsBufferedReader»import java.io.BufferedReader«ENDIF»
			«IF needsStringReader»import java.io.StringReader«ENDIF»
			
			class FooClass {
			
			val s = "line1\nline2\nline3"

				def void fooMethod() {
			'''
		val end = '''
				}
			}
			'''
		return start + input + end
	}

	def private CharSequence buildJavaOutput(CharSequence expected, boolean needsStringReader, boolean needsBufferedReader, boolean needsFunc0) {
		val start = '''
			package sample;
			
			«IF needsBufferedReader»import java.io.BufferedReader;«ENDIF»
			«IF needsStringReader»import java.io.StringReader;«ENDIF»
			import org.eclipse.xtext.xbase.lib.Exceptions;
			«IF needsFunc0»import org.eclipse.xtext.xbase.lib.Functions.Function0;«ENDIF»
			
			@SuppressWarnings("all")
			public class FooClass {
			  private final String s = "line1\nline2\nline3";
			  
			  public void fooMethod() {
			    try {
		    '''
		val end = '''
			    } catch (Throwable _e) {
			      throw Exceptions.sneakyThrow(_e);
			    }
			  }
			}
			'''
		return start + expected + end
	}
}
