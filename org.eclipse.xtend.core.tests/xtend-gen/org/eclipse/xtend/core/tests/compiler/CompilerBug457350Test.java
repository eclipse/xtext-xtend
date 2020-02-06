/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
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
 * @author Miro Spoenemann - Initial contribution and API
 */
@SuppressWarnings("all")
public class CompilerBug457350Test extends AbstractXtendCompilerTest {
  @Test
  public void testBug457350_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val Runnable r1 = [");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("new Runnable {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("override run() {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("self.run");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("this.run");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void test() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Runnable _function = new Runnable() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final Runnable _self = this;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public void run() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("new Runnable() {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("public void run() {");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("_self.run();");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("this.run();");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Runnable r1 = _function;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug457350_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("[val Runnable r1 = [");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("new Runnable {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("override run() {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("self.run");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("this.run");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]].apply(null)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void test() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Procedure1<Object> _function = new Procedure1<Object>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public void apply(final Object it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final Runnable _function = new Runnable() {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("final Runnable _self = this;");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("public void run() {");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("new Runnable() {");
    _builder_1.newLine();
    _builder_1.append("              ");
    _builder_1.append("public void run() {");
    _builder_1.newLine();
    _builder_1.append("                ");
    _builder_1.append("_self.run();");
    _builder_1.newLine();
    _builder_1.append("                ");
    _builder_1.append("this.run();");
    _builder_1.newLine();
    _builder_1.append("              ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final Runnable r1 = _function;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("_function.apply(null);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug457350_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new Runnable {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("override run() {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("val Runnable r1 = [");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("self.run");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("this.run");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
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
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void test() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("new Runnable() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final Runnable _this = this;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public void run() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final Runnable _function = new Runnable() {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("public void run() {");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("this.run();");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("_this.run();");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final Runnable r1 = _function;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug457350_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new Runnable {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("override run() {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("val Runnable r1 = [");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("self.run");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("this.run");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("def blub() {}");
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
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void test() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("abstract class __Test_1 implements Runnable {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final __Test_1 _this__Test_1 = this;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public abstract Object blub();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("new __Test_1() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public void run() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final Runnable _function = new Runnable() {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("public void run() {");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("this.run();");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("_this__Test_1.run();");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final Runnable r1 = _function;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Object blub() {");
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
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug457350_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("[new Runnable {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("override run() {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("val Runnable r1 = [");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("self.run");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("this.run");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}].apply(null)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void test() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<Object, Runnable> _function = new Function1<Object, Runnable>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Runnable apply(final Object it) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return new Runnable() {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("final Runnable _this = this;");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("public void run() {");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("final Runnable _function = new Runnable() {");
    _builder_1.newLine();
    _builder_1.append("              ");
    _builder_1.append("public void run() {");
    _builder_1.newLine();
    _builder_1.append("                ");
    _builder_1.append("this.run();");
    _builder_1.newLine();
    _builder_1.append("                ");
    _builder_1.append("_this.run();");
    _builder_1.newLine();
    _builder_1.append("              ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("final Runnable r1 = _function;");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("_function.apply(null);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug457350_06() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Test extends SuperFoo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val Runnable r1 = [");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("new Runnable {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("override run() {");
    _builder.newLine();
    _builder.append("\t\t\t\t\t");
    _builder.append("Test.super.bar");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class SuperFoo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void bar() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test extends SuperFoo {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void test() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Runnable _function = new Runnable() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public void run() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("new Runnable() {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("public void run() {");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("Test.super.bar();");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Runnable r1 = _function;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
