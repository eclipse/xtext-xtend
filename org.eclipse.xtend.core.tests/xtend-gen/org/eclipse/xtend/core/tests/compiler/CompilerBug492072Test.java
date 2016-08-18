/**
 * Copyright (c) 2016 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.tests.compiler;

import org.eclipse.xtend.core.tests.compiler.AbstractXtendCompilerTest;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Test;

/**
 * @author Holger Schill - Initial contribution and API
 */
@SuppressWarnings("all")
public class CompilerBug492072Test extends AbstractXtendCompilerTest {
  @Test
  public void testFeatureCallInAssignmentWithoutVariableDeclaration_0() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo(Bar a, Bar b) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a.name = b.name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def String getName(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void setName(String n){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("name = n");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Foo {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void foo(final Bar a, final Bar b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("a.setName(b.getName());");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testFeatureCallInAssignmentWithoutVariableDeclaration_1() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo(Bar a, Bar b) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a.name = b.name.toString");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def String getName(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void setName(String n){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("name = n");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Foo {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void foo(final Bar a, final Bar b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("a.setName(b.getName().toString());");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testFeatureCallInAssignmentWithVariableDeclarationWhenNullSafe_0() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo(Bar a, Bar b) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("a.name = b?.name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def String getName(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void setName(String n){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("name = n");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Foo {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void foo(final Bar a, final Bar b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("String _name = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (b!=null) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_name=b.getName();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("a.setName(_name);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testFeatureCallWithoutVariableDeclaration_0() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo(Bar a, Bar b) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("bar(a.name)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def bar(String n){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("n");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def String getName(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void setName(String n){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("name = n");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Foo {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String foo(final Bar a, final Bar b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.bar(a.getName());");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String bar(final String n) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return n;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testFeatureCallWithVariableDeclarationWhenNullSafe_0() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo(Bar a, Bar b) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("bar(a?.name)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def bar(String n){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("n");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def String getName(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void setName(String n){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("name = n");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Foo {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String foo(final Bar a, final Bar b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("String _name = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (a!=null) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_name=a.getName();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.bar(_name);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String bar(final String n) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return n;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
