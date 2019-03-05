/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.compiler

import org.junit.Test
import com.google.inject.Inject
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtend.core.xtend.XtendFile
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.eclipse.xtext.xbase.validation.IssueCodes
import org.eclipse.xtext.xbase.XbasePackage

/**
 * @author Eva Poell - Initial contribution and API
 */
class TryWithResourcesTest extends AbstractXtendCompilerTest {

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
			a.read'''.test1('''      final StringReader a = new StringReader(this.s);
      try {
        a.read();
      } finally {
        if (a != null) a.close();
      }
      ''')
	}
	
	@Test
	def test_assumeTypeAutoClosable() {
		'''try (val someCloseable = [  System.out.println("Closing") ]) {
		}'''.test0('''      final AutoCloseable someCloseable = null;
      try {
      } finally {
        if (someCloseable != null) someCloseable.close();
      }
      ''')
	}
	
	@Test
	def void test_twoResources() {
		'''try (val sr = new StringReader(s); val buffy = new BufferedReader(sr)) {
			buffy.read
		}'''.test2('''      final StringReader sr = new StringReader(this.s);
      final BufferedReader buffy = new BufferedReader(sr);
      try {
        buffy.read();
      } finally {
        if (buffy != null) buffy.close();
        if (sr != null) sr.close();
      }
      ''')
	}
	
	@Test
	def void test_twoNestedResources() {
		'''try (val br = new BufferedReader(new StringReader(s));) 
					br.readLine()'''.test2('''      final BufferedReader br = new BufferedReader(new StringReader(this.s));
      try {
        br.readLine();
      } finally {
        if (br != null) br.close();
      }
      ''')
	}
	
	@Test
	def void test_twoNestedResourcesOneDefinedOutside() {
		'''val sr = new StringReader(s)
		try (val br = new BufferedReader(sr);) 
					br.readLine()'''.test2('''      final StringReader sr = new StringReader(this.s);
      final BufferedReader br = new BufferedReader(sr);
      try {
        br.readLine();
      } finally {
        if (br != null) br.close();
      }
      ''')
	}
	
	@Test
	def void test_nestedIf1() {
		'''try (val fr = new StringReader(if (true) s+"1" else s+"2"); val br = new BufferedReader(fr)) {
			br.read
		}'''.test3('''      final StringReader fr = new StringReader(new Function0<String>() {
        public String apply() {
          String _xifexpression = null;
          if (true) {
            _xifexpression = (FooClass.this.s + "1");
          } else {
            _xifexpression = (FooClass.this.s + "2");
          }
          return _xifexpression;
        }
      }.apply());
      final BufferedReader br = new BufferedReader(fr);
      try {
        br.read();
      } finally {
        if (br != null) br.close();
        if (fr != null) fr.close();
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
	
	def private void test3(CharSequence input, CharSequence expected) {
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
