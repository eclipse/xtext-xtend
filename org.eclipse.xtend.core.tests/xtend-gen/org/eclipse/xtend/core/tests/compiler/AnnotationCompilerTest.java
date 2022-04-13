/**
 * Copyright (c) 2013, 2018 itemis AG (http://www.itemis.eu) and others.
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
public class AnnotationCompilerTest extends AbstractXtendCompilerTest {
  @Test
  public void testAnnotationWithEnumValue_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@A(NAME)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String s1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@A(value=NAME)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String s2");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("annotation A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("E value");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("enum E {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("NAME");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@A(E.NAME)");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private String s1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@A(value = E.NAME)");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private String s2;");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationWithEnumValue_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@A(NAME)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String s1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@A(NAME, NAME)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String s2");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@A(#[NAME])");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String s3");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@A(value=NAME)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String s4");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@A(value=#[NAME])");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String s5");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("annotation A {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("E[] value");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("enum E {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("NAME");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@A(E.NAME)");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private String s1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@A({ E.NAME, E.NAME })");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private String s2;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@A({ E.NAME })");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private String s3;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@A(value = E.NAME)");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private String s4;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@A(value = { E.NAME })");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private String s5;");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotation() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo");
    _builder.newLine();
    _builder.append("class Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@com.google.inject.Inject String string");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import com.google.inject.Inject;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Bar {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Inject");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private String string;");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationWithoutDefaultStringArray() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("annotation DependsOn {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String[] value");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface DependsOn {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String[] value();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationWithDefaultEmptyStringArray() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("annotation DependsOn {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String[] value = #[]");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface DependsOn {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String[] value() default {};");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationWithDefaultStringArray() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("annotation DependsOn {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String[] value = #[ \'abc\', \'efg\' ]");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface DependsOn {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String[] value() default { \"abc\", \"efg\" };");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationWithDefaultIntArray() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("annotation DependsOn {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int[] value = #[ 1, 2 ]");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface DependsOn {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public int[] value() default { 1, 2 };");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationWithDefaultBooleanArray() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("annotation DependsOn {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("boolean[] value = #[ true, true ]");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface DependsOn {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public boolean[] value() default { true, true };");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationWithDefaultLongArray() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("annotation DependsOn {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("long[] value = #[ 1l, 2l ]");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface DependsOn {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public long[] value() default { 1l, 2l };");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationWithClassArray() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("annotation DependsOn {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Class<?>[] value = #[ typeof(String), typeof(CharSequence) ]");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface DependsOn {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Class<?>[] value() default { String.class, CharSequence.class };");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationOnAnnotation() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo");
    _builder.newLine();
    _builder.append("@java.lang.annotation.Documented");
    _builder.newLine();
    _builder.append("annotation Bar {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("@com.google.inject.Inject String string");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import com.google.inject.Inject;");
    _builder_1.newLine();
    _builder_1.append("import java.lang.annotation.Documented;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@Documented");
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface Bar {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Inject");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String string();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void compileAnnotationWithFileHeader() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* This program and the accompanying materials are made available under the");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* terms of the Eclipse Public License 2.0 which is available at");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* http://www.eclipse.org/legal/epl-2.0.");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* SPDX-License-Identifier: EPL-2.0");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("package foo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("annotation bar { ");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("String name = \'foobar\'");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("/**");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("* Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("* This program and the accompanying materials are made available under the");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("* terms of the Eclipse Public License 2.0 which is available at");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("* http://www.eclipse.org/legal/epl-2.0.");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("* ");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("* SPDX-License-Identifier: EPL-2.0");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("*/");
    _builder_1.newLine();
    _builder_1.append("package foo;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface bar {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String name() default \"foobar\";");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationType_1() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("annotation MyAnnotation { ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String x;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int y;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Class<?>[] value;");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface MyAnnotation {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String x();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public int y();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Class<?>[] value();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationType_2() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("annotation MyAnnotation { ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String x = \'foo\'");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int y = 42");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Class<?> value = typeof(String)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("boolean flag = true");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface MyAnnotation {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String x() default \"foo\";");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public int y() default 42;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Class<?> value() default String.class;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public boolean flag() default true;");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationType_3() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("annotation annotation { ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String annotation = \'foo\'");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val inferred = \'bar\'");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val inferredClass = typeof(StringBuilder)");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface annotation {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String annotation() default \"foo\";");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String inferred() default \"bar\";");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Class<StringBuilder> inferredClass() default StringBuilder.class;");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationWithIntArray() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class TestXtend {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val static int a = 4");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Click(#[ a, a ])");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def meth() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("annotation Click {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int[] value");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class TestXtend {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private static final int a = 4;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Click({ TestXtend.a, TestXtend.a })");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Object meth() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationWithIntArrayAndComputation() throws Exception {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class TestXtend {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val static int a = 4");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Click(#[ a, a.bitwiseAnd(3) << 1 ])");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def meth() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("annotation Click {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int[] value");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class TestXtend {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private static final int a = 4;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Click({ TestXtend.a, ((TestXtend.a & 3) << 1) })");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Object meth() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testInnerEnum() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("annotation A {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("enum B { C }");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("B b");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public enum B {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("C;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public A.B b();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testInterfaceExtendsAnnotation() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.lang.annotation.Annotation");
    _builder.newLine();
    _builder.append("annotation MyAnno {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static interface MyIf extends MyAnno {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static class MyClass implements MyIf {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("override Class<? extends Annotation> annotationType() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static class MyClass2 implements MyAnno {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("override Class<? extends Annotation> annotationType() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return null;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.lang.annotation.Annotation;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public @interface MyAnno {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface MyIf extends MyAnno {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static class MyClass implements MyAnno.MyIf {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public Class<? extends Annotation> annotationType() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static class MyClass2 implements MyAnno {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public Class<? extends Annotation> annotationType() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
