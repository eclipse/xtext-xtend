/**
 * Copyright (c) 2013, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.compiler;

import org.eclipse.xtend.core.tests.SingletonGeneratorConfigRuntimeInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.util.JavaRuntimeVersion;
import org.eclipse.xtext.xbase.compiler.GeneratorConfig;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@InjectWith(SingletonGeneratorConfigRuntimeInjectorProvider.class)
@SuppressWarnings("all")
public class ConfiguredCompilerTest extends AbstractXtendCompilerTest {
  @Test
  public void compileWithConfiguration() {
    final GeneratorConfig generatorConfig = this.generatorConfigProvider.get(null);
    generatorConfig.setGenerateSyntheticSuppressWarnings(false);
    generatorConfig.setGenerateExpressions(false);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* javadoc");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("class Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1 + 1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("/**");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("* javadoc");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("*/");
    _builder_1.newLine();
    _builder_1.append("public class Bar {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public int foo() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("throw new UnsupportedOperationException(\"foo is not implemented\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationWithValueArray_01() {
    final GeneratorConfig generatorConfig = this.generatorConfigProvider.get(null);
    generatorConfig.setGenerateSyntheticSuppressWarnings(false);
    generatorConfig.setGenerateExpressions(true);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@SuppressWarnings(#[ \'abc\', \'efg\' ])");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings({ \"abc\", \"efg\" })");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testAnnotationWithValueArray_02() {
    final GeneratorConfig generatorConfig = this.generatorConfigProvider.get(null);
    generatorConfig.setGenerateSyntheticSuppressWarnings(false);
    generatorConfig.setGenerateExpressions(true);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@SuppressWarnings(\'abc\', \'efg\')");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings({ \"abc\", \"efg\" })");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void compileWithConfiguration_2() {
    final GeneratorConfig generatorConfig = this.generatorConfigProvider.get(null);
    generatorConfig.setGenerateSyntheticSuppressWarnings(true);
    generatorConfig.setGenerateExpressions(false);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* javadoc");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("@SuppressWarnings(\"unused\")");
    _builder.newLine();
    _builder.append("class Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1 + 1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("/**");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("* javadoc");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("*/");
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"unused\")");
    _builder_1.newLine();
    _builder_1.append("public class Bar {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public int foo() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("throw new UnsupportedOperationException(\"foo is not implemented\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void compileWithConfiguration_3() {
    final GeneratorConfig generatorConfig = this.generatorConfigProvider.get(null);
    generatorConfig.setGenerateSyntheticSuppressWarnings(true);
    generatorConfig.setGenerateExpressions(false);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* javadoc");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("@Deprecated");
    _builder.newLine();
    _builder.append("@SuppressWarnings(\"unused\")");
    _builder.newLine();
    _builder.append("class Bar {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("1 + 1");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("/**");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("* javadoc");
    _builder_1.newLine();
    _builder_1.append(" ");
    _builder_1.append("*/");
    _builder_1.newLine();
    _builder_1.append("@Deprecated");
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"unused\")");
    _builder_1.newLine();
    _builder_1.append("public class Bar {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public int foo() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("throw new UnsupportedOperationException(\"foo is not implemented\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testGeneratedAnnotation_01() {
    final GeneratorConfig generatorConfig = this.generatorConfigProvider.get(null);
    generatorConfig.setGenerateSyntheticSuppressWarnings(false);
    generatorConfig.setGenerateGeneratedAnnotation(true);
    generatorConfig.setIncludeDateInGeneratedAnnotation(false);
    generatorConfig.setGeneratedAnnotationComment("");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo");
    _builder.newLine();
    _builder.append("class Bar {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo;");
    _builder_1.newLine();
    _builder_1.newLine();
    {
      boolean _isJava11OrLater = JavaRuntimeVersion.isJava11OrLater();
      boolean _not = (!_isJava11OrLater);
      if (_not) {
        _builder_1.append("import javax.annotation.Generated;");
        _builder_1.newLine();
        _builder_1.newLine();
        _builder_1.append("@Generated(\"org.eclipse.xtend.core.compiler.XtendGenerator\")");
        _builder_1.newLine();
      }
    }
    _builder_1.append("public class Bar {");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testGeneratedAnnotation_02() {
    final GeneratorConfig generatorConfig = this.generatorConfigProvider.get(null);
    generatorConfig.setGenerateSyntheticSuppressWarnings(false);
    generatorConfig.setGenerateGeneratedAnnotation(true);
    generatorConfig.setIncludeDateInGeneratedAnnotation(false);
    generatorConfig.setGeneratedAnnotationComment("Source: ${sourcefile}");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo");
    _builder.newLine();
    _builder.append("class Bar {");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("package foo;");
    _builder_1.newLine();
    _builder_1.newLine();
    {
      boolean _isJava11OrLater = JavaRuntimeVersion.isJava11OrLater();
      boolean _not = (!_isJava11OrLater);
      if (_not) {
        _builder_1.append("import javax.annotation.Generated;");
        _builder_1.newLine();
        _builder_1.newLine();
        _builder_1.append("@Generated(value = \"org.eclipse.xtend.core.compiler.XtendGenerator\", comments = \"Source: Bar.xtend\")");
        _builder_1.newLine();
      }
    }
    _builder_1.append("public class Bar {");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
