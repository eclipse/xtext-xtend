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
public class CompilerBug424329Test extends AbstractXtendCompilerTest {
  @Test
  public void testBug_424329_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(String s){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("A.mainDir = \"D:\" ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final String s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(\"D:\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(String s){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("mainDir = \"D:\" ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final String s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(\"D:\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(Class<? extends A> c, String s){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("A.mainDir = \"D:\" ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final Class<? extends A> c, final String s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(A.class, \"D:\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(Class<? extends A> c, String s){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("B.mainDir = \"D:\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class B extends A {}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final Class<? extends A> c, final String s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(B.class, \"D:\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(Class<? extends A> c, String... s){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("B.mainDir = #[\"D:\"] ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class B extends A {}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final Class<? extends A> c, final String... s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(B.class, new String[] { \"D:\" });");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_06() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(String s){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("A.setMainDir(\"D:\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final String s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(\"D:\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_07() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(String s){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("setMainDir(\"D:\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final String s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(\"D:\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_08() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(Class<? extends A> c, String s){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("A.setMainDir(\"D:\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final Class<? extends A> c, final String s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(A.class, \"D:\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_09() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(Class<? extends A> c, String s){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("B.setMainDir(\"D:\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class B extends A {}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final Class<? extends A> c, final String s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(B.class, \"D:\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_10() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(Class<? extends A> c, String... s){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("B.setMainDir(#[\"D:\"])");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class B extends A {}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final Class<? extends A> c, final String... s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(B.class, new String[] { \"D:\" });");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_11() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(Class<? extends A> c, String... s){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("B.setMainDir(\"D:\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class B extends A {}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final Class<? extends A> c, final String... s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(B.class, \"D:\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_12() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(Class<? extends A> c, String... s){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("B.setMainDir(\"D:\", \"E:\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class B extends A {}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final Class<? extends A> c, final String... s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(B.class, \"D:\", \"E:\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_13() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(Class<? extends A> c, String... s){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("B.setMainDir(null) // == B.setMainDir(null, new String[0])");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class B extends A {}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final Class<? extends A> c, final String... s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("B.setMainDir(null);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_14() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(Class<? extends A> c, String... s){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("B.setMainDir()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class B extends A {}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final Class<? extends A> c, final String... s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(B.class);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_15() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(Class<? extends A> c, String... s){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("B.setMainDir");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class B extends A {}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final Class<? extends A> c, final String... s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("A.setMainDir(B.class);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424329_16() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("public def static void setMainDir(Class<? extends A> c, String... s){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String[] args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("B.setMainDir(null, null)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class B extends A {}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void setMainDir(final Class<? extends A> c, final String... s) {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void main(final String[] args) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("B.setMainDir(null, null);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug_424886_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class B {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("def execute() {");
    _builder.newLine();
    _builder.append("     ");
    _builder.append("A.myVar = \"myVar\"");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class A {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("def static setMyVar(String param) {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class B {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Object execute() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return A.setMyVar(\"myVar\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
