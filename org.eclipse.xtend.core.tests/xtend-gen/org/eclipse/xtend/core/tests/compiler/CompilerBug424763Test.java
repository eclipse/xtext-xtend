/**
 * Copyright (c) 2014, 2016 itemis AG (http://www.itemis.eu) and others.
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
public class CompilerBug424763Test extends AbstractXtendCompilerTest {
  @Test
  public void testBug_424763_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static com.google.common.base.Preconditions.*");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class NoConstructor<JAVA_TYPE> implements Functions.Function0<JAVA_TYPE> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val String type");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(String theTypeName) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("type = checkNotNull(theTypeName, \"theTypeName\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(Class<JAVA_TYPE> theType) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("type = checkNotNull(theType, \"theType\").name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override apply() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new UnsupportedOperationException(\"Instances of type \"+type");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("+\" cannot be created (without parameters?)\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test<JAVA_TYPE> {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val Functions.Function0<JAVA_TYPE> constructor");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(Functions.Function0<JAVA_TYPE> theConstructor,");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Class<JAVA_TYPE> theType) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("constructor = if (theConstructor == null)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("new NoConstructor<JAVA_TYPE>(theType) else theConstructor");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static test(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new Test(null, String)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Preconditions;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function0;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class NoConstructor<JAVA_TYPE extends Object> implements Function0<JAVA_TYPE> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final String type;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public NoConstructor(final String theTypeName) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this.type = Preconditions.<String>checkNotNull(theTypeName, \"theTypeName\");");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public NoConstructor(final Class<JAVA_TYPE> theType) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this.type = Preconditions.<Class<JAVA_TYPE>>checkNotNull(theType, \"theType\").getName();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public JAVA_TYPE apply() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("throw new UnsupportedOperationException(");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("((\"Instances of type \" + this.type) + \" cannot be created (without parameters?)\"));");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug_424763_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static com.google.common.base.Preconditions.*");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test<JAVA_TYPE> {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val Functions.Function0<JAVA_TYPE> constructor");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val list = new java.util.ArrayList(1)");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(Functions.Function0<JAVA_TYPE> theConstructor, Class<JAVA_TYPE> theType) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("constructor = if (theConstructor == null)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("new NoConstructor<JAVA_TYPE>(theType)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("else ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("theConstructor");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static test(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new Test(null, String)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class NoConstructor<JAVA_TYPE> implements Functions.Function0<JAVA_TYPE> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val String type");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(String theTypeName) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("type = checkNotNull(theTypeName, \"theTypeName\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(Class<JAVA_TYPE> theType) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("type = checkNotNull(theType, \"theType\").name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override apply() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new UnsupportedOperationException(\"Instances of type \"+type");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("+\" cannot be created (without parameters?)\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Objects;");
    _builder_1.newLine();
    _builder_1.append("import java.util.ArrayList;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function0;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test<JAVA_TYPE extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final Function0<JAVA_TYPE> constructor;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final ArrayList<Object> list = new ArrayList<Object>(1);");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Test(final Function0<JAVA_TYPE> theConstructor, final Class<JAVA_TYPE> theType) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("Function0<JAVA_TYPE> _xifexpression = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("boolean _equals = Objects.equal(theConstructor, null);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (_equals) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_xifexpression = new NoConstructor<JAVA_TYPE>(theType);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} else {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_xifexpression = theConstructor;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this.constructor = _xifexpression;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static Test<String> test() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return new Test<String>(null, String.class);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug_424763_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C<T> {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val Functions.Function0<T> constructor");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(C<T> theConstructor) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.constructor = theConstructor?.constructor");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function0;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final Function0<T> constructor;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public C(final C<T> theConstructor) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("Function0<T> _constructor = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (theConstructor!=null) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_constructor=theConstructor.constructor;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this.constructor = _constructor;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug_424763_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C<T> {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val Functions.Function0<T> constructor");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(C<T> theConstructor) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.constructor = theConstructor?.doGetConstructor(try {\'\'} finally {})");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def doGetConstructor(String s) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("constructor");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function0;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final Function0<T> constructor;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public C(final C<T> theConstructor) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("Function0<T> _doGetConstructor = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (theConstructor!=null) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("String _xtrycatchfinallyexpression = null;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("_xtrycatchfinallyexpression = \"\";");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_doGetConstructor=theConstructor.doGetConstructor(_xtrycatchfinallyexpression);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this.constructor = _doGetConstructor;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Function0<T> doGetConstructor(final String s) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.constructor;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug_424763_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static com.google.common.base.Preconditions.*");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Test<JAVA_TYPE> {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val Functions.Function0<JAVA_TYPE> constructor");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(Functions.Function0<JAVA_TYPE> theConstructor, Class<JAVA_TYPE> theType) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("constructor = if (theConstructor == null)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("new NoConstructor<JAVA_TYPE>(try { theType } finally {})");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("else ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("theConstructor");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static test(){");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new Test(null, String)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class NoConstructor<JAVA_TYPE> implements Functions.Function0<JAVA_TYPE> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val String type");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(String theTypeName) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("type = checkNotNull(theTypeName, \"theTypeName\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(Class<JAVA_TYPE> theType) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("type = checkNotNull(theType, \"theType\").name");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override apply() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("throw new UnsupportedOperationException(\"Instances of type \"+type");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("+\" cannot be created (without parameters?)\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Objects;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function0;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class Test<JAVA_TYPE extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final Function0<JAVA_TYPE> constructor;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Test(final Function0<JAVA_TYPE> theConstructor, final Class<JAVA_TYPE> theType) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("Function0<JAVA_TYPE> _xifexpression = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("boolean _equals = Objects.equal(theConstructor, null);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (_equals) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Class<JAVA_TYPE> _xtrycatchfinallyexpression = null;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("_xtrycatchfinallyexpression = theType;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_xifexpression = new NoConstructor<JAVA_TYPE>(_xtrycatchfinallyexpression);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} else {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_xifexpression = theConstructor;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this.constructor = _xifexpression;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static Test<String> test() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return new Test<String>(null, String.class);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug_424763_06() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C<T> {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val Functions.Function0<T> constructor");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(C<T> theConstructor) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this(theConstructor?.constructor)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(Functions.Function0<T> f) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.constructor = f");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function0;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final Function0<T> constructor;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public C(final C<T> theConstructor) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this(new Function0<Function0<T>>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public Function0<T> apply() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("Function0<T> _constructor = null;");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("if (theConstructor!=null) {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("_constructor=theConstructor.constructor;");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return _constructor;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}.apply());");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public C(final Function0<T> f) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this.constructor = f;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug_424763_07() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C<T> {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val Functions.Function0<T> constructor");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(C<T> theConstructor) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.constructor = theConstructor.doGetConstructor(try {\'\'} finally {})");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def doGetConstructor(String s) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("constructor");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function0;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final Function0<T> constructor;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public C(final C<T> theConstructor) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("String _xtrycatchfinallyexpression = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_xtrycatchfinallyexpression = \"\";");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this.constructor = theConstructor.doGetConstructor(_xtrycatchfinallyexpression);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Function0<T> doGetConstructor(final String s) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.constructor;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void testBug_424763_08() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C<T> {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("val Functions.Function0<T> constructor");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(C<T> theConstructor) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this(theConstructor.doGetConstructor(try {\'\'} finally {}))");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(Functions.Function0<T> f) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.constructor = f");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def doGetConstructor(String s) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("constructor");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function0;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C<T extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("private final Function0<T> constructor;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public C(final C<T> theConstructor) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this(theConstructor.doGetConstructor(new Function0<String>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public String apply() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("String _xtrycatchfinallyexpression = null;");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("_xtrycatchfinallyexpression = \"\";");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return _xtrycatchfinallyexpression;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}.apply()));");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public C(final Function0<T> f) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("this.constructor = f;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Function0<T> doGetConstructor(final String s) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return this.constructor;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
