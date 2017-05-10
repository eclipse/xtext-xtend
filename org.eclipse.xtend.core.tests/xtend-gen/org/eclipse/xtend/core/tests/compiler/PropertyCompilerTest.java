/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.tests.compiler;

import com.google.inject.Inject;
import java.lang.reflect.Method;
import org.eclipse.xtend.core.tests.compiler.AbstractXtendCompilerTest;
import org.eclipse.xtend.core.xtend.XtendMember;
import org.eclipse.xtend.core.xtend.XtendPackage;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.testing.CompilationTestHelper;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class PropertyCompilerTest extends AbstractXtendCompilerTest {
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void testBug438347_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C<T> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Property var (T)=>void s");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtend.lib.Property;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Pure;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Property");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private Procedure1<? super T> _s;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Pure");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Procedure1<? super T> getS() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this._s;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void setS(final Procedure1<? super T> s) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this._s = s;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug438347_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Property var (String)=>void s");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtend.lib.Property;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Pure;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Property");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private Procedure1<? super String> _s;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Pure");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Procedure1<? super String> getS() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this._s;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void setS(final Procedure1<? super String> s) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this._s = s;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void compileProperty() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Property");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("boolean generateExpressions = true");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtend.lib.Property;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Pure;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Bar {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Property");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private boolean _generateExpressions = true;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Pure");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public boolean isGenerateExpressions() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this._generateExpressions;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void setGenerateExpressions(final boolean generateExpressions) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this._generateExpressions = generateExpressions;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void compileReadonlyPropertyWithoutType() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Property");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val string = \'\'");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtend.lib.Property;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Pure;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Property");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final String _string = \"\";");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Pure");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String getString() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this._string;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void compilePropertyWithoutType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Property");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var string = \'\'");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(IterableExtensions.<XtendMember>head(this.clazz(_builder.toString()).getMembers()), XtendPackage.Literals.XTEND_FIELD, "user.issue", "inferred");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void compilePropertyWithTypeParameter() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C<T> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Property");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("var T t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtend.lib.Property;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Pure;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Property");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private T _t;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Pure");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public T getT() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this._t;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void setT(final T t) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this._t = t;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void compilePropertyWithoutTypeButTypeParameter() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class C<T> {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Property");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("var iterable = null as Iterable<T>");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      this._validationTestHelper.assertError(IterableExtensions.<XtendMember>head(this.clazz(_builder.toString()).getMembers()), XtendPackage.Literals.XTEND_FIELD, "user.issue", "inferred");
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void compilePropertyWithArrayType() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C<T> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Property");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("var String[] array = #[\'a\']");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtend.lib.Property;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Pure;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Property");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private String[] _array = { \"a\" };");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Pure");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String[] getArray() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this._array;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void setArray(final String[] array) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this._array = array;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void compileExplicitProperty() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class X {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("@Property val String x = \'hello\'");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtend.lib.Property;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Pure;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class X {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Property");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final String _x = \"hello\";");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("@Pure");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public String getX() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this._x;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testExistingGetter() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Property int foo");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def getFoo() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        try {
          final Object instance = it.getCompiledClass().newInstance();
          final Method getFoo = it.getCompiledClass().getDeclaredMethod("getFoo");
          Assert.assertEquals(Integer.valueOf(5), getFoo.invoke(instance));
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      this.compilationTestHelper.compile(_builder, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testExistingSetter() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Property int foo");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def setFoo(int foo) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("_foo = 5");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        try {
          final Object instance = it.getCompiledClass().newInstance();
          final Method setFoo = it.getCompiledClass().getDeclaredMethod("setFoo", int.class);
          setFoo.invoke(instance, Integer.valueOf(1));
          final Method getFoo = it.getCompiledClass().getDeclaredMethod("getFoo");
          Assert.assertEquals(Integer.valueOf(5), getFoo.invoke(instance));
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      this.compilationTestHelper.compile(_builder, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testStaticProperty() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Property static int foo = 1");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final IAcceptor<CompilationTestHelper.Result> _function = (CompilationTestHelper.Result it) -> {
        try {
          final Method setFoo = it.getCompiledClass().getDeclaredMethod("setFoo", int.class);
          setFoo.invoke(null, Integer.valueOf(1));
          final Method getFoo = it.getCompiledClass().getDeclaredMethod("getFoo");
          Assert.assertEquals(Integer.valueOf(1), getFoo.invoke(null));
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      this.compilationTestHelper.compile(_builder, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
