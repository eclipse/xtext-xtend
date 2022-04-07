/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.compiler;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class CompilerBug419688Test extends AbstractXtendCompilerTest {
  @Test
  public void test_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class D extends C<D> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new() { super() }");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super.m()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class C<T extends C<T>>{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def T m() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class D extends C<D> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public D() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("super();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public D m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return super.m();");
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
    _builder.append("class D extends C<D> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new() { super() }");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super.m()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class C<T extends C<T>>{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def Iterable<T> m() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class D extends C<D> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public D() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("super();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Iterable<D> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return super.m();");
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
    _builder.append("class D extends C<D> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new() { super() }");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super.m2()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class C<T extends C<T>>{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def T m2() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class D extends C<D> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public D() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("super();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public D m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return super.m2();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class D extends C<D> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new() { super() }");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super.m2() + m2");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class C<T extends C<T>>{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def Iterable<T> m2() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.collect.Iterables;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class D extends C<D> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public D() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("super();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Iterable<D> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("Iterable<D> _m2 = super.m2();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("Iterable<D> _m2_1 = this.m2();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return Iterables.<D>concat(_m2, _m2_1);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class D extends C<D> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new() { super() }");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super.m2()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class C<T extends C<T>>{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <V extends T> V m2() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class D extends C<D> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public D() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("super();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public D m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return super.<D>m2();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_06() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class D extends C<D> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new() { super() }");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super.<E>m2()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class E extends D {}");
    _builder.newLine();
    _builder.append("class C<T extends C<T>>{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <V extends T> V m2() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class D extends C<D> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public D() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("super();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public E m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return super.<E>m2();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_07() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class D extends C<D> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new() { super() }");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super.m2()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class C<T extends C<T>>{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <V extends Iterable<T>> V m2() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class D extends C<D> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public D() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("super();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Iterable<D> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return super.<Iterable<D>>m2();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_08() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class D extends C<D> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new() { super() }");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def DIter m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("super.m2()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("abstract class DIter implements Iterable<D> {}");
    _builder.newLine();
    _builder.append("class C<T extends C<T>>{");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <V extends Iterable<T>> V m2() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class D extends C<D> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public D() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("super();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public DIter m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return super.<DIter>m2();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
