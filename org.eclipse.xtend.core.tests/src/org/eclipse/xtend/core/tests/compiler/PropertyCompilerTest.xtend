/*******************************************************************************
 * Copyright (c) 2014, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.compiler

import com.google.inject.Inject
import org.eclipse.xtend.core.xtend.XtendPackage
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
class PropertyCompilerTest extends AbstractXtendCompilerTest {
	@Inject
	extension ValidationTestHelper
	
	@Test
	def testBug438347_01() {
		assertCompilesTo('''
			class C<T> {
				@Property var (T)=>void s
			}
		''', '''
			import org.eclipse.xtend.lib.Property;
			import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
			import org.eclipse.xtext.xbase.lib.Pure;
			
			@SuppressWarnings("all")
			public class C<T extends Object> {
			  @Property
			  private Procedure1<? super T> _s;
			
			  @Pure
			  public Procedure1<? super T> getS() {
			    return this._s;
			  }
			
			  public void setS(final Procedure1<? super T> s) {
			    this._s = s;
			  }
			}
		''')
	}
	
	@Test
	def testBug438347_02() {
		assertCompilesTo('''
			class C {
				@Property var (String)=>void s
			}
		''', '''
			import org.eclipse.xtend.lib.Property;
			import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
			import org.eclipse.xtext.xbase.lib.Pure;
			
			@SuppressWarnings("all")
			public class C {
			  @Property
			  private Procedure1<? super String> _s;
			
			  @Pure
			  public Procedure1<? super String> getS() {
			    return this._s;
			  }
			
			  public void setS(final Procedure1<? super String> s) {
			    this._s = s;
			  }
			}
		''')
	}
	
	@Test
	def compileProperty() {
		assertCompilesTo(
			'''
				package foo

				class Bar {
					@Property
					boolean generateExpressions = true
				}
			''',
			'''
				package foo;

				import org.eclipse.xtend.lib.Property;
				import org.eclipse.xtext.xbase.lib.Pure;
				
				@SuppressWarnings("all")
				public class Bar {
				  @Property
				  private boolean _generateExpressions = true;
				
				  @Pure
				  public boolean isGenerateExpressions() {
				    return this._generateExpressions;
				  }
				
				  public void setGenerateExpressions(final boolean generateExpressions) {
				    this._generateExpressions = generateExpressions;
				  }
				}
			''')
	}
	
	@Test
	def compileReadonlyPropertyWithoutType() {
		assertCompilesTo(
			'''
				class C {
					@Property
					val string = ''
				}
			''',
			'''
				import org.eclipse.xtend.lib.Property;
				import org.eclipse.xtext.xbase.lib.Pure;
				
				@SuppressWarnings("all")
				public class C {
				  @Property
				  private final String _string = "";
				
				  @Pure
				  public String getString() {
				    return this._string;
				  }
				}
			''')
	}
	
	@Test
	def compilePropertyWithoutType() {
		clazz(
			'''
				class C {
					@Property
					var string = ''
				}
			''').members.head.assertError(XtendPackage.Literals.XTEND_FIELD, "user.issue", "inferred")
	}
	
	@Test
	def compilePropertyWithTypeParameter() {
		assertCompilesTo(
			'''
				class C<T> {
					@Property
					var T t
				}
			''',
			'''
				import org.eclipse.xtend.lib.Property;
				import org.eclipse.xtext.xbase.lib.Pure;
				
				@SuppressWarnings("all")
				public class C<T extends Object> {
				  @Property
				  private T _t;
				
				  @Pure
				  public T getT() {
				    return this._t;
				  }
				
				  public void setT(final T t) {
				    this._t = t;
				  }
				}
			''')
	}
	
	@Test
	def compilePropertyWithoutTypeButTypeParameter() {
		clazz(
			'''
				class C<T> {
					@Property
					var iterable = null as Iterable<T>
				}
			''').members.head.assertError(XtendPackage.Literals.XTEND_FIELD, "user.issue", "inferred")
	}
	
	@Test
	def compilePropertyWithArrayType() {
		assertCompilesTo(
			'''
				class C<T> {
					@Property
					var String[] array = #['a']
				}
			''',
			'''
				import org.eclipse.xtend.lib.Property;
				import org.eclipse.xtext.xbase.lib.Pure;
				
				@SuppressWarnings("all")
				public class C<T extends Object> {
				  @Property
				  private String[] _array = { "a" };
				
				  @Pure
				  public String[] getArray() {
				    return this._array;
				  }
				
				  public void setArray(final String[] array) {
				    this._array = array;
				  }
				}
			''')
	}
	
	@Test
	def compileExplicitProperty() {
		assertCompilesTo(
			'''
				class X {
					@Property val String x = 'hello'
				}
			''', '''
				import org.eclipse.xtend.lib.Property;
				import org.eclipse.xtext.xbase.lib.Pure;
				
				@SuppressWarnings("all")
				public class X {
				  @Property
				  private final String _x = "hello";
				
				  @Pure
				  public String getX() {
				    return this._x;
				  }
				}
			''')
	}
	
	@Test
	def void testExistingGetter() {
		'''
			class Foo {
				@Property int foo
				def getFoo() {
					5
				}
			}
		'''.compile[
			val instance = compiledClass.getDeclaredConstructor().newInstance
			val getFoo = compiledClass.getDeclaredMethod("getFoo")
			assertEquals(5, getFoo.invoke(instance))
		]
	}
	
	@Test
	def void testExistingSetter() {
		'''
			class Foo {
				@Property int foo
				def setFoo(int foo) {
					_foo = 5
				}
			}
		'''.compile[
			val instance = compiledClass.getDeclaredConstructor().newInstance
			val setFoo = compiledClass.getDeclaredMethod("setFoo", int)
			setFoo.invoke(instance, 1)
			val getFoo = compiledClass.getDeclaredMethod("getFoo")
			assertEquals(5, getFoo.invoke(instance))
		]
	}
	
	@Test
	def void testStaticProperty() {
		'''
			class Foo {
				@Property static int foo = 1
			}
		'''.compile[
			val setFoo = compiledClass.getDeclaredMethod("setFoo", int)
			setFoo.invoke(null, 1)
			val getFoo = compiledClass.getDeclaredMethod("getFoo")
			assertEquals(1, getFoo.invoke(null))
		]
	}
}