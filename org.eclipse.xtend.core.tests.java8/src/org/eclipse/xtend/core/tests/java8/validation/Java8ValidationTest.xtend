/*******************************************************************************
 * Copyright (c) 2015, 2016 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.java8.validation

import com.google.inject.Inject
import org.eclipse.xtend.core.tests.AbstractXtendTestCase
import org.eclipse.xtend.core.tests.java8.Java8RuntimeInjectorProvider
import org.eclipse.xtext.diagnostics.Severity
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test

import static org.eclipse.xtend.core.validation.IssueCodes.*
import static org.eclipse.xtend.core.xtend.XtendPackage.Literals.*
import static org.eclipse.xtext.xbase.XbasePackage.Literals.*
import static org.eclipse.xtext.xbase.validation.IssueCodes.*

/**
 * @author Miro Spoenemann - Initial contribution and API
 * @author Stephane Galland - https://github.com/eclipse/xtext-xtend/issues/191
 */
@InjectWith(Java8RuntimeInjectorProvider) 
class Java8ValidationTest extends AbstractXtendTestCase {
	
	@Inject extension ValidationTestHelper
	
	@Test
	def void testInheritedDefaultMethod() {
		file('''
			interface A {
				def void foo() { }
			}
			class C implements A {
				def bar() { foo }
			}
		''').assertNoErrors
	}
	
	@Test
	def void testRedeclaredMethodFromObject() {
		file('''
			interface A {
				override String toString()
			}
			class C implements A {
			}
		''').assertNoErrors
	}
	
	@Test
	def void testRedeclaredMethodFromCustomClass() {
		file('''
			interface A {
				def void m()
			}
			class B {
				def void m() {}
			}
			class C extends B {}
			class D extends C implements A {
			}
		''').assertNoErrors
	}
	
