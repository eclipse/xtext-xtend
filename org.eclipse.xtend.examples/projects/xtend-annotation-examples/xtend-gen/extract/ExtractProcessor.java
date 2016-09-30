/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package extract;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.Collections;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableInterfaceDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ExtractProcessor extends AbstractClassProcessor {
  @Override
  public void doRegisterGlobals(final ClassDeclaration annotatedClass, final RegisterGlobalsContext context) {
    String _interfaceName = this.getInterfaceName(annotatedClass);
    context.registerInterface(_interfaceName);
  }
  
  public String getInterfaceName(final ClassDeclaration annotatedClass) {
    String _qualifiedName = annotatedClass.getQualifiedName();
    return (_qualifiedName + "Interface");
  }
  
  @Override
  public void doTransform(final MutableClassDeclaration annotatedClass, @Extension final TransformationContext context) {
    String _interfaceName = this.getInterfaceName(annotatedClass);
    final MutableInterfaceDeclaration interfaceType = context.findInterface(_interfaceName);
    context.setPrimarySourceElement(interfaceType, annotatedClass);
    Iterable<? extends TypeReference> _implementedInterfaces = annotatedClass.getImplementedInterfaces();
    TypeReference _newTypeReference = context.newTypeReference(interfaceType);
    Iterable<TypeReference> _plus = Iterables.<TypeReference>concat(_implementedInterfaces, Collections.<TypeReference>unmodifiableList(CollectionLiterals.<TypeReference>newArrayList(_newTypeReference)));
    annotatedClass.setImplementedInterfaces(_plus);
    Iterable<? extends MutableMethodDeclaration> _declaredMethods = annotatedClass.getDeclaredMethods();
    for (final MutableMethodDeclaration method : _declaredMethods) {
      Visibility _visibility = method.getVisibility();
      boolean _equals = Objects.equal(_visibility, Visibility.PUBLIC);
      if (_equals) {
        String _simpleName = method.getSimpleName();
        final Procedure1<MutableMethodDeclaration> _function = (MutableMethodDeclaration it) -> {
          String _docComment = method.getDocComment();
          it.setDocComment(_docComment);
          TypeReference _returnType = method.getReturnType();
          it.setReturnType(_returnType);
          Iterable<? extends MutableParameterDeclaration> _parameters = method.getParameters();
          for (final MutableParameterDeclaration p : _parameters) {
            String _simpleName_1 = p.getSimpleName();
            TypeReference _type = p.getType();
            it.addParameter(_simpleName_1, _type);
          }
          Iterable<? extends TypeReference> _exceptions = method.getExceptions();
          it.setExceptions(((TypeReference[])Conversions.unwrapArray(_exceptions, TypeReference.class)));
          context.setPrimarySourceElement(it, method);
        };
        interfaceType.addMethod(_simpleName, _function);
      }
    }
  }
}
