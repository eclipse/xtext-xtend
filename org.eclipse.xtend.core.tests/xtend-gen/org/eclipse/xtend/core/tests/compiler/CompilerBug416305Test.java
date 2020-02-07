/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.compiler;

import org.eclipse.xtend.core.tests.compiler.AbstractXtendCompilerTest;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class CompilerBug416305Test extends AbstractXtendCompilerTest {
  @Test
  public void test_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A<T> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <E extends T> void f(Iterable<E> iterable) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <E extends T> void f(java.util.Iterator<E> iterable) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val list = #[\"abc\", \"def\"]");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val a = new A<String>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a.f(list)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Collections;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Iterator;");
    _builder_1.newLine();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <E extends T> void f(final Iterable<E> iterable) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <E extends T> void f(final Iterator<E> iterable) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(\"abc\", \"def\"));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final A<String> a = new A<String>();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("a.<String>f(list);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A<T> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <E extends T> void f(Iterable<E> iterable) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <E extends T> void f(java.util.Iterator<E> iterable) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val list = #[\"abc\", \"def\"]");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val a = new A<CharSequence>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a.f(list)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Collections;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Iterator;");
    _builder_1.newLine();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <E extends T> void f(final Iterable<E> iterable) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <E extends T> void f(final Iterator<E> iterable) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(\"abc\", \"def\"));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final A<CharSequence> a = new A<CharSequence>();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("a.<String>f(list);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A<T> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <E extends T> void f(Iterable<E> iterable) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <E extends T> void f(java.util.Iterator<E> iterable) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val list = #[]");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val a = new A<String>");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a.f(list)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Collections;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Iterator;");
    _builder_1.newLine();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <E extends T> void f(final Iterable<E> iterable) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <E extends T> void f(final Iterator<E> iterable) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList());");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final A<String> a = new A<String>();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("a.<String>f(list);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
