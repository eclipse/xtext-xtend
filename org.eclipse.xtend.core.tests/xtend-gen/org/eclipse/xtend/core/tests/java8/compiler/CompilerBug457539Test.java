/**
 * Copyright (c) 2015, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.java8.compiler;

import org.eclipse.xtend.core.tests.java8.Java8RuntimeInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@InjectWith(Java8RuntimeInjectorProvider.class)
@SuppressWarnings("all")
public class CompilerBug457539Test extends org.eclipse.xtend.core.tests.compiler.CompilerBug457539Test {
  @Test
  @Override
  public void test_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.ArrayList");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m()\t{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val list = new ArrayList");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.forEach [");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (it instanceof String) list.add(it)");
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
    _builder_1.append("import java.util.ArrayList;");
    _builder_1.newLine();
    _builder_1.append("import java.util.function.Consumer;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final ArrayList<Object> list = new ArrayList<Object>();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Consumer<Object> _function = (Object it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("if ((it instanceof String)) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("list.add(it);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.forEach(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  @Override
  public void test_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m()\t{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val list = newArrayList");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.forEach [");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (it instanceof String) list.add(it)");
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
    _builder_1.append("import java.util.ArrayList;");
    _builder_1.newLine();
    _builder_1.append("import java.util.function.Consumer;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.CollectionLiterals;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final ArrayList<Object> list = CollectionLiterals.<Object>newArrayList();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Consumer<Object> _function = (Object it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("if ((it instanceof String)) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("list.add(it);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.forEach(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  @Override
  public void test_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m()\t{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val list = newIterable");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.forEach [");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <T extends CharSequence> MyIterable<T> newIterable(T... initial) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface MyIterable<T extends CharSequence> extends Iterable<T> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.function.Consumer;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface MyIterable<T extends CharSequence> extends Iterable<T> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C.MyIterable<CharSequence> list = C.<CharSequence>newIterable();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Consumer<CharSequence> _function = (CharSequence it) -> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.forEach(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <T extends CharSequence> C.MyIterable<T> newIterable(final T... initial) {");
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
  @Override
  public void test_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m()\t{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val list = newIterable");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.add(\'\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.forEach [");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <T extends CharSequence> MyIterable<T> newIterable(T... initial) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface MyIterable<T extends CharSequence> extends Iterable<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def void add(T t)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def T get()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.function.Consumer;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface MyIterable<T extends CharSequence> extends Iterable<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("void add(final T t);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("T get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C.MyIterable<String> list = C.<String>newIterable();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.add(\"\");");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Consumer<String> _function = (String it) -> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.forEach(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <T extends CharSequence> C.MyIterable<T> newIterable(final T... initial) {");
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
  @Override
  public void test_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m()\t{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val list = newIterable");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.get.toString");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.add(\'\')");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.forEach [");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <T extends CharSequence> MyIterable<T> newIterable(T... initial) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface MyIterable<T extends CharSequence> extends Iterable<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def void add(T t)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def T get()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.function.Consumer;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface MyIterable<T extends CharSequence> extends Iterable<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("void add(final T t);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("T get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C.MyIterable<CharSequence> list = C.<CharSequence>newIterable();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.get().toString();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.add(\"\");");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Consumer<CharSequence> _function = (CharSequence it) -> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.forEach(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <T extends CharSequence> C.MyIterable<T> newIterable(final T... initial) {");
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
  @Override
  public void test_06() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m()\t{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val list = newIterable");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.get.toString");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.forEach [");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (it instanceof String) list.add(it)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <T extends CharSequence> MyIterable<T> newIterable(T... initial) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface MyIterable<T> extends Iterable<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def void add(T t)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def T get()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.function.Consumer;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface MyIterable<T extends Object> extends Iterable<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("void add(final T t);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("T get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C.MyIterable<CharSequence> list = C.<CharSequence>newIterable();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.get().toString();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Consumer<CharSequence> _function = (CharSequence it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("if ((it instanceof String)) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("list.add(it);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.forEach(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <T extends CharSequence> C.MyIterable<T> newIterable(final T... initial) {");
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
  @Override
  public void test_07() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m()\t{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val list = newIterable");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.forEach [");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (it instanceof String) list.add(it)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <T extends CharSequence> MyIterable<T> newIterable(T... initial) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface MyIterable<T> extends Iterable<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def void add(T t)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def T get()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.function.Consumer;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface MyIterable<T extends Object> extends Iterable<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("void add(final T t);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("T get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C.MyIterable<CharSequence> list = C.<CharSequence>newIterable();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Consumer<CharSequence> _function = (CharSequence it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("if ((it instanceof String)) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("list.add(it);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.forEach(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <T extends CharSequence> C.MyIterable<T> newIterable(final T... initial) {");
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
  @Override
  public void test_08() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m()\t{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val list = newIterable");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.forEach [ CharSequence it |");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (it instanceof String) list.add(it)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <T> MyIterable<T> newIterable(T... initial) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface MyIterable<T> extends Iterable<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def void add(T t)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def T get()");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.util.function.Consumer;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface MyIterable<T extends Object> extends Iterable<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("void add(final T t);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("T get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C.MyIterable<CharSequence> list = C.<CharSequence>newIterable();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Consumer<CharSequence> _function = (CharSequence it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("if ((it instanceof String)) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("list.add(it);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.forEach(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <T extends Object> C.MyIterable<T> newIterable(final T... initial) {");
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
  @Override
  public void test_09() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import org.eclipse.xtend.core.tests.java8.compiler.StringBuilderLike");
    _builder.newLine();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m()\t{");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("newIterable(new StringBuilderLike).forEach2 [");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("m(it, new Long(0))");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("println(length)");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <T> m(T a, T b){}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static <T> MyIterable<T> newIterable(T... initial) {}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface MyIterable<T> extends Iterable<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def void forEach2((T)=>void f)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import java.io.Serializable;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtend.core.tests.java8.compiler.StringBuilderLike;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.InputOutput;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface MyIterable<T extends Object> extends Iterable<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("void forEach2(final Procedure1<? super T> f);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("StringBuilderLike _stringBuilderLike = new StringBuilderLike();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Procedure1<StringBuilderLike> _function = (StringBuilderLike it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("Long _long = new Long(0);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("this.<Serializable>m(it, _long);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("InputOutput.<Integer>println(Integer.valueOf(it.length()));");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("C.<StringBuilderLike>newIterable(_stringBuilderLike).forEach2(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <T extends Object> Object m(final T a, final T b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static <T extends Object> C.MyIterable<T> newIterable(final T... initial) {");
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
  @Override
  public void test_10() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <V> m(MyIterable<? super V> list) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("list.forEach2 []");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("interface MyIterable<T> extends Iterable<T> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("def void forEach2((T)=>void f)");
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
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public interface MyIterable<T extends Object> extends Iterable<T> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("void forEach2(final Procedure1<? super T> f);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <V extends Object> void m(final C.MyIterable<? super V> list) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Procedure1<Object> _function = (Object it) -> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("list.forEach2(_function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
