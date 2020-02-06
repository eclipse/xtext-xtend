/**
 * Copyright (c) 2013, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.java8.compiler;

import org.eclipse.xtend.core.tests.compiler.AbstractXtendCompilerTest;
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
public class CompilerBug421999Test extends AbstractXtendCompilerTest {
  @Test
  public void test_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(java.util.List<String> list) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return list.sortBy(e|e)");
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
    _builder_1.append("public List<String> m(final List<String> list) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, String> _function = (String e) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return e;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.<String, String>sortBy(list, _function);");
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
    _builder.append("def java.util.List<String> m(java.util.List<String> list) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return list.sortBy(e|e)");
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
    _builder_1.append("public List<String> m(final List<String> list) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, String> _function = (String e) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return e;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.<String, String>sortBy(list, _function);");
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
    _builder.append("def m(java.util.List<String> list) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.sortBy(e|e)");
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
    _builder_1.append("public List<String> m(final List<String> list) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, String> _function = (String e) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return e;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.<String, String>sortBy(list, _function);");
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
    _builder.append("def java.util.List<String> m(java.util.List<String> list) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.sortBy(e|e)");
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
    _builder_1.append("public List<String> m(final List<String> list) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, String> _function = (String e) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return e;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.<String, String>sortBy(list, _function);");
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
    _builder.append("import org.eclipse.emf.ecore.EClass");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.ecore.EObject");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.ecore.resource.ResourceSet");
    _builder.newLine();
    _builder.append("import org.eclipse.xtext.EcoreUtil2");
    _builder.newLine();
    _builder.append("import org.eclipse.xtext.naming.QualifiedName");
    _builder.newLine();
    _builder.append("import org.eclipse.xtext.resource.IEObjectDescription");
    _builder.newLine();
    _builder.append("import org.eclipse.xtext.xbase.lib.Pair");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Dummy {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def private <T extends EObject> T resolve(IEObjectDescription it, ResourceSet resourceSet) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <T extends EObject> Iterable<Pair<QualifiedName, T>> getVisibleIEObjectDescriptionByTypeAndCondition(");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("ResourceSet resourceSet,");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("EClass type,");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("(IEObjectDescription)=>boolean p) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val c = <IEObjectDescription>newArrayList");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("c.map[eod|");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("eod.qualifiedName -> resolve(eod, resourceSet)]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.ArrayList;");
    _builder_1.newLine();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.emf.ecore.EClass;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.emf.ecore.EObject;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.emf.ecore.resource.ResourceSet;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.naming.QualifiedName;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.resource.IEObjectDescription;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.ListExtensions;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Pair;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Dummy {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private <T extends EObject> T resolve(final IEObjectDescription it, final ResourceSet resourceSet) {");
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
    _builder_1.append("public <T extends EObject> Iterable<Pair<QualifiedName, T>> getVisibleIEObjectDescriptionByTypeAndCondition(final ResourceSet resourceSet, final EClass type, final Function1<? super IEObjectDescription, ? extends Boolean> p) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("List<Pair<QualifiedName, T>> _xblockexpression = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("{");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final ArrayList<IEObjectDescription> c = CollectionLiterals.<IEObjectDescription>newArrayList();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final Function1<IEObjectDescription, Pair<QualifiedName, T>> _function = (IEObjectDescription eod) -> {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("QualifiedName _qualifiedName = eod.getQualifiedName();");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("T _resolve = this.<T>resolve(eod, resourceSet);");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return Pair.<QualifiedName, T>of(_qualifiedName, _resolve);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_xblockexpression = ListExtensions.<IEObjectDescription, Pair<QualifiedName, T>>map(c, _function);");
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
}
