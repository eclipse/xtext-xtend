/**
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.tests.compiler;

import com.google.inject.Inject;
import org.eclipse.xtend.core.tests.compiler.AbstractXtendCompilerTest;
import org.eclipse.xtend.core.xtend.XtendFile;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.validation.IssueCodes;
import org.junit.Test;

/**
 * @author Eva Poell - Initial contribution and API
 */
@SuppressWarnings("all")
public class TryWithResourcesTest extends AbstractXtendCompilerTest {
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Inject
  @Extension
  private ParseHelper<XtendFile> _parseHelper;
  
  @Test
  public void test_noIssues() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("try (val a = new StringReader(s); ");
      _builder.newLine();
      _builder.append(") ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("a.read");
      _builder.newLine();
      this._validationTestHelper.assertNoIssues(this._parseHelper.parse(this.buildXtendInput(_builder, true, false)));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void test_Error_nullResource() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("try (val a = new StringReader(s); ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("val b = \"\"");
      _builder.newLine();
      _builder.append(") ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("a.read");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(this.buildXtendInput(_builder, true, false)), XbasePackage.Literals.XVARIABLE_DECLARATION, 
        IssueCodes.INVALID_TRY_RESOURCE_TYPE, "implement java.lang.AutoCloseable");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void test_Error_NotAutoClosable() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("try (val a = #[1,2,3]) {}");
      _builder.newLine();
      this._validationTestHelper.assertError(this._parseHelper.parse(this.buildXtendInput(_builder, true, false)), XbasePackage.Literals.XVARIABLE_DECLARATION, 
        IssueCodes.INVALID_TRY_RESOURCE_TYPE, "implement java.lang.AutoCloseable");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void test_normalTryWithoutResource() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("val br = new BufferedReader(new StringReader(s));");
    _builder.newLine();
    _builder.append("try ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("br.readLine()");
    _builder.newLine();
    _builder.append("finally");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("if(br !== null)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("br.close()");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("StringReader _stringReader = new StringReader(this.s);");
    _builder_1.newLine();
    _builder_1.append("final BufferedReader br = new BufferedReader(_stringReader);");
    _builder_1.newLine();
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("br.readLine();");
    _builder_1.newLine();
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("if ((br != null)) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("br.close();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.test2(_builder, _builder_1);
  }
  
  @Test
  public void test_easyResource() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("try (val a = new StringReader(s))");
    _builder.newLine();
    _builder.append("a.read");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("StringReader a = null;");
    _builder_1.newLine();
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("a = new StringReader(this.s);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("a.read();");
    _builder_1.newLine();
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("if (a != null) a.close();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.test1(_builder, _builder_1);
  }
  
  @Test
  public void test_assumeTypeAutoClosable() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("val array = newArrayOfSize(2)");
    _builder.newLine();
    _builder.append("try(val someCloseable = array.get(0)) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("someCloseable.close");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("final AutoCloseable[] array = new AutoCloseable[2];");
    _builder_1.newLine();
    _builder_1.append("AutoCloseable someCloseable = null;");
    _builder_1.newLine();
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("someCloseable = array[0];");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("someCloseable.close();");
    _builder_1.newLine();
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("if (someCloseable != null) someCloseable.close();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.test0(_builder, _builder_1);
  }
  
  @Test
  public void test_automaticCloseButStillExceptionCatched() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("val reader1 = new StringReader(s)");
    _builder.newLine();
    _builder.append("val reader2 = new StringReader(s)");
    _builder.newLine();
    _builder.append("val array = #[reader1,reader2]");
    _builder.newLine();
    _builder.newLine();
    _builder.append("try (val AutoCloseable closable = array.get(0)){}");
    _builder.newLine();
    CharSequence _buildXtendInput = this.buildXtendInput(_builder, true, false);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package sample;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import java.io.StringReader;");
    _builder_1.newLine();
    _builder_1.append("import java.util.Collections;");
    _builder_1.newLine();
    _builder_1.append("import java.util.List;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Exceptions;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class FooClass {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final String s = \"line1\\nline2\\nline3\";");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void fooMethod() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final StringReader reader1 = new StringReader(this.s);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final StringReader reader2 = new StringReader(this.s);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final List<StringReader> array = Collections.<StringReader>unmodifiableList(CollectionLiterals.<StringReader>newArrayList(reader1, reader2));");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("AutoCloseable closable = null;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("closable = array.get(0);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("if (closable != null) closable.close();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} catch (Throwable _e) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("throw Exceptions.sneakyThrow(_e);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_buildXtendInput, _builder_1);
  }
  
  @Test
  public void test_noteSecretVariableNames() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("try (val a = new StringReader(s)){");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val x = 1+1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("a.read");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("try(val x = new StringReader(s)) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("x.close");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("StringReader a = null;");
    _builder_1.newLine();
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("a = new StringReader(this.s);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("final int x = (1 + 1);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("a.read();");
    _builder_1.newLine();
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("if (a != null) a.close();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("StringReader x = null;");
    _builder_1.newLine();
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("x = new StringReader(this.s);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("x.close();");
    _builder_1.newLine();
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("if (x != null) x.close();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.test1(_builder, _builder_1);
  }
  
  @Test
  public void test_twoResources() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("try (val sr = new StringReader(s); val buffy = new BufferedReader(sr)) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("buffy.read");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("StringReader sr = null;");
    _builder_1.newLine();
    _builder_1.append("BufferedReader buffy = null;");
    _builder_1.newLine();
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("sr = new StringReader(this.s);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("buffy = new BufferedReader(sr);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("buffy.read();");
    _builder_1.newLine();
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("if (buffy != null) buffy.close();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("if (sr != null) sr.close();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.test2(_builder, _builder_1);
  }
  
  @Test
  public void test_twoNestedResources() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("try (val br = new BufferedReader(new StringReader(s));) ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("br.readLine()");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("BufferedReader br = null;");
    _builder_1.newLine();
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("br = new BufferedReader(new StringReader(this.s));");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("br.readLine();");
    _builder_1.newLine();
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("if (br != null) br.close();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.test2(_builder, _builder_1);
  }
  
  @Test
  public void test_twoResourcesOneDefinedOutside() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("val sr = new StringReader(s)");
    _builder.newLine();
    _builder.append("try (val br = new BufferedReader(sr);) ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("br.readLine()");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("final StringReader sr = new StringReader(this.s);");
    _builder_1.newLine();
    _builder_1.append("BufferedReader br = null;");
    _builder_1.newLine();
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("br = new BufferedReader(sr);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("br.readLine();");
    _builder_1.newLine();
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("if (br != null) br.close();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.test2(_builder, _builder_1);
  }
  
  @Test
  public void test_nestedIf1() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("try (val fr = new StringReader(if (true) s+\"1\" else s+\"2\"); val br = new BufferedReader(fr)) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("br.read");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("StringReader fr = null;");
    _builder_1.newLine();
    _builder_1.append("BufferedReader br = null;");
    _builder_1.newLine();
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("fr = new Function0<StringReader>() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public StringReader apply() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("String _xifexpression = null;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("if (true) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("_xifexpression = (FooClass.this.s + \"1\");");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("} else {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("_xifexpression = (FooClass.this.s + \"2\");");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return new StringReader(_xifexpression);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}.apply();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("br = new BufferedReader(fr);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("br.read();");
    _builder_1.newLine();
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("if (br != null) br.close();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("if (fr != null) fr.close();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.test3(_builder, _builder_1);
  }
  
  @Test
  public void test_ResourceIOException() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package sample");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import java.io.File");
    _builder.newLine();
    _builder.append("import java.io.FileReader");
    _builder.newLine();
    _builder.append("import java.io.IOException");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class FooClass {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void fooMethod() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try (val a = new FileReader(new File(\"\\\\home/docs/text.txt\")))");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("a.read");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("catch(IOException e)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("e.fillInStackTrace");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package sample;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import java.io.File;");
    _builder_1.newLine();
    _builder_1.append("import java.io.FileReader;");
    _builder_1.newLine();
    _builder_1.append("import java.io.IOException;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Exceptions;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function0;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class FooClass {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void fooMethod() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("FileReader a = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("a = new Function0<FileReader>() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("public FileReader apply() {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("File _file = new File(\"\\\\home/docs/text.txt\");");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("return new FileReader(_file);");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("} catch (Throwable _e) {");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("throw Exceptions.sneakyThrow(_e);");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}.apply();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("a.read();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} catch (final Throwable _t) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("if (_t instanceof IOException) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final IOException e = (IOException)_t;");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("e.fillInStackTrace();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("} else {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("throw Exceptions.sneakyThrow(_t);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("if (a != null) a.close();");
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
  public void test_tryWithLambda01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("try (var r = [System.out.println(\"Closing\")]) {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("AutoCloseable r = null;");
    _builder_1.newLine();
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("r = new AutoCloseable() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public void close() throws Exception {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("System.out.println(\"Closing\");");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("if (r != null) r.close();");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.test0(_builder, _builder_1);
  }
  
  @Test
  public void test_tryWithLambda02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("val myList = newArrayList;");
    _builder.newLine();
    _builder.append("try (val someCloseable = [ myList.add(\'close\') ]) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("myList.add(\'body\')");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    CharSequence _buildXtendInput = this.buildXtendInput(_builder, false, false);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package sample;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import java.util.ArrayList;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Exceptions;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class FooClass {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final String s = \"line1\\nline2\\nline3\";");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void fooMethod() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final ArrayList<String> myList = CollectionLiterals.<String>newArrayList();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("AutoCloseable someCloseable = null;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("someCloseable = new AutoCloseable() {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("public void close() throws Exception {");
    _builder_1.newLine();
    _builder_1.append("            ");
    _builder_1.append("myList.add(\"close\");");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("myList.add(\"body\");");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("if (someCloseable != null) someCloseable.close();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} catch (Throwable _e) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("throw Exceptions.sneakyThrow(_e);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_buildXtendInput, _builder_1);
  }
  
  /**
   * Test does not need any imports besides 'Exceptions'
   */
  private void test0(final CharSequence input, final CharSequence expected) {
    this.assertCompilesTo(this.buildXtendInput(input, false, false), this.buildJavaOutput(expected, false, false, false));
  }
  
  /**
   * Test needs 'StringReader'
   */
  private void test1(final CharSequence input, final CharSequence expected) {
    this.assertCompilesTo(this.buildXtendInput(input, true, false), this.buildJavaOutput(expected, true, false, false));
  }
  
  /**
   * Test needs 'StringReader' and 'BufferedReader'
   */
  private void test2(final CharSequence input, final CharSequence expected) {
    this.assertCompilesTo(this.buildXtendInput(input, true, true), this.buildJavaOutput(expected, true, true, false));
  }
  
  /**
   * Test needs 'StringReader', 'BufferedReader' and 'Functions.Function0'
   */
  private void test3(final CharSequence input, final CharSequence expected) {
    this.assertCompilesTo(this.buildXtendInput(input, true, true), this.buildJavaOutput(expected, true, true, true));
  }
  
  private CharSequence buildXtendInput(final CharSequence input, final boolean needsStringReader, final boolean needsBufferedReader) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package sample");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.newLine();
    {
      if (needsBufferedReader) {
        _builder.append("import java.io.BufferedReader");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      if (needsStringReader) {
        _builder.append("import java.io.StringReader");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("class FooClass {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val s = \"line1\\nline2\\nline3\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void fooMethod() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(input, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  private CharSequence buildJavaOutput(final CharSequence expected, final boolean needsStringReader, final boolean needsBufferedReader, final boolean needsFunc0) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package sample;");
    _builder.newLine();
    _builder.newLine();
    {
      if (needsBufferedReader) {
        _builder.append("import java.io.BufferedReader;");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      if (needsStringReader) {
        _builder.append("import java.io.StringReader;");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("import org.eclipse.xtext.xbase.lib.Exceptions;");
    _builder.newLine();
    {
      if (needsFunc0) {
        _builder.append("import org.eclipse.xtext.xbase.lib.Functions.Function0;");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("@SuppressWarnings(\"all\")");
    _builder.newLine();
    _builder.append("public class FooClass {");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("private final String s = \"line1\\nline2\\nline3\";");
    _builder.newLine();
    _builder.append("  ");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("public void fooMethod() {");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append(expected, "      ");
    _builder.newLineIfNotEmpty();
    _builder.append("    ");
    _builder.append("} catch (Throwable _e) {");
    _builder.newLine();
    _builder.append("      ");
    _builder.append("throw Exceptions.sneakyThrow(_e);");
    _builder.newLine();
    _builder.append("    ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
}
