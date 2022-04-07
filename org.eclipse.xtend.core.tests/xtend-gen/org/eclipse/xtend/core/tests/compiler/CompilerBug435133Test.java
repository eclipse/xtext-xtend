/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.compiler;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class CompilerBug435133Test extends AbstractXtendCompilerTest {
  @Test
  public void test_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.google.common.base.Optional");
    _builder.newLine();
    _builder.append("import static java.lang.annotation.RetentionPolicy.*");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static Optional<Object> valueOf(Object o, Object context) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def Optional<Object> m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val idx = valueOf(null, 1)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("idx.present");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Optional;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static Optional<Object> valueOf(final Object o, final Object context) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Optional<Object> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Optional<Object> idx = C.valueOf(null, Integer.valueOf(1));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("idx.isPresent();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
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
    _builder.append("import com.google.common.base.Optional");
    _builder.newLine();
    _builder.append("import static java.lang.Enum.valueOf");
    _builder.newLine();
    _builder.append("import java.lang.annotation.RetentionPolicy");
    _builder.newLine();
    _builder.append("import java.util.List");
    _builder.newLine();
    _builder.append("import java.util.Set");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static Optional<Object> valueOf(Object o, Object context) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def Optional<Object> m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val idx = valueOf(RetentionPolicy, \'\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("idx.toString");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Optional;");
    _builder_1.newLine();
    _builder_1.append("import java.lang.annotation.RetentionPolicy;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static Optional<Object> valueOf(final Object o, final Object context) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Optional<Object> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final RetentionPolicy idx = Enum.<RetentionPolicy>valueOf(RetentionPolicy.class, \"\");");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("idx.toString();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
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
    _builder.append("import com.google.common.base.Optional");
    _builder.newLine();
    _builder.append("import static java.lang.Enum.*");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static Optional<Object> valueOf(Object o, Object context) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def Optional<Object> m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val idx = <Enum>valueOf(null, null)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Optional;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static Optional<Object> valueOf(final Object o, final Object context) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Optional<Object> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Enum idx = Enum.<Enum>valueOf(null, null);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
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
    _builder.append("import com.google.common.base.Optional");
    _builder.newLine();
    _builder.append("import static java.lang.Enum.*");
    _builder.newLine();
    _builder.append("import java.lang.annotation.RetentionPolicy");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static Optional<Object> valueOf(Object o, Object context) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def Optional<Object> m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val RetentionPolicy idx = valueOf(null, null)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Optional;");
    _builder_1.newLine();
    _builder_1.append("import java.lang.annotation.RetentionPolicy;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static Optional<Object> valueOf(final Object o, final Object context) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Optional<Object> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final RetentionPolicy idx = Enum.<RetentionPolicy>valueOf(null, null);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
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
    _builder.append("import com.google.common.base.Optional");
    _builder.newLine();
    _builder.append("import static java.lang.Enum.*");
    _builder.newLine();
    _builder.append("import java.lang.annotation.RetentionPolicy");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static Optional<Object> valueOf(Object o, Object context) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def Optional<Object> m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val Enum<RetentionPolicy> idx = valueOf(null, null)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return null");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Optional;");
    _builder_1.newLine();
    _builder_1.append("import java.lang.annotation.RetentionPolicy;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static Optional<Object> valueOf(final Object o, final Object context) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Optional<Object> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Enum<RetentionPolicy> idx = Enum.<RetentionPolicy>valueOf(null, null);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Ignore
  @Test
  public void testBug436483_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.google.common.base.Optional");
    _builder.newLine();
    _builder.append("import static java.lang.Enum.*");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static Optional<Object> valueOf(Object o, Object context) {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def Optional<Object> m() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val idx = valueOf(null, null)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return idx");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Optional;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static Optional<Object> valueOf(final Object o, final Object context) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Optional<Object> m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Enum idx = Enum.<Enum>valueOf(null, null);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
