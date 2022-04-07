/*******************************************************************************
 * Copyright (c) 2012, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.compiler

import com.google.inject.Inject
import org.eclipse.xtend.core.tests.AbstractXtendTestCase
import org.eclipse.xtext.common.types.JvmDeclaredType
import org.eclipse.xtext.generator.IFilePostProcessor
import org.eclipse.xtext.xbase.compiler.ElementIssueProvider
import org.eclipse.xtext.xbase.compiler.IGeneratorConfigProvider
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator
import org.junit.Test

class XtendCompilerErrorHandlingTest extends AbstractXtendTestCase {
	
	@Inject JvmModelGenerator generator
	
	@Inject ElementIssueProvider.Factory issueProviderFactory
	
	@Inject IGeneratorConfigProvider generatorConfigProvider

	@Inject protected IFilePostProcessor postProcessor

	@Test
	def testUnresolvedSuperclass() {
		'''
			class Foo extends Unresolved {
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo /* implements Unresolved  */{
			}
		''')
	}
	
	@Test
	def testPrivateToplevelClass() {
		'''
			private class C {
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			class C {
			}
		''')
	}
		
	@Test
	def testUnresolvedInterface() {
		'''
			class Foo implements Unresolved {
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo /* implements Unresolved  */{
			}
		''')
	}
		
	@Test
	def testUnresolvedInterface_1() {
		'''
			class Foo implements Cloneable, Unresolved {
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo implements Cloneable/* , Unresolved */ {
			}
		''')
	}
		
	@Test
	def testUnresolvedInterface_2() {
		'''
			class Foo implements Unresolved, Cloneable {
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo implements /* Unresolved */Cloneable {
			}
		''')
	}
		
	@Test
	def testUnresolvedAnnotation() {
		'''
			@MyAnnotation
			class Foo {
			}
		'''.assertCompilesTo( '''
			/* @MyAnnotation */@SuppressWarnings("all")
			public class Foo {
			}
		''')
	}
		
	@Test
	def testUnresolvedAnnotation_1() {
		'''
			@Deprecated
			@Unresolved
			class Foo {
			}
		'''.assertCompilesTo( '''
			@Deprecated/* 
			@Unresolved */
			@SuppressWarnings("all")
			public class Foo {
			}
		''')
	}
	
	//TODO
	/*
	 * the @SuppressWarnings should be transformed into
	 * a synthetic @SuppressWarnings("all") on class declarations.
	 */
	@Test
	def testUnresolvedAnnotation_2() {
		'''
			@MyAnnotation(x = 'foo')
			@SuppressWarnings("unused")
			class Foo {
			}
		'''.assertCompilesTo( '''
			/* @MyAnnotation() */@SuppressWarnings("unused")
			public class Foo {
			}
		''')
	}
		
	@Test
	def testUnresolvedFieldType() {
		'''
			class Foo {
				Unresolved bar
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo {
			  private /* Unresolved */Object bar;
			}
		''')
	}
		
	@Test
	def testUnresolvedReturnType() {
		'''
			class Foo {
				def Unresolved bar() {
					null
				}
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo {
			  public /* Unresolved */Object bar() {
			    return null;
			  }
			}
		''')
	}
		
	@Test
	def testUnresolvedParameterType() {
		'''
			class Foo {
				def void bar(Unresolved p) {
				}
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo {
			  public void bar(final /* Unresolved */Object p) {
			  }
			}
		''')
	}
		
	@Test
	def testUnresolvedException() {
		'''
			class Foo {
				def void bar() throws Unresolved {
				}
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo {
			  public void bar()/*  throws Unresolved */ {
			  }
			}
		''')
	}
		
	@Test
	def testUnresolvedException_1() {
		'''
			class Foo {
				def void bar() throws Unresolved, RuntimeException {
				}
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo {
			  public void bar() throws /* Unresolved */RuntimeException {
			  }
			}
		''')
	}
		
	@Test
	def testUnresolvedException_2() {
		'''
			class Foo {
				def void bar() throws RuntimeException, Unresolved {
				}
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo {
			  public void bar() throws RuntimeException/* , Unresolved */ {
			  }
			}
		''')
	}	

	@Test
	def testUnresolvedTypeConstraint() {
		'''
			class Foo <T extends Unresolved> {}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo<T/*  extends Unresolved */> {
			}
		''')
	}
	
	@Test
	def testFieldInitializerTypeError() {
		'''
			class Foo {
				val int bar = null
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo {
			  private final int bar /* Skipped initializer because of errors */;
			}
		''')
	}

	@Test
	def testFieldInitializerLinkError() {
		'''
			class Foo {
				val bar = foo()
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo {
			  private final Object bar /* Skipped initializer because of errors */;
			}
		''')
	}

	@Test
	def testMethodBodyTypeError() {
		'''
			class Foo {
				def int bar() {
					null
				}
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo {
			  public int bar() {
			    throw new Error("Unresolved compilation problems:"
			      + "\nType mismatch: cannot convert from null to int");
			  }
			}
		''')
	}
	
	@Test
	def testMethodBodyLinkError() {
		'''
			class Foo {
				def int bar() {
					foo()
				}
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo {
			  public int bar() {
			    throw new Error("Unresolved compilation problems:"
			      + "\nThe method foo() is undefined");
			  }
			}
		''')
	}
	
	@Test
	def testMethodOrFieldLinkError() {
		'''
			class Foo {
				def int bar() {
					foo
				}
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo {
			  public int bar() {
			    throw new Error("Unresolved compilation problems:"
			      + "\nThe method or field foo is undefined");
			  }
			}
		''')
	}
	
	@Test
	def testMethodLinkReceiverError() {
		'''
			class Foo {
				def int bar() {
					this.foo()
				}
			}
		'''.assertCompilesTo( '''
			@SuppressWarnings("all")
			public class Foo {
			  public int bar() {
			    throw new Error("Unresolved compilation problems:"
			      + "\nThe method foo() is undefined for the type Foo");
			  }
			}
		''')
	}
	
	@Test
	def testBug462914() {
		'''
			class C {
				def create DoesNotExists::createZonk m(String s) {
				}
			}
		'''.assertCompilesTo( '''
			import java.util.ArrayList;
			import java.util.HashMap;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			
			@SuppressWarnings("all")
			public class C {
			  public Object m(final String s) {
			    throw new Error("Unresolved compilation problems:"
			      + "\nDoesNotExists cannot be resolved to a type."
			      + "\ncreateZonk cannot be resolved");
			  }
			
			  private final /* HashMap<ArrayList<?>, Object> */Object _createCache_m = CollectionLiterals.newHashMap();
			
			  private void _init_m(final Object it, final String s) {
			  }
			}
		''')
	}
	
	@Test
	def void testBug482845() {
		'''
			class C {
				val foo = String.
			}
		'''.assertCompilesTo('''
			@SuppressWarnings("all")
			public class C {
			  private final Object foo = String.class./* name is null */;
			}
		''', false)
	}
	
	def assertCompilesTo(CharSequence input, CharSequence expected) { 
		assertCompilesTo(input, expected, true)	
	}
	
	def assertCompilesTo(CharSequence input, CharSequence expected, boolean shouldBeSyntacticallyValid) {
		val file = file(input.toString(), false, shouldBeSyntacticallyValid)
		val resource = file.eResource
		try {
			// we need to attach the data here explicitly since #generateType is called directly
			issueProviderFactory.attachData(resource)
			val inferredType = resource.contents.filter(JvmDeclaredType).head
			var javaCode = generator.generateType(inferredType, generatorConfigProvider.get(inferredType));
			javaCode = postProcessor.postProcess(null, javaCode)
			assertEquals(expected.toString, javaCode.toString)
		} finally {
			issueProviderFactory.detachData(resource);
		}
	}
}