/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package extract;

import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableInterfaceDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class ExtractTest {
  @Extension
  private XtendCompilerTester compilerTester = XtendCompilerTester.newXtendCompilerTester(this.getClass().getClassLoader());
  
  @Test
  public void testExtractAnnotation() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("@extract.Extract");
    _builder.newLine();
    _builder.append("class MyClass {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("override doStuff(String myParam) throws IllegalArgumentException {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return myParam");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    final IAcceptor<XtendCompilerTester.CompilationResult> _function = (XtendCompilerTester.CompilationResult it) -> {
      @Extension
      final TransformationContext ctx = it.getTransformationContext();
      final MutableInterfaceDeclaration interf = ctx.findInterface("MyClassInterface");
      final MutableClassDeclaration clazz = ctx.findClass("MyClass");
      Iterable<? extends TypeReference> _implementedInterfaces = clazz.getImplementedInterfaces();
      TypeReference _head = IterableExtensions.head(_implementedInterfaces);
      Type _type = _head.getType();
      Assert.assertEquals(_type, interf);
      Iterable<? extends MutableMethodDeclaration> _declaredMethods = interf.getDeclaredMethods();
      MutableMethodDeclaration _head_1 = IterableExtensions.head(_declaredMethods);
      final Procedure1<MutableMethodDeclaration> _function_1 = (MutableMethodDeclaration it_1) -> {
        String _simpleName = it_1.getSimpleName();
        Assert.assertEquals("doStuff", _simpleName);
        TypeReference _string = ctx.getString();
        TypeReference _returnType = it_1.getReturnType();
        Assert.assertEquals(_string, _returnType);
        TypeReference _newTypeReference = ctx.newTypeReference(IllegalArgumentException.class);
        Iterable<? extends TypeReference> _exceptions = it_1.getExceptions();
        TypeReference _head_2 = IterableExtensions.head(_exceptions);
        Assert.assertEquals(_newTypeReference, _head_2);
      };
      ObjectExtensions.<MutableMethodDeclaration>operator_doubleArrow(_head_1, _function_1);
    };
    this.compilerTester.compile(_builder, _function);
  }
}
