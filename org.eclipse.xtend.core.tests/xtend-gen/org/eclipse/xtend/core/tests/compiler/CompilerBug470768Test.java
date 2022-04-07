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
public class CompilerBug470768Test extends AbstractXtendCompilerTest {
  @Test
  public void test_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class SOE {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def dispatch getList(A it) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new CustomItr().toList;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def dispatch getList(Object it) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("emptyList;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static class A {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static class CustomItr implements Iterable<A> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("override iterator() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("throw new UnsupportedOperationException()");
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
    _builder_1.append("import java.util.Arrays;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Iterator;");
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
    _builder_1.append("public class SOE {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static class CustomItr implements Iterable<SOE.A> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public Iterator<SOE.A> iterator() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("throw new UnsupportedOperationException();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("protected List<SOE.A> _getList(final SOE.A it) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.<SOE.A>toList(new SOE.CustomItr());");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("protected List<SOE.A> _getList(final Object it) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return CollectionLiterals.<SOE.A>emptyList();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<SOE.A> getList(final Object it) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (it instanceof SOE.A) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return _getList((SOE.A)it);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} else if (it != null) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return _getList(it);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} else {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("throw new IllegalArgumentException(\"Unhandled parameter types: \" +");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("Arrays.<Object>asList(it).toString());");
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
  
  @Test
  public void test_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def dispatch getList(C it) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new CustomItr().toList;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def dispatch getList(Object it) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("<C>emptyList");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static class CustomItr implements Iterable<C> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("override iterator() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("null");
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
    _builder_1.append("import java.util.Arrays;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Iterator;");
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
    _builder_1.append("public static class CustomItr implements Iterable<C> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public Iterator<C> iterator() {");
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
    _builder_1.append("protected List<C> _getList(final C it) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.<C>toList(new C.CustomItr());");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("protected List<C> _getList(final Object it) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return CollectionLiterals.<C>emptyList();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<C> getList(final Object it) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (it instanceof C) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return _getList((C)it);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} else if (it != null) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return _getList(it);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} else {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("throw new IllegalArgumentException(\"Unhandled parameter types: \" +");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("Arrays.<Object>asList(it).toString());");
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
  
  @Test
  public void test_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class SOE {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def dispatch getList(Object it) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("emptyList");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def dispatch getList(A it) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new CustomItr().<A>toList;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static class A {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static class CustomItr implements Iterable<A> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("override iterator() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("null");
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
    _builder_1.append("import java.util.Arrays;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Iterator;");
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
    _builder_1.append("public class SOE {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static class CustomItr implements Iterable<SOE.A> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public Iterator<SOE.A> iterator() {");
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
    _builder_1.append("protected List<SOE.A> _getList(final Object it) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return CollectionLiterals.<SOE.A>emptyList();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("protected List<SOE.A> _getList(final SOE.A it) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.<SOE.A>toList(new SOE.CustomItr());");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<SOE.A> getList(final Object it) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (it instanceof SOE.A) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return _getList((SOE.A)it);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} else if (it != null) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return _getList(it);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} else {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("throw new IllegalArgumentException(\"Unhandled parameter types: \" +");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("Arrays.<Object>asList(it).toString());");
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
  
  @Test
  public void test_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class SOE {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def dispatch getList(A it) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new CustomItr().<A>toList;");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def dispatch getList(Object it) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("emptyList");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static class A {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static class CustomItr implements Iterable<A> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("override iterator() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("null");
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
    _builder_1.append("import java.util.Arrays;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Iterator;");
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
    _builder_1.append("public class SOE {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static class A {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static class CustomItr implements Iterable<SOE.A> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public Iterator<SOE.A> iterator() {");
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
    _builder_1.append("protected List<SOE.A> _getList(final SOE.A it) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.<SOE.A>toList(new SOE.CustomItr());");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("protected List<SOE.A> _getList(final Object it) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return CollectionLiterals.<SOE.A>emptyList();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public List<SOE.A> getList(final Object it) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (it instanceof SOE.A) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return _getList((SOE.A)it);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} else if (it != null) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return _getList(it);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} else {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("throw new IllegalArgumentException(\"Unhandled parameter types: \" +");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("Arrays.<Object>asList(it).toString());");
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
