/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.tests.java8.compiler;

import org.eclipse.xtend.core.tests.java8.Java8RuntimeInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * @author Miro Spoenemann - Copied and adapted to Java 8 output
 */
@InjectWith(Java8RuntimeInjectorProvider.class)
@SuppressWarnings("all")
public class CompilerBug406425Test extends org.eclipse.xtend.core.tests.compiler.CompilerBug406425Test {
  @Test
  @Override
  public void testBug406425_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static org.hamcrest.core.Is.*");
    _builder.newLine();
    _builder.append("import static org.junit.Assert.*");
    _builder.newLine();
    _builder.append("import org.hamcrest.Matcher");
    _builder.newLine();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("new MyEntity => [");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("assertThat(it, nullValue)");
    _builder.newLine();
    _builder.append("           ");
    _builder.append("assertThat(it.name, is(\"\"))");
    _builder.newLine();
    _builder.append("       ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("def static <T> Matcher<T> nullValue() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class MyEntity {");
    _builder.newLine();
    _builder.append("   ");
    _builder.append("@Property String name");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.ObjectExtensions;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;");
    _builder_1.newLine();
    _builder_1.append("import org.hamcrest.Matcher;");
    _builder_1.newLine();
    _builder_1.append("import org.hamcrest.core.Is;");
    _builder_1.newLine();
    _builder_1.append("import org.junit.Assert;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public MyEntity test() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("MyEntity _myEntity = new MyEntity();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Procedure1<MyEntity> _function = (MyEntity it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Assert.<MyEntity>assertThat(it, Test.<MyEntity>nullValue());");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Assert.<String>assertThat(it.getName(), Is.<String>is(\"\"));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return ObjectExtensions.<MyEntity>operator_doubleArrow(_myEntity, _function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <T extends Object> Matcher<T> nullValue() {");
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
  @Override
  public void testBug406425_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test<T> {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def m(T a, T b){}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("static def m() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("new StringBuilder => [");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("new Test().m(it, new Long(0))");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("println(length)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.io.Serializable;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.InputOutput;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.ObjectExtensions;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Object m(final T a, final T b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static StringBuilder m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("StringBuilder _stringBuilder = new StringBuilder();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Procedure1<StringBuilder> _function = (StringBuilder it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Test<Serializable> _test = new Test<Serializable>();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Long _long = new Long(0);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_test.m(it, _long);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("InputOutput.<Integer>println(Integer.valueOf(it.length()));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return ObjectExtensions.<StringBuilder>operator_doubleArrow(_stringBuilder, _function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  @Override
  public void testBug406425_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def <T> m(T a, T b){}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("new StringBuilder => [");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("m(it, new Long(0))");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("println(length)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.io.Serializable;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.InputOutput;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.ObjectExtensions;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <T extends Object> Object m(final T a, final T b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public StringBuilder m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("StringBuilder _stringBuilder = new StringBuilder();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Procedure1<StringBuilder> _function = (StringBuilder it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Long _long = new Long(0);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("this.<Serializable>m(it, _long);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("InputOutput.<Integer>println(Integer.valueOf(it.length()));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return ObjectExtensions.<StringBuilder>operator_doubleArrow(_stringBuilder, _function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  @Override
  public void testBug406425_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def <T> m(T a, T b){}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("new StringBuilder => [");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("it.m(new Long(0))");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("println(length)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.io.Serializable;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.InputOutput;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.ObjectExtensions;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <T extends Object> Object m(final T a, final T b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public StringBuilder m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("StringBuilder _stringBuilder = new StringBuilder();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Procedure1<StringBuilder> _function = (StringBuilder it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Long _long = new Long(0);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("this.<Serializable>m(it, _long);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("InputOutput.<Integer>println(Integer.valueOf(it.length()));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return ObjectExtensions.<StringBuilder>operator_doubleArrow(_stringBuilder, _function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  @Override
  public void testBug406425_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def <T> m(T a, T b){}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("new StringBuilder => [");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("m(new Long(0))");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("println(length)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.io.Serializable;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.InputOutput;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.ObjectExtensions;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <T extends Object> Object m(final T a, final T b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public StringBuilder m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("StringBuilder _stringBuilder = new StringBuilder();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Procedure1<StringBuilder> _function = (StringBuilder it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Long _long = new Long(0);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("this.<Serializable>m(it, _long);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("InputOutput.<Integer>println(Integer.valueOf(it.length()));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return ObjectExtensions.<StringBuilder>operator_doubleArrow(_stringBuilder, _function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  @Override
  public void testBug406425_06() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def <T> m(T a, T b){}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("newArrayList(new StringBuilder).forEach [");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("m(it, new Long(0))");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("println(length)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.io.Serializable;");
    _builder_1.newLine();
    _builder_1.append("import java.util.function.Consumer;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.InputOutput;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <T extends Object> Object m(final T a, final T b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("StringBuilder _stringBuilder = new StringBuilder();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Consumer<StringBuilder> _function = (StringBuilder it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Long _long = new Long(0);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("this.<Serializable>m(it, _long);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("InputOutput.<Integer>println(Integer.valueOf(it.length()));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("CollectionLiterals.<StringBuilder>newArrayList(_stringBuilder).forEach(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  @Override
  public void testBug406425_07() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def static <T> m(T a, T b){}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("new StringBuilder => [");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("m(it, new Long(0))");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("println(length)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.io.Serializable;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.InputOutput;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.ObjectExtensions;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <T extends Object> Object m(final T a, final T b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public StringBuilder m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("StringBuilder _stringBuilder = new StringBuilder();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Procedure1<StringBuilder> _function = (StringBuilder it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Long _long = new Long(0);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Test.<Serializable>m(it, _long);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("InputOutput.<Integer>println(Integer.valueOf(it.length()));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return ObjectExtensions.<StringBuilder>operator_doubleArrow(_stringBuilder, _function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  @Override
  public void testBug406425_08() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def static <T> m(T a, T b){}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def static m() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("new StringBuilder => [");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("m(it, new Long(0))");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("println(length)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.io.Serializable;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.InputOutput;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.ObjectExtensions;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <T extends Object> Object m(final T a, final T b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static StringBuilder m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("StringBuilder _stringBuilder = new StringBuilder();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Procedure1<StringBuilder> _function = (StringBuilder it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Long _long = new Long(0);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Test.<Serializable>m(it, _long);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("InputOutput.<Integer>println(Integer.valueOf(it.length()));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return ObjectExtensions.<StringBuilder>operator_doubleArrow(_stringBuilder, _function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  @Override
  public void testBug406425_09() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def <T> m(T a, T b){}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("static def m() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("newArrayList(new StringBuilder).forEach [");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("new Test().m(it, new Long(0))");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("println(length)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.io.Serializable;");
    _builder_1.newLine();
    _builder_1.append("import java.util.function.Consumer;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.InputOutput;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <T extends Object> Object m(final T a, final T b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("StringBuilder _stringBuilder = new StringBuilder();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Consumer<StringBuilder> _function = (StringBuilder it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Test _test = new Test();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Long _long = new Long(0);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_test.<Serializable>m(it, _long);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("InputOutput.<Integer>println(Integer.valueOf(it.length()));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("CollectionLiterals.<StringBuilder>newArrayList(_stringBuilder).forEach(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  @Override
  public void testBug406425_10() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test<T> {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("def m(T a, T b){}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("static def m() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("newArrayList(new StringBuilder).forEach [");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("new Test().m(it, new Long(0))");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("println(length)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.io.Serializable;");
    _builder_1.newLine();
    _builder_1.append("import java.util.function.Consumer;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.InputOutput;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Object m(final T a, final T b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("StringBuilder _stringBuilder = new StringBuilder();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Consumer<StringBuilder> _function = (StringBuilder it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Test<Serializable> _test = new Test<Serializable>();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Long _long = new Long(0);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_test.m(it, _long);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("InputOutput.<Integer>println(Integer.valueOf(it.length()));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("CollectionLiterals.<StringBuilder>newArrayList(_stringBuilder).forEach(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  @Override
  public void testBug406425_11() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("static def <T> m(T a, T b){}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("    ");
    _builder.append("static def m() {");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("newArrayList(new StringBuilder).forEach [");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("m(it, new Long(0))");
    _builder.newLine();
    _builder.append("            ");
    _builder.append("println(length)");
    _builder.newLine();
    _builder.append("        ");
    _builder.append("]");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.io.Serializable;");
    _builder_1.newLine();
    _builder_1.append("import java.util.function.Consumer;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.InputOutput;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <T extends Object> Object m(final T a, final T b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("StringBuilder _stringBuilder = new StringBuilder();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Consumer<StringBuilder> _function = (StringBuilder it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Long _long = new Long(0);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Test.<Serializable>m(it, _long);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("InputOutput.<Integer>println(Integer.valueOf(it.length()));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("CollectionLiterals.<StringBuilder>newArrayList(_stringBuilder).forEach(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