	@Test
	def void testConflictingDefaultMethods01() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo() { }
			}
			class C implements A, B { }
		''').assertError(XTEND_CLASS, CONFLICTING_DEFAULT_METHODS,
			"The type C inherits multiple implementations of the method foo() from A and B.")
	}
	
	@Test
	def void testConflictingDefaultMethods02() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo() { }
			}
			interface C extends A, B { }
		''').assertError(XTEND_INTERFACE, CONFLICTING_DEFAULT_METHODS,
			"The type C inherits multiple implementations of the method foo() from A and B.")
	}
	
	@Test
	def void testConflictingDefaultMethods03() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo()
			}
			class C implements A, B { }
		''').assertError(XTEND_CLASS, CONFLICTING_DEFAULT_METHODS,
			"The non-abstract method foo() inherited from A conflicts with the method foo() inherited from B.")
	}
	
	@Test
	def void testConflictingDefaultMethods04() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo()
			}
			interface C extends A, B { }
		''').assertError(XTEND_INTERFACE, CONFLICTING_DEFAULT_METHODS,
			"The non-abstract method foo() inherited from A conflicts with the method foo() inherited from B.")
	}
	
	@Test
	def void testConflictingDefaultMethods05() {
		file('''
			interface A {
				def int foo(java.util.List<String> list) { 0 }
			}
			interface B {
				def double foo(java.util.List<Class<?>> list) { 0 }
			}
			class C implements A, B { }
		''').assertError(XTEND_CLASS, CONFLICTING_DEFAULT_METHODS,
			"The type C inherits multiple implementations of the method foo(List<String>) from A and B.")
	}
	
	@Test
	def void testConflictingDefaultMethods06() {
		file('''
			interface A {
				def int foo(java.util.List<String> list) { 0 }
			}
			interface B {
				def double foo(java.util.List<Class<?>> list) { 0 }
			}
			interface C extends A, B { }
		''').assertError(XTEND_INTERFACE, CONFLICTING_DEFAULT_METHODS,
			"The type C inherits multiple implementations of the method foo(List<String>) from A and B.")
	}
	
	@Test
	def void testConflictingDefaultMethods07() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo() { }
			}
			interface C {
				def void foo() { }
			}
			interface D extends A, B {
				override foo() { }
			}
			class E implements A, B, C, D { }
		''').assertError(XTEND_CLASS, CONFLICTING_DEFAULT_METHODS,
			"The type E inherits multiple implementations of the method foo() from C and D.")
	}
	
	@Test
	def void testConflictingDefaultMethods08() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo() { }
			}
			interface C {
				def void foo() { }
			}
			interface D extends A, B {
				override foo() { }
			}
			class E implements A, B, D, C { }
		''').assertError(XTEND_CLASS, CONFLICTING_DEFAULT_METHODS,
			"The type E inherits multiple implementations of the method foo() from D and C.")
	}
	
	@Test
	def void testConflictingDefaultMethods09() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo()
			}
			interface C {
				def void foo()
			}
			interface D extends A, B {
				override foo() { }
			}
			class E implements A, B, D, C { }
		''').assertError(XTEND_CLASS, CONFLICTING_DEFAULT_METHODS,
			"The non-abstract method foo() inherited from D conflicts with the method foo() inherited from C.")
	}
	
	@Test
	def void testConflictingDefaultMethods10() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo()
			}
			interface C {
				def void foo()
			}
			interface D extends A, B {
				override foo() { }
			}
			class E implements B, C, A, D { }
		''').assertError(XTEND_CLASS, CONFLICTING_DEFAULT_METHODS,
			"The non-abstract method foo() inherited from D conflicts with the method foo() inherited from C.")
	}
	
	@Test
	def void testConflictingDefaultMethods11() {
		file('''
			interface I {
				def void m() {}
			}
			interface J {
				def void m();
			}
			interface J1 extends J {}
			class E implements I, J1 {}
		''').assertError(XTEND_CLASS, CONFLICTING_DEFAULT_METHODS,
			"The non-abstract method m() inherited from I conflicts with the method m() inherited from J.")
	}
	
	@Test
	def void testMissingImplementation_01() {
		file('''
			abstract class A {
				def void m();
			}
			abstract class B extends A {} 
			interface I {
				def void m() {}
			}
			class C extends B implements I {}
		''').assertError(XTEND_CLASS, CLASS_MUST_BE_ABSTRACT,
			"The class C must be defined abstract because it does not implement m()")
	}
	
	@Test
	def void testMissingImplementation_02() {
		file('''
			interface I {
				def void m() {}
			}
			interface J extends I {
				override void m()
			} 
			class C implements J {}
		''').assertError(XTEND_CLASS, CLASS_MUST_BE_ABSTRACT,
			"The class C must be defined abstract because it does not implement m()")
	}
	
	@Test
	def void testMissingImplementation_03() {
		file('''
			interface I {
				def void m() {}
			}
			interface J extends I {
			}
			interface K extends J {
				override void m()
			}
			interface L extends J {}
			class C implements I, K, L {}
		''').assertError(XTEND_CLASS, CLASS_MUST_BE_ABSTRACT,
			"The class C must be defined abstract because it does not implement m()")
	}
	
	@Test
	def void testResolvedConflict01() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo() { }
			}
			class C implements A, B {
				override foo() { }
			}
		''').assertNoErrors
	}
	
	@Test
	def void testResolvedConflict02() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo() { }
			}
			interface C extends A, B {
				override foo() { }
			}
			class D implements A, B, C { }
		''').assertNoErrors
	}
	
	@Test
	def void testResolvedConflict03() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo() { }
			}
			interface C extends A, B {
				override foo() { }
			}
			class D implements A, C, B { }
		''').assertNoErrors
	}
	
	@Test
	def void testResolvedConflict04() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo() { }
			}
			interface C extends A, B {
				override foo() { }
			}
			class D implements C, A, B { }
		''').assertNoErrors
	}
	
	@Test
	def void testResolvedConflict05() {
		file('''
			interface I {
				def void m() {}
			}
			interface J extends I {
			}
			interface K extends J {
				override void m() {}
			}
			interface L extends J {}
			class C implements I, J, L {}
		''').assertNoErrors
	}
	
	@Test
	def void testInheritedConflict01() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo() { }
			}
			class C implements A, B { }
			class D extends C { }
		''').assertNoIssues(XTEND_CLASS, CONFLICTING_DEFAULT_METHODS, 106, -1, Severity.ERROR)
	}
	
	@Test
	def void testInheritedConflict02() {
		file('''
			interface A {
				def void foo() { }
			}
			interface B {
				def void foo() { }
			}
			interface C extends A, B { }
			interface D extends C { }
		''').assertNoIssues(XTEND_INTERFACE, CONFLICTING_DEFAULT_METHODS, 111, -1, Severity.ERROR)
	}
	
	@Test
	def void testAbstractMethodCall() {
		file('''
			interface A {
				def void foo()
			}
			class E implements A {
				override void foo() {
					A.super.foo
				}
			}
		''').assertError(XMEMBER_FEATURE_CALL, ABSTRACT_METHOD_INVOCATION,
			"Cannot directly invoke the abstract method foo() of the type A")
	}
	
	@Test
	def void testInterfaceSuperCall() {
		file('''
			class Foo {
				def void foo() {
					java.util.List.super.clear
				}
			}
		''').assertError(XMEMBER_FEATURE_CALL, NO_ENCLOSING_INSTANCE_AVAILABLE,
			"The enclosing type does not extend or implement the interface List")
	}
	
	/**
	private static class ResolvedByAbstractType01 {
		class A<T> extends java.util.AbstractCollection<T> implements java.util.Set<T> {
			public java.util.Iterator<T> iterator() {
				return null;
			}
			public int size() {
				return 0;
			}
		}
		class Test {
			public void test(java.util.AbstractCollection<?> a) {
				a.spliterator();
			}
			public void test(A<?> a) {
				a.spliterator();
			}
		}
	}
	*/
	@Test
	def void testResolvedByAbstractType01() {
		file('''
			import java.util.Iterator
			import java.util.Set
			import java.util.AbstractCollection
			class A<T> extends AbstractCollection<T> implements Set<T> {
			  override Iterator<T> iterator() {
			    null
			  }
			  override int size() {
			    0
			  }
			}
		''').assertNoErrors()
	}

	/**
	private static class ResolvedByAbstractType02 {
		abstract class B<T> extends java.util.AbstractCollection<T> {
			public java.util.Spliterator<T> splititerator() {
				return null;
			}
		}
		class A<T> extends B<T> implements java.util.Set<T> {
			public java.util.Iterator<T> iterator() {
				return null;
			}
			public int size() {
				return 0;
			}
		}
		class Test {
			public void test(java.util.AbstractCollection<?> a) {
				a.spliterator();
			}
			public void test(A<?> a) {
				a.spliterator();
			}
		}
	}
	*/
	@Test
	def void testResolvedByAbstractType02() {
		file('''
			import java.util.Iterator
			import java.util.Set
			import java.util.AbstractCollection
			abstract class B<T> extends AbstractCollection<T> {
			  def java.util.Spliterator<T> splititrator() {
			    null
			  }
			}
			class A<T> extends B<T> implements Set<T> {
			  override Iterator<T> iterator() {
			    null
			  }
			  override int size() {
			    0
			  }
			}
		''').assertNoErrors()
	}

	/**
	private static class ResolvedByAbstractType03 {
		interface I<T> extends java.util.Set<T> {
			
		}
		class A<T> extends java.util.AbstractCollection<T> implements I<T> {
			public java.util.Iterator<T> iterator() {
				return null;
			}
			public int size() {
				return 0;
			}
		}
		class Test {
			public void test(java.util.AbstractCollection<?> a) {
				a.spliterator();
			}
			public void test(A<?> a) {
				a.spliterator();
			}
		}
	}
	*/
	@Test
	def void testResolvedByAbstractType03() {
		file('''
			import java.util.Iterator
			import java.util.Set
			import java.util.AbstractCollection
			interface I<T> extends Set<T> {
			}
			class A<T> extends AbstractCollection<T> implements I<T> {
			  override Iterator<T> iterator() {
			    null
			  }
			  override int size {
			    0
			  }
			}
		''').assertNoErrors()
	}

}
