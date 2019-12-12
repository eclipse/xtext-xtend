/**
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.tests.validation;

import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.xtend.core.tests.AbstractXtendTestCase;
import org.eclipse.xtend.core.validation.IssueCodes;
import org.eclipse.xtend.core.xtend.XtendFile;
import org.eclipse.xtend.core.xtend.XtendPackage;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.diagnostics.Diagnostic;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
@SuppressWarnings("all")
public class JUnitMethodReturnTypeValidationTest extends AbstractXtendTestCase {
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void test001() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasNoValidationIssue(_builder);
  }
  
  @Test
  public void test002() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def int test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasNoValidationIssue(_builder);
  }
  
  @Test
  public void test003() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def void test() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasNoValidationIssue(_builder);
  }
  
  @Test
  public void test004() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasNoValidationIssue(_builder);
  }
  
  @Test
  public void test005() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Before");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before def void before() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasNoValidationIssue(_builder);
  }
  
  @Test
  public void test006() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Before");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before def before() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasNoValidationIssue(_builder);
  }
  
  @Test
  public void test007() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.After");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After def void after() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasNoValidationIssue(_builder);
  }
  
  @Test
  public void test008() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.After");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After def after() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasNoValidationIssue(_builder);
  }
  
  @Test
  public void test009() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.BeforeClass");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@BeforeClass def static void beforeClass() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasNoValidationIssue(_builder);
  }
  
  @Test
  public void test010() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.BeforeClass");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@BeforeClass def static beforeClass() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasNoValidationIssue(_builder);
  }
  
  @Test
  public void test011() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.AfterClass");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@AfterClass def static void afterClass() {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasNoValidationIssue(_builder);
  }
  
  @Test
  public void test012() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.AfterClass");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@AfterClass def static afterClass() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasNoValidationIssue(_builder);
  }
  
  @Test
  public void test013() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def int test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is int.");
  }
  
  @Test
  public void test014() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method m() must be void but is int.");
  }
  
  @Test
  public void test015() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def Object test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\"foo\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test016() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test017() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Before");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before def int before() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method before() must be void but is int.");
  }
  
  @Test
  public void test018() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Before");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before def before() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method before() must be void but is int.");
  }
  
  @Test
  public void test019() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Before");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before def Object before() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\"foo\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method before() must be void but is Object.");
  }
  
  @Test
  public void test020() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Before");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before def before() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method before() must be void but is Object.");
  }
  
  @Test
  public void test021() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.After");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After def int after() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method after() must be void but is int.");
  }
  
  @Test
  public void test022() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.After");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After def after() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method after() must be void but is int.");
  }
  
  @Test
  public void test023() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.After");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After def Object after() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\"foo\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method after() must be void but is Object.");
  }
  
  @Test
  public void test024() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.After");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After def after() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method after() must be void but is Object.");
  }
  
  @Test
  public void test025() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.BeforeClass");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@BeforeClass def static int beforeClass() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method beforeClass() must be void but is int.");
  }
  
  @Test
  public void test026() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.BeforeClass");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@BeforeClass def static beforeClass() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method beforeClass() must be void but is int.");
  }
  
  @Test
  public void test027() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.BeforeClass");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@BeforeClass def static Object beforeClass() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\"foo\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method beforeClass() must be void but is Object.");
  }
  
  @Test
  public void test028() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.BeforeClass");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@BeforeClass def static beforeClass() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method beforeClass() must be void but is Object.");
  }
  
  @Test
  public void test029() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.AfterClass");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@AfterClass def static int afterClass() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method afterClass() must be void but is int.");
  }
  
  @Test
  public void test030() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.AfterClass");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@AfterClass def static afterClass() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method afterClass() must be void but is int.");
  }
  
  @Test
  public void test031() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.AfterClass");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@AfterClass def static Object afterClass() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\"foo\"");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method afterClass() must be void but is Object.");
  }
  
  @Test
  public void test032() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.AfterClass");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@AfterClass def static afterClass() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method afterClass() must be void but is Object.");
  }
  
  @Test
  public void test033() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("foo");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, XbasePackage.Literals.XFEATURE_CALL, Diagnostic.LINKING_DIAGNOSTIC, "The method or field foo is undefined");
  }
  
  @Test
  public void test040_RunWith_Test() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(org.junit.runners.Parameterized)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test041_RunWith_Test() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.runners.Parameterized");
    _builder.newLine();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(Parameterized)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test042_RunWith_Test() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(org.eclipse.xtext.testing.XtextRunner)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test043_RunWith_Test() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.eclipse.xtext.testing.XtextRunner");
    _builder.newLine();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(XtextRunner)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test044_RunWith_Test() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(com.foo.bar.MyRunner)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package com.foo.bar");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class MyRunner extends org.eclipse.xtext.testing.XtextRunner {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("new(Class<?> klass) throws org.junit.runners.model.InitializationError {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("super(klass);");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.hasNoValidationIssue(this.withRunner(_builder, _builder_1));
  }
  
  @Test
  public void test045_RunWith_Test() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.foo.bar.MyRunner");
    _builder.newLine();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(MyRunner)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package com.foo.bar");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class MyRunner extends org.eclipse.xtext.testing.XtextRunner {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("new(Class<?> klass) throws org.junit.runners.model.InitializationError {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("super(klass);");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.hasNoValidationIssue(this.withRunner(_builder, _builder_1));
  }
  
  @Test
  public void test046_RunWith_Before() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Before");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(org.junit.runners.Parameterized)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test047_RunWith_Before() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.runners.Parameterized");
    _builder.newLine();
    _builder.append("import org.junit.Before");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(Parameterized)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test048_RunWith_Before() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Before");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(org.eclipse.xtext.testing.XtextRunner)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test049_RunWith_Before() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.eclipse.xtext.testing.XtextRunner");
    _builder.newLine();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Before");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(XtextRunner)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test050_RunWith_Before() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Before");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(com.foo.bar.MyRunner)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package com.foo.bar");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class MyRunner extends org.eclipse.xtext.testing.XtextRunner {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("new(Class<?> klass) throws org.junit.runners.model.InitializationError {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("super(klass);");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.hasNoValidationIssue(this.withRunner(_builder, _builder_1));
  }
  
  @Test
  public void test051_RunWith_Before() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.foo.bar.MyRunner");
    _builder.newLine();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Before");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(MyRunner)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Before def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package com.foo.bar");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class MyRunner extends org.eclipse.xtext.testing.XtextRunner {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("new(Class<?> klass) throws org.junit.runners.model.InitializationError {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("super(klass);");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.hasNoValidationIssue(this.withRunner(_builder, _builder_1));
  }
  
  @Test
  public void test052_RunWith_After() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.After");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(org.junit.runners.Parameterized)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test053_RunWith_After() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.runners.Parameterized");
    _builder.newLine();
    _builder.append("import org.junit.After");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(Parameterized)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test054_RunWith_After() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.After");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(org.eclipse.xtext.testing.XtextRunner)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test055_RunWith_After() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.eclipse.xtext.testing.XtextRunner");
    _builder.newLine();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.After");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(XtextRunner)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.hasOneValidationIssue(_builder, "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test056_RunWith_After() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.After");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(com.foo.bar.MyRunner)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package com.foo.bar");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.testing.XtextRunner");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class MyRunner extends XtextRunner {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("new(Class<?> klass) throws org.junit.runners.model.InitializationError {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("super(klass);");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.hasNoValidationIssue(this.withRunner(_builder, _builder_1));
  }
  
  @Test
  public void test057_RunWith_After() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.foo.bar.MyRunner");
    _builder.newLine();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.After");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(MyRunner)");
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@After def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package com.foo.bar");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.testing.XtextRunner");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("class MyRunner extends XtextRunner {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("new(Class<?> klass) throws org.junit.runners.model.InitializationError {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("super(klass);");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.hasNoValidationIssue(this.withRunner(_builder, _builder_1));
  }
  
  @Test
  public void test060_RunWith_withSuperClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo extends Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.junit.runner.RunWith");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@RunWith(org.junit.runners.Parameterized)");
    _builder_1.newLine();
    _builder_1.append("class Bar {");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.hasOneValidationIssue(this.withSuperClass(_builder, _builder_1), "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test061_RunWith_withSuperClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo extends Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.junit.runner.RunWith");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@RunWith(org.eclipse.xtext.testing.XtextRunner)");
    _builder_1.newLine();
    _builder_1.append("class Bar {");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.hasOneValidationIssue(this.withSuperClass(_builder, _builder_1), "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test062_RunWith_withSuperClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo extends Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.junit.runner.RunWith");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@RunWith(com.foo.bar.MyRunner)");
    _builder_1.newLine();
    _builder_1.append("class Bar {");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("package com.foo.bar");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("class MyRunner extends org.eclipse.xtext.testing.XtextRunner {");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("new(Class<?> klass) throws org.junit.runners.model.InitializationError {");
    _builder_2.newLine();
    _builder_2.append("\t\t");
    _builder_2.append("super(null);");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("}");
    _builder_2.newLine();
    _builder_2.append("}");
    _builder_2.newLine();
    this.hasNoValidationIssue(this.withSuperClassAndRunner(_builder, _builder_1, _builder_2));
  }
  
  @Test
  public void test063_RunWith_withSuperClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(org.junit.runners.Parameterized)");
    _builder.newLine();
    _builder.append("class Foo extends Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.junit.runner.RunWith");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@RunWith(com.foo.bar.MyRunner)");
    _builder_1.newLine();
    _builder_1.append("class Bar {");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("package com.foo.bar");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("class MyRunner extends org.eclipse.xtext.testing.XtextRunner {");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("new(Class<?> klass) throws org.junit.runners.model.InitializationError {");
    _builder_2.newLine();
    _builder_2.append("\t\t");
    _builder_2.append("super(null);");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("}");
    _builder_2.newLine();
    _builder_2.append("}");
    _builder_2.newLine();
    this.hasOneValidationIssue(this.withSuperClassAndRunner(_builder, _builder_1, _builder_2), "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test064_RunWith_withSuperClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(org.eclipse.xtext.testing.XtextRunner)");
    _builder.newLine();
    _builder.append("class Foo extends Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.junit.runner.RunWith");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@RunWith(com.foo.bar.MyRunner)");
    _builder_1.newLine();
    _builder_1.append("class Bar {");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("package com.foo.bar");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("class MyRunner extends org.eclipse.xtext.testing.XtextRunner {");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("new(Class<?> klass) throws org.junit.runners.model.InitializationError {");
    _builder_2.newLine();
    _builder_2.append("\t\t");
    _builder_2.append("super(null);");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("}");
    _builder_2.newLine();
    _builder_2.append("}");
    _builder_2.newLine();
    this.hasOneValidationIssue(this.withSuperClassAndRunner(_builder, _builder_1, _builder_2), "JUnit method test() must be void but is Object.");
  }
  
  @Test
  public void test065_RunWith_withSuperClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(com.foo.bar.MyRunner)");
    _builder.newLine();
    _builder.append("class Foo extends Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.junit.runner.RunWith");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@RunWith(org.junit.runners.Parameterized)");
    _builder_1.newLine();
    _builder_1.append("class Bar {");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("package com.foo.bar");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("class MyRunner extends org.eclipse.xtext.testing.XtextRunner {");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("new(Class<?> klass) throws org.junit.runners.model.InitializationError {");
    _builder_2.newLine();
    _builder_2.append("\t\t");
    _builder_2.append("super(null);");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("}");
    _builder_2.newLine();
    _builder_2.append("}");
    _builder_2.newLine();
    this.hasNoValidationIssue(this.withSuperClassAndRunner(_builder, _builder_1, _builder_2));
  }
  
  @Test
  public void test066_RunWith_withSuperClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.junit.runner.RunWith");
    _builder.newLine();
    _builder.append("import org.junit.Test");
    _builder.newLine();
    _builder.newLine();
    _builder.append("@RunWith(com.foo.bar.MyRunner)");
    _builder.newLine();
    _builder.append("class Foo extends Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Test def test() {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.junit.runner.RunWith");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@RunWith(org.eclipse.xtext.testing.XtextRunner)");
    _builder_1.newLine();
    _builder_1.append("class Bar {");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    StringConcatenation _builder_2 = new StringConcatenation();
    _builder_2.append("package com.foo.bar");
    _builder_2.newLine();
    _builder_2.newLine();
    _builder_2.append("class MyRunner extends org.eclipse.xtext.testing.XtextRunner {");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("new(Class<?> klass) throws org.junit.runners.model.InitializationError {");
    _builder_2.newLine();
    _builder_2.append("\t\t");
    _builder_2.append("super(null);");
    _builder_2.newLine();
    _builder_2.append("\t");
    _builder_2.append("}");
    _builder_2.newLine();
    _builder_2.append("}");
    _builder_2.newLine();
    this.hasNoValidationIssue(this.withSuperClassAndRunner(_builder, _builder_1, _builder_2));
  }
  
  private void hasNoValidationIssue(final CharSequence it) {
    this.assertNumberOfValidationIssues(it, 0);
  }
  
  private void hasOneValidationIssue(final CharSequence it, final String message) {
    this.hasOneValidationIssue(it, XtendPackage.Literals.XTEND_FUNCTION, IssueCodes.INVALID_RETURN_TYPE_IN_CASE_OF_JUNIT_ANNOTATION, message);
  }
  
  private void hasOneValidationIssue(final CharSequence it, final EClass objectType, final String issueCode, final String message) {
    try {
      this.hasOneValidationIssue(this.file(it.toString()), objectType, issueCode, message);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private XtendFile assertNumberOfValidationIssues(final CharSequence it, final int expectedNumberOfIssues) {
    try {
      return this.assertNumberOfValidationIssues(this.file(it.toString()), expectedNumberOfIssues);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private XtendFile withRunner(final CharSequence it, final CharSequence runnerDef) {
    try {
      final Iterator<XtendFile> files = this.files(false, runnerDef.toString(), it.toString()).iterator();
      final List<Issue> runnerIssues = this._validationTestHelper.validate(files.next());
      String _join = IterableExtensions.join(runnerIssues, "\n");
      String _plus = ("Runner class has issues:\n" + _join);
      Assert.assertEquals(_plus, 0, runnerIssues.size());
      return files.next();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private XtendFile withSuperClass(final CharSequence it, final CharSequence superClazzDef) {
    try {
      final Iterator<XtendFile> files = this.files(false, superClazzDef.toString(), it.toString()).iterator();
      final List<Issue> superIssues = this._validationTestHelper.validate(files.next());
      String _join = IterableExtensions.join(superIssues, "\n");
      String _plus = ("Super class has issues:\n" + _join);
      Assert.assertEquals(_plus, 0, superIssues.size());
      return files.next();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private XtendFile withSuperClassAndRunner(final CharSequence it, final CharSequence superClazzDef, final CharSequence runnerDef) {
    try {
      final Iterator<XtendFile> files = this.files(false, runnerDef.toString(), superClazzDef.toString(), it.toString()).iterator();
      final List<Issue> runnerIssues = this._validationTestHelper.validate(files.next());
      String _join = IterableExtensions.join(runnerIssues, "\n");
      String _plus = ("Runner class has issues:\n" + _join);
      Assert.assertEquals(_plus, 0, runnerIssues.size());
      final List<Issue> superIssues = this._validationTestHelper.validate(files.next());
      String _join_1 = IterableExtensions.join(superIssues, "\n");
      String _plus_1 = ("Super class has issues:\n" + _join_1);
      Assert.assertEquals(_plus_1, 0, superIssues.size());
      return files.next();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void hasNoValidationIssue(final XtendFile it) {
    this.assertNumberOfValidationIssues(it, 0);
  }
  
  private void hasOneValidationIssue(final XtendFile it, final String message) {
    this.hasOneValidationIssue(it, XtendPackage.Literals.XTEND_FUNCTION, IssueCodes.INVALID_RETURN_TYPE_IN_CASE_OF_JUNIT_ANNOTATION, message);
  }
  
  private void hasOneValidationIssue(final XtendFile it, final EClass objectType, final String issueCode, final String message) {
    this.assertNumberOfValidationIssues(it, 1);
    this._validationTestHelper.assertError(it, objectType, issueCode, message);
  }
  
  private XtendFile assertNumberOfValidationIssues(final XtendFile it, final int expectedNumberOfIssues) {
    XtendFile _xblockexpression = null;
    {
      final List<Issue> issues = this._validationTestHelper.validate(it);
      String _xifexpression = null;
      boolean _isEmpty = issues.isEmpty();
      boolean _not = (!_isEmpty);
      if (_not) {
        String _join = IterableExtensions.join(issues, "\n");
        _xifexpression = ("Issues:\n" + _join);
      }
      Assert.assertEquals(_xifexpression, expectedNumberOfIssues, issues.size());
      _xblockexpression = it;
    }
    return _xblockexpression;
  }
}
