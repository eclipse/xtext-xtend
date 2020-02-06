/**
 * Copyright (c) 2013, 2016 itemis AG (http://www.itemis.eu) and others.
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
 * @see CompilerBugTest#testBug404051_01 and friends
 */
@SuppressWarnings("all")
public class CompilerBug404051Test extends AbstractXtendCompilerTest {
  @Test
  public void test_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(Iterable<String> iterable) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("iterable.flatMap[].sortBy [ hashCode ]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <A,B> Iterable<? super B> flatMap(Iterable<? extends A> iterable, (A)=>B map) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.IterableExtensions;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<? super Object> m(final Iterable<String> iterable) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, Object> _function = new Function1<String, Object>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Object apply(final String it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<Object, Integer> _function_1 = new Function1<Object, Integer>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Integer apply(final Object it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return Integer.valueOf(it.hashCode());");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.sortBy(this.<String, Object>flatMap(iterable, _function), _function_1);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <A extends Object, B extends Object> Iterable<? super B> flatMap(final Iterable<? extends A> iterable, final Function1<? super A, ? extends B> map) {");
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
  public void test_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(Iterable<String> iterable) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("iterable.flatMap[].sortBy [ hashCode ]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <A,B extends Number> Iterable<? super B> flatMap(Iterable<? extends A> iterable, (A)=>B map) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.IterableExtensions;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<? super Number> m(final Iterable<String> iterable) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, Number> _function = new Function1<String, Number>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Number apply(final String it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<Object, Integer> _function_1 = new Function1<Object, Integer>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Integer apply(final Object it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return Integer.valueOf(it.hashCode());");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.sortBy(this.<String, Number>flatMap(iterable, _function), _function_1);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <A extends Object, B extends Number> Iterable<? super B> flatMap(final Iterable<? extends A> iterable, final Function1<? super A, ? extends B> map) {");
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
  public void test_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(Iterable<String> iterable) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("iterable.flatMap[].sortBy [ intValue ]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <A,B extends Number> Iterable<? extends B> flatMap(Iterable<? extends A> iterable, (A)=>B map) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.IterableExtensions;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<? extends Number> m(final Iterable<String> iterable) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, Number> _function = new Function1<String, Number>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Number apply(final String it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<Number, Integer> _function_1 = new Function1<Number, Integer>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Integer apply(final Number it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return Integer.valueOf(it.intValue());");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.sortBy(this.<String, Number>flatMap(iterable, _function), _function_1);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <A extends Object, B extends Number> Iterable<? extends B> flatMap(final Iterable<? extends A> iterable, final Function1<? super A, ? extends B> map) {");
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
  public void test_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#[].flatMap[ length ].sortBy [ intValue ]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <A extends String,B extends Number> Iterable<? extends B> flatMap(Iterable<? extends A> iterable, (A)=>B map) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Collections;");
    _builder_1.newLine();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.IterableExtensions;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<? extends Integer> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, Integer> _function = new Function1<String, Integer>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Integer apply(final String it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return Integer.valueOf(it.length());");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<Integer, Integer> _function_1 = new Function1<Integer, Integer>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Integer apply(final Integer it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return Integer.valueOf(it.intValue());");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.sortBy(this.<String, Integer>flatMap(Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList()), _function), _function_1);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <A extends String, B extends Number> Iterable<? extends B> flatMap(final Iterable<? extends A> iterable, final Function1<? super A, ? extends B> map) {");
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
  public void test_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#[].flatMap[].sortBy [ intValue ]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <A extends String,B extends Number> Iterable<? extends B> flatMap(Iterable<? extends A> iterable, (A)=>B map) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Collections;");
    _builder_1.newLine();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.IterableExtensions;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<? extends Number> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, Number> _function = new Function1<String, Number>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Number apply(final String it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<Number, Integer> _function_1 = new Function1<Number, Integer>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Integer apply(final Number it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return Integer.valueOf(it.intValue());");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.sortBy(this.<String, Number>flatMap(Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList()), _function), _function_1);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <A extends String, B extends Number> Iterable<? extends B> flatMap(final Iterable<? extends A> iterable, final Function1<? super A, ? extends B> map) {");
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
  public void test_06() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(Iterable<String> iterable) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("iterable.flatMap[].sortBy [ length ]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <A,B extends A> Iterable<? extends B> flatMap(Iterable<? extends A> iterable, (A)=>B map) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.IterableExtensions;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<? extends String> m(final Iterable<String> iterable) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, String> _function = new Function1<String, String>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public String apply(final String it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, Integer> _function_1 = new Function1<String, Integer>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Integer apply(final String it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return Integer.valueOf(it.length());");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.sortBy(this.<String, String>flatMap(iterable, _function), _function_1);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <A extends Object, B extends A> Iterable<? extends B> flatMap(final Iterable<? extends A> iterable, final Function1<? super A, ? extends B> map) {");
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
}
