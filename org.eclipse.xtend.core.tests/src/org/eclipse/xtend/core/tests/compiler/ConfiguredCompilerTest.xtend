/*******************************************************************************
 * Copyright (c) 2013, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.compiler

import org.eclipse.xtend.core.tests.SingletonGeneratorConfigRuntimeInjectorProvider
import org.eclipse.xtext.testing.InjectWith
import org.junit.Test

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
 @InjectWith(SingletonGeneratorConfigRuntimeInjectorProvider)
class ConfiguredCompilerTest extends AbstractXtendCompilerTest {
	
	@Test
	def compileWithConfiguration() {
		val generatorConfig = generatorConfigProvider.get(null)
		generatorConfig.generateSyntheticSuppressWarnings = false
		generatorConfig.generateExpressions = false
		assertCompilesTo(
			'''
				package foo

				/**
				 * javadoc
				 */
				class Bar {
					def foo(){
						1 + 1
					}
				}
			''',
			'''
				package foo;

				/**
				 * javadoc
				 */
				public class Bar {
				  public int foo() {
				    throw new UnsupportedOperationException("foo is not implemented");
				  }
				}
			''')
	}
	
	@Test
	def testAnnotationWithValueArray_01() {
		val generatorConfig = generatorConfigProvider.get(null)
		generatorConfig.generateSyntheticSuppressWarnings = false
		generatorConfig.generateExpressions = true
		assertCompilesTo('''
			@SuppressWarnings(#[ 'abc', 'efg' ])
			class C {
			}
		''', '''
			@SuppressWarnings({ "abc", "efg" })
			public class C {
			}
		''')
	}
	
	@Test
	def testAnnotationWithValueArray_02() {
		val generatorConfig = generatorConfigProvider.get(null)
		generatorConfig.generateSyntheticSuppressWarnings = false
		generatorConfig.generateExpressions = true
		assertCompilesTo('''
			@SuppressWarnings('abc', 'efg')
			class C {
			}
		''', '''
			@SuppressWarnings({ "abc", "efg" })
			public class C {
			}
		''')
	}
	
	@Test
	def compileWithConfiguration_2() {
		val generatorConfig = generatorConfigProvider.get(null)
		generatorConfig.generateSyntheticSuppressWarnings = true
		generatorConfig.generateExpressions = false
		assertCompilesTo(
			'''
				package foo

				/**
				 * javadoc
				 */
				@SuppressWarnings("unused")
				class Bar {
					def foo(){
						1 + 1
					}
				}
			''',
			'''
				package foo;

				/**
				 * javadoc
				 */
				@SuppressWarnings("unused")
				public class Bar {
				  public int foo() {
				    throw new UnsupportedOperationException("foo is not implemented");
				  }
				}
			''')
	}

	@Test
	def compileWithConfiguration_3() {
		val generatorConfig = generatorConfigProvider.get(null)
		generatorConfig.generateSyntheticSuppressWarnings = true
		generatorConfig.generateExpressions = false
		assertCompilesTo(
			'''
				package foo

				/**
				 * javadoc
				 */
				@Deprecated
				@SuppressWarnings("unused")
				class Bar {
					def foo(){
						1 + 1
					}
				}
			''',
			'''
				package foo;

				/**
				 * javadoc
				 */
				@Deprecated
				@SuppressWarnings("unused")
				public class Bar {
				  public int foo() {
				    throw new UnsupportedOperationException("foo is not implemented");
				  }
				}
			''')
	}
	
	@Test
	def testGeneratedAnnotation_01() {
		val generatorConfig = generatorConfigProvider.get(null)
		generatorConfig.generateSyntheticSuppressWarnings = false
		generatorConfig.generateGeneratedAnnotation = true
		generatorConfig.includeDateInGeneratedAnnotation = false
		generatorConfig.generatedAnnotationComment = ''
		assertCompilesTo(
			'''
				package foo
				class Bar {
				}
			''',
			'''
				package foo;
				
				�IF !isJava11OrLater�
				import javax.annotation.Generated;
				
				@Generated("org.eclipse.xtend.core.compiler.XtendGenerator")
				�ENDIF�
				public class Bar {
				}
			''')
	}
	
	@Test
	def testGeneratedAnnotation_02() {
		val generatorConfig = generatorConfigProvider.get(null)
		generatorConfig.generateSyntheticSuppressWarnings = false
		generatorConfig.generateGeneratedAnnotation = true
		generatorConfig.includeDateInGeneratedAnnotation = false
		generatorConfig.generatedAnnotationComment = 'Source: ${sourcefile}'
		assertCompilesTo(
			'''
				package foo
				class Bar {
				}
			''',
			'''
				package foo;
				
				�IF !isJava11OrLater�
				import javax.annotation.Generated;
				
				@Generated(value = "org.eclipse.xtend.core.compiler.XtendGenerator", comments = "Source: Bar.xtend")
				�ENDIF�
				public class Bar {
				}
			''')
	}
	
}