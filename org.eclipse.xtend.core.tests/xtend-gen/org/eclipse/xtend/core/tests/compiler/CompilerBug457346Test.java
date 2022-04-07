/**
 * Copyright (c) 2015, 2016 itemis AG (http://www.itemis.eu) and others.
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
public class CompilerBug457346Test extends AbstractXtendCompilerTest {
  @Test
  public void test_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.google.common.collect.Ordering");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("#[\'a\', \'aa\', \'aaa\'].sortWith(Ordering.natural.onResultOf [ String s| s.length ].onResultOf[])");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Function;");
    _builder_1.newLine();
    _builder_1.append("import com.google.common.collect.Ordering;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Collections;");
    _builder_1.newLine();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.IterableExtensions;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<String> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function<String, Integer> _function = new Function<String, Integer>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Integer apply(final String s) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return Integer.valueOf(s.length());");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function<String, String> _function_1 = new Function<String, String>() {");
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
    _builder_1.append("return IterableExtensions.<String>sortWith(Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(\"a\", \"aa\", \"aaa\")), Ordering.<Integer>natural().<String>onResultOf(_function).<String>onResultOf(_function_1));");
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
    _builder.append("import com.google.common.collect.Ordering");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(Sortable<String> sortMe) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("sortMe.sort(Ordering.natural.onResultOf [ String s| s.length ].onResultOf[])");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface Sortable<T> extends Iterable<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def void sort(java.util.Comparator<? super T> c)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Function;");
    _builder_1.newLine();
    _builder_1.append("import com.google.common.collect.Ordering;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Comparator;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface Sortable<T extends Object> extends Iterable<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("void sort(final Comparator<? super T> c);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m(final C.Sortable<String> sortMe) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function<String, Integer> _function = new Function<String, Integer>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Integer apply(final String s) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return Integer.valueOf(s.length());");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function<String, String> _function_1 = new Function<String, String>() {");
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
    _builder_1.append("sortMe.sort(Ordering.<Integer>natural().<String>onResultOf(_function).<String>onResultOf(_function_1));");
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
    _builder.append("import java.util.Comparator");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(Sortable<String> sortMe) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("sortMe.sort(MyComparator.comparingInt2 [String s| s.length].thenComparing2[String s | s])");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface Sortable<T> extends Iterable<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def void sort(java.util.Comparator<? super T> c)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static abstract class MyComparator<T> implements Comparator<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def static <T> MyComparator<T> comparingInt2(ToIntFunction<? super T> fun) {}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def <U extends Comparable<? super U>> MyComparator<T> thenComparing2((T)=>U f) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface ToIntFunction<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def int apply(T t)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Comparator;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface Sortable<T extends Object> extends Iterable<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("void sort(final Comparator<? super T> c);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static abstract class MyComparator<T extends Object> implements Comparator<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public static <T extends Object> C.MyComparator<T> comparingInt2(final C.ToIntFunction<? super T> fun) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public <U extends Comparable<? super U>> C.MyComparator<T> thenComparing2(final Function1<? super T, ? extends U> f) {");
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
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface ToIntFunction<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("int apply(final T t);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m(final C.Sortable<String> sortMe) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C.ToIntFunction<String> _function = new C.ToIntFunction<String>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public int apply(final String s) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return s.length();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, String> _function_1 = new Function1<String, String>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public String apply(final String s) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return s;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("sortMe.sort(C.MyComparator.<String>comparingInt2(_function).<String>thenComparing2(_function_1));");
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
    _builder.append("import java.util.Comparator");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(Sortable<String> sortMe) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("sortMe.sort(MyComparator.comparingInt2 [String s| s.length].thenComparing2[toUpperCase])");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface Sortable<T> extends Iterable<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def void sort(java.util.Comparator<? super T> c)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static abstract class MyComparator<T> implements Comparator<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def static <T> MyComparator<T> comparingInt2(ToIntFunction<? super T> fun) {}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def <U extends Comparable<? super U>> MyComparator<T> thenComparing2((T)=>U f) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface ToIntFunction<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def int apply(T t)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Comparator;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface Sortable<T extends Object> extends Iterable<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("void sort(final Comparator<? super T> c);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static abstract class MyComparator<T extends Object> implements Comparator<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public static <T extends Object> C.MyComparator<T> comparingInt2(final C.ToIntFunction<? super T> fun) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public <U extends Comparable<? super U>> C.MyComparator<T> thenComparing2(final Function1<? super T, ? extends U> f) {");
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
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface ToIntFunction<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("int apply(final T t);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m(final C.Sortable<String> sortMe) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C.ToIntFunction<String> _function = new C.ToIntFunction<String>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public int apply(final String s) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return s.length();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, String> _function_1 = new Function1<String, String>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public String apply(final String it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return it.toUpperCase();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("sortMe.sort(C.MyComparator.<String>comparingInt2(_function).<String>thenComparing2(_function_1));");
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
    _builder.append("import java.util.Comparator");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(Sortable<String> sortMe) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("sortMe.sort(MyComparator.comparingInt2 [String s| s.length].thenComparing2[it])");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface Sortable<T> extends Iterable<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def void sort(java.util.Comparator<? super T> c)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static abstract class MyComparator<T> implements Comparator<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def static <T> MyComparator<T> comparingInt2(ToIntFunction<? super T> fun) {}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def <U extends Comparable<? super U>> MyComparator<T> thenComparing2((T)=>U f) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface ToIntFunction<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def int apply(T t)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Comparator;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface Sortable<T extends Object> extends Iterable<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("void sort(final Comparator<? super T> c);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static abstract class MyComparator<T extends Object> implements Comparator<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public static <T extends Object> C.MyComparator<T> comparingInt2(final C.ToIntFunction<? super T> fun) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public <U extends Comparable<? super U>> C.MyComparator<T> thenComparing2(final Function1<? super T, ? extends U> f) {");
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
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface ToIntFunction<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("int apply(final T t);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m(final C.Sortable<String> sortMe) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C.ToIntFunction<String> _function = new C.ToIntFunction<String>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public int apply(final String s) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return s.length();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, String> _function_1 = new Function1<String, String>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public String apply(final String it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return it;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("sortMe.sort(C.MyComparator.<String>comparingInt2(_function).<String>thenComparing2(_function_1));");
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
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("MyComparator.comparingInt [String s| s.length].thenComparing[it]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static abstract class MyComparator<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def static <T> MyComparator<T> comparingInt(ToIntFunction<? super T> fun) {}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def <U extends Comparable<? super U>> MyComparator<T> thenComparing(");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Function<? super T, ? extends U> keyExtractor,");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("MyComparator<? super U> keyComparator)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def MyComparator<T> thenComparing(MyComparator<? super T> other)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def <U extends Comparable<? super U>> MyComparator<T> thenComparing(");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("Function<? super T, ? extends U> keyExtractor)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface ToIntFunction<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def int apply(T t)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface Function<T, R> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def R apply(T t)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static abstract class MyComparator<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public static <T extends Object> C.MyComparator<T> comparingInt(final C.ToIntFunction<? super T> fun) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public abstract <U extends Comparable<? super U>> C.MyComparator<T> thenComparing(final C.Function<? super T, ? extends U> keyExtractor, final C.MyComparator<? super U> keyComparator);");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public abstract C.MyComparator<T> thenComparing(final C.MyComparator<? super T> other);");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public abstract <U extends Comparable<? super U>> C.MyComparator<T> thenComparing(final C.Function<? super T, ? extends U> keyExtractor);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface ToIntFunction<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("int apply(final T t);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface Function<T extends Object, R extends Object> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("R apply(final T t);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public C.MyComparator<String> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C.ToIntFunction<String> _function = new C.ToIntFunction<String>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public int apply(final String s) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return s.length();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C.Function<String, String> _function_1 = new C.Function<String, String>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public String apply(final String it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return it;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return C.MyComparator.<String>comparingInt(_function).<String>thenComparing(_function_1);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
