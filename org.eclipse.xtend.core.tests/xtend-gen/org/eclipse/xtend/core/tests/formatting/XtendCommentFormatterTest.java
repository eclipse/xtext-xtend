/**
 * Copyright (c) 2012, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.formatting;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Ignore;
import org.junit.Test;

@SuppressWarnings("all")
public class XtendCommentFormatterTest extends AbstractXtendFormatterTest {
  @Test
  public void formatMLCommentBeforePackage() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/***********");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* copyright");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("***********/");
    _builder.newLine();
    _builder.append("package foo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class zonk {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("/***********");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("* copyright");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("***********/package foo");
    _builder_1.newLine();
    _builder_1.append("class zonk {}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatMLComment1() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/***********");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* copyright");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("***********/");
    _builder.newLine();
    _builder.append("class zonk {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("/***********");
    _builder_1.newLine();
    _builder_1.append("copyright");
    _builder_1.newLine();
    _builder_1.append("***********/");
    _builder_1.newLine();
    _builder_1.append("class zonk {}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatSLCommentAfterStatement() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo // my comment");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo // my comment");
    _builder_1.newLine();
    _builder_1.append("class bar{}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatSLCommentBeforeStatement1() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("// my comment");
    _builder.newLine();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo ");
    _builder_1.newLine();
    _builder_1.append("// my comment");
    _builder_1.newLine();
    _builder_1.append("class bar{}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatSLCommentBeforeStatement2() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import java.util.Map");
    _builder.newLine();
    _builder.newLine();
    _builder.append("// my comment");
    _builder.newLine();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo import java.util.Map ");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("// my comment");
    _builder_1.newLine();
    _builder_1.append("class bar{}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatMLCommentAfterStatement() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo /* my comment */");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo /* my comment */");
    _builder_1.newLine();
    _builder_1.append("class bar{}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatSLCommentInStatement() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package /* my comment */ foo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package  /* my comment */  foo");
    _builder_1.newLine();
    _builder_1.append("class bar{}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatMLCommentInStatement() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package");
    _builder.newLine();
    _builder.append("/* my ");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("comment */");
    _builder.newLine();
    _builder.append("foo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package  /* my ");
    _builder_1.newLine();
    _builder_1.append("comment */  foo");
    _builder_1.newLine();
    _builder_1.append("class bar{}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatMLCommentBeforeStatement1() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/* my comment */");
    _builder.newLine();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo ");
    _builder_1.newLine();
    _builder_1.append("/* my comment */");
    _builder_1.newLine();
    _builder_1.append("class bar{}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatMLCommentBeforeStatement2() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.Map");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/* my comment */");
    _builder.newLine();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.Map ");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("/* my comment */");
    _builder_1.newLine();
    _builder_1.append("class bar{}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatSLCommentAfterStatement2() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class bar { // my comment");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class bar{    // my comment");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatSLDocCommentInStatement21() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("// my comment");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class bar{");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("//    my comment");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatSLDocCommentInStatement22() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("//");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class bar{");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("//    ");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatSLCodeCommentInStatement21() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("//    my comment");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class bar{");
    _builder_1.newLine();
    _builder_1.append("//    my comment");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatMLCommentInBlock1() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* my comment");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class bar{");
    _builder_1.newLine();
    _builder_1.append("/*");
    _builder_1.newLine();
    _builder_1.append("my comment");
    _builder_1.newLine();
    _builder_1.append("*/");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatMLCommentInBlock2() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* my comment");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class bar{");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("/*");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("my comment");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("*/");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatMLCommentInBlock3() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/*");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* my comment");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class bar{");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("/*");
    _builder_1.newLine();
    _builder_1.append("\t\t\t\t");
    _builder_1.append("*my comment");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("*/");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatMLCommentAfterStatement2() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class bar { /* my comment */");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class bar{   /* my comment */ ");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatMLCommentBeforeStatement21() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/* my comment */");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class bar{");
    _builder_1.newLine();
    _builder_1.append("/* my comment */");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Test
  public void formatMLCommentBeforeStatement22() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class AATest {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/**");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("* genColumns");
    _builder.newLine();
    _builder.append("\t ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertFormatted(_builder);
  }

  @Ignore("see https://bugs.eclipse.org/bugs/show_bug.cgi?id=415950")
  @Test
  public void formatSLCommentAtEndOfClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val i = 0");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("/* my comment */");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class bar{");
    _builder_1.newLine();
    _builder_1.append("val i = 0");
    _builder_1.newLine();
    _builder_1.append("/* my comment */");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertFormatted(_builder, _builder_1);
  }

  @Ignore("see https://bugs.eclipse.org/bugs/show_bug.cgi?id=415950")
  @Test
  public void formatSLCommentAtEndOfMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class FormatterIssue {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def method() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val i = 0");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("// comment");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertFormatted(_builder);
  }

  @Ignore("see https://github.com/eclipse/xtext-xtend/issues/77")
  @Test
  public void formatSLCommentAfterCode() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def method() { // comment");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val i = 0");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.assertFormatted(_builder);
  }
}
