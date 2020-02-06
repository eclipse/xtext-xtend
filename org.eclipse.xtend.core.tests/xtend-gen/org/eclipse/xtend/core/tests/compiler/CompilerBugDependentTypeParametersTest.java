/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
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
public class CompilerBugDependentTypeParametersTest extends AbstractXtendCompilerTest {
  @Test
  public void test_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <I extends Iterable<?>> nonEmpty(I i) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("i");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(java.util.Collection<String> c) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("c.nonEmpty");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Collection;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <I extends Iterable<?>> I nonEmpty(final I i) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return i;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Collection<String> m(final Collection<String> c) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.<Collection<String>>nonEmpty(c);");
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
    _builder.append("def <E, I extends Iterable<E>> nonEmpty(I i) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("i");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(java.util.Collection<String> c) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("c.nonEmpty");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Collection;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <E extends Object, I extends Iterable<E>> I nonEmpty(final I i) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return i;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Collection<String> m(final Collection<String> c) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.<String, Collection<String>>nonEmpty(c);");
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
    _builder.append("def <K, V, M extends java.util.Map<K, V>> nonEmpty(M m) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(java.util.Map<String, Integer> m) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m.nonEmpty");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Map;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <K extends Object, V extends Object, M extends Map<K, V>> M nonEmpty(final M m) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return m;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Map<String, Integer> m(final Map<String, Integer> m) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.<String, Integer, Map<String, Integer>>nonEmpty(m);");
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
    _builder.append("def <K, V extends K, M extends java.util.Map<K, V>> nonEmpty(M m) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val m = newHashMap");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m.put(\'\', null)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m.nonEmpty");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.HashMap;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Map;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <K extends Object, V extends K, M extends Map<K, V>> M nonEmpty(final M m) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return m;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public HashMap<String, String> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("HashMap<String, String> _xblockexpression = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("{");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final HashMap<String, String> m = CollectionLiterals.<String, String>newHashMap();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("m.put(\"\", null);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_xblockexpression = this.<String, String, HashMap<String, String>>nonEmpty(m);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return _xblockexpression;");
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
    _builder.append("def <K, V extends K, M extends java.util.Map<K, V>> nonEmpty(M m) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val m = newHashMap");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m.put(null, \'\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m.nonEmpty");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.HashMap;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Map;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <K extends Object, V extends K, M extends Map<K, V>> M nonEmpty(final M m) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return m;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public HashMap<String, String> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("HashMap<String, String> _xblockexpression = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("{");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final HashMap<String, String> m = CollectionLiterals.<String, String>newHashMap();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("m.put(null, \"\");");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_xblockexpression = this.<String, String, HashMap<String, String>>nonEmpty(m);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return _xblockexpression;");
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
    _builder.append("def <K, V extends K, M extends java.util.Map<? extends K, ? extends V>> nonEmpty(M m) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val m = newHashMap");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m.put(null, \'\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("m.nonEmpty");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.HashMap;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Map;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <K extends Object, V extends K, M extends Map<? extends K, ? extends V>> M nonEmpty(final M m) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return m;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public HashMap<String, String> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("HashMap<String, String> _xblockexpression = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("{");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final HashMap<String, String> m = CollectionLiterals.<String, String>newHashMap();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("m.put(null, \"\");");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_xblockexpression = this.<String, String, HashMap<String, String>>nonEmpty(m);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return _xblockexpression;");
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
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <E, I extends Iterable<? extends E>> nonEmpty(I i) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("i");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(java.util.Collection<? extends String> c) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("c.nonEmpty");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Collection;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <E extends Object, I extends Iterable<? extends E>> I nonEmpty(final I i) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return i;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Collection<? extends String> m(final Collection<? extends String> c) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.<String, Collection<? extends String>>nonEmpty(c);");
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
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <E, I extends Iterable<? extends E>> nonEmpty(I i) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("i");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(java.util.Collection<? super String> c) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("c.nonEmpty");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Collection;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <E extends Object, I extends Iterable<? extends E>> I nonEmpty(final I i) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return i;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Collection<? super String> m(final Collection<? super String> c) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.<Object, Collection<? super String>>nonEmpty(c);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_09() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <E, I extends Iterable<E>> nonEmpty(I i) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("i");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def nonEmpty(String s) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("s");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(java.util.List<Iterable<?>> o) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("nonEmpty(o)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <E extends Object, I extends Iterable<E>> I nonEmpty(final I i) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return i;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String nonEmpty(final String s) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return s;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<Iterable<?>> m(final List<Iterable<?>> o) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.<Iterable<?>, List<Iterable<?>>>nonEmpty(o);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_10() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <E, I extends Iterable<E>> nonEmpty(I i) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("i");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def nonEmpty(String s) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("s");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <T> m(Iterable<? extends T> o) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("nonEmpty(o)");
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
    _builder_1.append("public <E extends Object, I extends Iterable<E>> I nonEmpty(final I i) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return i;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String nonEmpty(final String s) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return s;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <T extends Object> Iterable<? extends T> m(final Iterable<? extends T> o) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.nonEmpty(o);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_11() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <I extends Iterable<?>> nonEmpty(I i) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("i");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(Object[] o) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("(o as java.util.List<Object>).nonEmpty");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Conversions;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <I extends Iterable<?>> I nonEmpty(final I i) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return i;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<Object> m(final Object[] o) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.<List<Object>>nonEmpty(((List<Object>) Conversions.doWrapArray(o)));");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_12() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <I extends Iterable<?>> nonEmpty(I i) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("i");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(CharSequence[] o) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("(o as java.util.Collection<String>).nonEmpty");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Collection;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Conversions;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <I extends Iterable<?>> I nonEmpty(final I i) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return i;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Collection<String> m(final CharSequence[] o) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.<Collection<String>>nonEmpty(((Collection<String>) Conversions.doWrapArray(o)));");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
