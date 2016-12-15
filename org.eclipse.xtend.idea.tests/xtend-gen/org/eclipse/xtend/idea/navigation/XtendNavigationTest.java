/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.idea.navigation;

import org.eclipse.xtend.core.xtend.AnonymousClass;
import org.eclipse.xtend.core.xtend.XtendClass;
import org.eclipse.xtend.core.xtend.XtendConstructor;
import org.eclipse.xtend.core.xtend.XtendEnum;
import org.eclipse.xtend.core.xtend.XtendEnumLiteral;
import org.eclipse.xtend.core.xtend.XtendField;
import org.eclipse.xtend.core.xtend.XtendFunction;
import org.eclipse.xtend.core.xtend.XtendInterface;
import org.eclipse.xtend.core.xtend.XtendParameter;
import org.eclipse.xtend.core.xtend.XtendVariableDeclaration;
import org.eclipse.xtend.idea.LightXtendTest;
import org.eclipse.xtend.idea.navigation.NavigationTestData;
import org.eclipse.xtend2.lib.StringConcatenation;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class XtendNavigationTest extends LightXtendTest {
  public void testNavigateToParameter() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Greeter {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void sayHello(String ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "\t");
    _builder.append("name");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "\t");
    _builder.append(") {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("println(na");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t");
    _builder.append("me)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<XtendParameter>testNavigateTo(
      "mypackage/Greeter.xtend", _builder.toString(), 
      XtendParameter.class);
  }
  
  public void testNavigateToClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "");
    _builder.append("Greeter");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String ... args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new ");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t");
    _builder.append("Greeter().sayHello(args.head)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void sayHello(String name) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("println(name)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<XtendClass>testNavigateTo(
      "mypackage/Greeter.xtend", _builder.toString(), 
      XtendClass.class);
  }
  
  public void testNavigateToClass_Extends() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "");
    _builder.append("Foo");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Bar extends ");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "");
    _builder.append("Foo {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    this.<XtendClass>testNavigateTo(
      "mypackage/Foo.xtend", _builder.toString(), 
      XtendClass.class);
  }
  
  public void testNavigateToClass_Implements() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("interface ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "");
    _builder.append("Foo");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Bar implements ");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "");
    _builder.append("Foo {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    this.<XtendInterface>testNavigateTo(
      "mypackage/Foo.xtend", _builder.toString(), 
      XtendInterface.class);
  }
  
  public void testNavigateToClass_Throws() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "");
    _builder.append("MyException");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "");
    _builder.append(" extends Exception {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Client {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void someMethod() throws My");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t");
    _builder.append("Exception {}");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<XtendClass>testNavigateTo(
      "mypackage/Exception.xtend", _builder.toString(), 
      XtendClass.class);
  }
  
  public void testNavigateToClass_ExtendsBound() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "");
    _builder.append("Foo");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Bar<T extends Fo");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "");
    _builder.append("o> {");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    this.<XtendClass>testNavigateTo(
      "mypackage/Foo.xtend", _builder.toString(), 
      XtendClass.class);
  }
  
  public void testNavigateToInnerClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static class ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "\t");
    _builder.append("Bar");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "\t");
    _builder.append(" {}");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("static class Baz extends ");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t");
    _builder.append("Bar {}");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    this.<XtendClass>testNavigateTo(
      "mypackage/Foo.xtend", _builder.toString(), 
      XtendClass.class);
  }
  
  public void testNavigateToAnnonymousClass() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("interface Greeter {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void sayHello(String name)");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String ... args) {");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("new Greeter() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("override sayHello(String name) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t\t\t");
    _builder.append("this.sayHello(name)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("new Greeter() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("override sayHello(String name) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("throw new UnsupportedOperationException(\"TODO: auto-generated method stub\")");
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
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<AnonymousClass>testNavigateTo(
      "mypackage/Foo.xtend", _builder.toString(), 
      AnonymousClass.class);
  }
  
  public void testNavigateToAnnonymousClass2() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("interface Greeter {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void sayHello(String name)");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String ... args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new Greeter() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("override sayHello(String name) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("throw new UnsupportedOperationException(\"TODO: auto-generated method stub\")");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("new Greeter() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("override sayHello(String name) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t\t\t");
    _builder.append("this.sayHello(name)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<AnonymousClass>testNavigateTo(
      "mypackage/Foo.xtend", _builder.toString(), 
      AnonymousClass.class);
  }
  
  public void testNavigateToAnnonymousClass3() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("interface ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "");
    _builder.append("Greeter");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("def void sayHello(String name)");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String ... args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("acceptGreeter [ String name |");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t\t");
    _builder.append("self.sayHello(name)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new Greeter() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("override sayHello(String name) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("throw new UnsupportedOperationException(\"TODO: auto-generated method stub\")");
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
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void acceptGreeter(Greeter greeter) {}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<XtendInterface>testNavigateTo(
      "mypackage/Foo.xtend", _builder.toString(), 
      XtendInterface.class);
  }
  
  public void testNavigateToAnnonymousClass4() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("abstract class Greeter {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("abstract def void sayHello(String name)");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String ... args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("acceptGreeter [ String name |");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("throw new UnsupportedOperationException(\"TODO: auto-generated method stub\")");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("new Greeter() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("override sayHello(String name) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t\t\t");
    _builder.append("this.sayHello(name)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void acceptGreeter(Greeter greeter) {}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<AnonymousClass>testNavigateTo(
      "mypackage/Foo.xtend", _builder.toString(), 
      AnonymousClass.class);
  }
  
  public void testNavigateToAnnonymousClass5() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("abstract class Greeter {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("abstract def void sayHello(String name)");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static val greeter =");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "\t");
    _builder.append(" new Greeter() {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("override sayHello(String name) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t\t\t");
    _builder.append("this.sayHello(name)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "\t\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String ... args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("greeter.sayHello(args.head)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<AnonymousClass>testNavigateTo(
      "mypackage/Foo.xtend", _builder.toString(), 
      AnonymousClass.class);
  }
  
  public void testNavigateToEnumeration() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("enum ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "");
    _builder.append("Light");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "");
    _builder.append(" {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("ON, OFF");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Room {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t");
    _builder.append("Light light");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void enable() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("light = Light.ON");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<XtendEnum>testNavigateTo(
      "mypackage/Foo.xtend", _builder.toString(), 
      XtendEnum.class);
  }
  
  public void testNavigateToEnumConstant() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("enum Light {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "\t");
    _builder.append("ON");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "\t");
    _builder.append(", OFF");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Room {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Light light");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void enable() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("light = Light.");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t");
    _builder.append("ON");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<XtendEnumLiteral>testNavigateTo(
      "mypackage/Foo.xtend", _builder.toString(), 
      XtendEnumLiteral.class);
  }
  
  public void testNavigateToConstructor() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Greeter {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String ... args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new ");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t");
    _builder.append("Greeter(args.head)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(String name) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("println(name)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<XtendConstructor>testNavigateTo(
      "mypackage/Greeter.xtend", _builder.toString(), 
      XtendConstructor.class);
  }
  
  public void testNavigateToMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Greeter {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def static void main(String ... args) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new Greeter().");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t");
    _builder.append("sayHello(args.head)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "\t");
    _builder.append("sayHello");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "\t");
    _builder.append("(String name) {");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("println(name)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<XtendFunction>testNavigateTo(
      "mypackage/Greeter.xtend", _builder.toString(), 
      XtendFunction.class);
  }
  
  public void testNavigateToAnnotationMethod() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("annotation MyAnnotation {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Class<?> ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "\t");
    _builder.append("type");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "\t");
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def getType(MyAnnotation myAnnotation) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("myAnnotation.ty");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t");
    _builder.append("pe()");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<XtendField>testNavigateTo(
      "mypackage/MyAnnotation.xtend", _builder.toString(), 
      XtendField.class);
  }
  
  public void testNavigateToField() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class Greeter {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "\t");
    _builder.append("name");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "\t");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def void sayHello() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("println(\'Hello \' + na");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t");
    _builder.append("me)");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<XtendField>testNavigateTo(
      "mypackage/MyAnnotation.xtend", _builder.toString(), 
      XtendField.class);
  }
  
  public void testNavigateToVariable() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package mypackage");
    _builder.newLine();
    _builder.newLine();
    _builder.append("class MyFunction {");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def calc() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val ");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_START_OFFSET, "\t\t");
    _builder.append("x");
    _builder.append(NavigationTestData.NAVIGATION_ELEMENT_END_OFFSET, "\t\t");
    _builder.append(" = 1");
    _builder.newLineIfNotEmpty();
    _builder.append("\t\t");
    _builder.append("val y = 1");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(NavigationTestData.REFERENCE_OFFSET, "\t\t");
    _builder.append("x + y");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    this.<XtendVariableDeclaration>testNavigateTo(
      "mypackage/MyFunction.xtend", _builder.toString(), 
      XtendVariableDeclaration.class);
  }
  
  protected <T extends Object> T testNavigateTo(final String relativePath, final String fileText, final Class<T> expectedType) {
    throw new Error("Unresolved compilation problems:"
      + "\nTextRange cannot be resolved."
      + "\nThe method openFileInEditor(String, String) from the type XtendNavigationTest refers to the missing type Object"
      + "\nThe method findNavigationElement(TextRange) from the type XtendNavigationTest refers to the missing type Object"
      + "\nThe method assertReference(int, Class<T>, PsiElement) from the type XtendNavigationTest refers to the missing type PsiElement");
  }
  
  protected <T extends Object> T assertReference(final int referenceOffset, final Class<T> expectedType, final /* PsiElement */Object expectedNavigationElement) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field myFixture is undefined"
      + "\nThe method or field myFixture is undefined"
      + "\nThe method assertNotNull(Object) is undefined"
      + "\nThe method assertEquals(Object, PsiElement, Object) is undefined"
      + "\nThe method or field DebugUtil is undefined"
      + "\nThe method or field file is undefined"
      + "\nThe method assertTrue(String, boolean) is undefined"
      + "\neditor cannot be resolved"
      + "\ncaretModel cannot be resolved"
      + "\nmoveToOffset cannot be resolved"
      + "\nelementAtCaret cannot be resolved"
      + "\nnavigationElement cannot be resolved"
      + "\n!= cannot be resolved"
      + "\npsiToString cannot be resolved");
  }
  
  protected Object findNavigationElement(final /* TextRange */Object range) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field file is undefined"
      + "\nThe method assertNotNull(Object) is undefined"
      + "\nThe method assertEquals(Object, Object) is undefined"
      + "\nThe method assertNotNull(Object) is undefined"
      + "\nThe method assertEquals(Object, Object) is undefined"
      + "\nfindElementAt cannot be resolved"
      + "\nstartOffset cannot be resolved"
      + "\nfindPsiNamedEObject cannot be resolved"
      + "\n!== cannot be resolved"
      + "\nnavigationElement cannot be resolved"
      + "\ntextRange cannot be resolved"
      + "\n!= cannot be resolved"
      + "\nparent cannot be resolved"
      + "\nnavigationElement cannot be resolved");
  }
  
  protected /* PsiElement */Object findPsiNamedEObject(final /* PsiElement */Object element, final /* TextRange */Object identifierRange) {
    throw new Error("Unresolved compilation problems:"
      + "\n=== cannot be resolved"
      + "\nnameIdentifier cannot be resolved"
      + "\n!== cannot be resolved"
      + "\ntextRange cannot be resolved"
      + "\n== cannot be resolved"
      + "\nparent cannot be resolved"
      + "\nfindPsiNamedEObject cannot be resolved");
  }
  
  protected Object openFileInEditor(final String relativePath, final String fileText) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field myFixture is undefined"
      + "\nThe method or field myFixture is undefined"
      + "\nThe method or field myFixture is undefined"
      + "\naddFileToProject cannot be resolved"
      + "\ntestHighlighting cannot be resolved"
      + "\nvirtualFile cannot be resolved"
      + "\nopenFileInEditor cannot be resolved"
      + "\nvirtualFile cannot be resolved");
  }
}
