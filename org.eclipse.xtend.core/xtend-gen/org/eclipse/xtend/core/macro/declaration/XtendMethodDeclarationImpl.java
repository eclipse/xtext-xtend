/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.macro.declaration;

import org.eclipse.xtend.core.macro.declaration.XtendMemberDeclarationImpl;
import org.eclipse.xtend.core.macro.declaration.XtendParameterDeclarationImpl;
import org.eclipse.xtend.core.macro.declaration.XtendTypeParameterDeclarationImpl;
import org.eclipse.xtend.core.xtend.XtendFunction;
import org.eclipse.xtend.core.xtend.XtendParameter;
import org.eclipse.xtend.lib.macro.declaration.MethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.declaration.Visibility;
import org.eclipse.xtend.lib.macro.expression.Expression;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class XtendMethodDeclarationImpl extends XtendMemberDeclarationImpl<XtendFunction> implements MethodDeclaration {
  @Override
  public boolean isAbstract() {
    XExpression _expression = this.getDelegate().getExpression();
    return (_expression == null);
  }
  
  @Override
  public boolean isFinal() {
    return this.getDelegate().isFinal();
  }
  
  public boolean isOverride() {
    return this.getDelegate().isOverride();
  }
  
  @Override
  public boolean isStatic() {
    return this.getDelegate().isStatic();
  }
  
  @Override
  public boolean isSynchronized() {
    return false;
  }
  
  @Override
  public boolean isDefault() {
    return false;
  }
  
  @Override
  public boolean isStrictFloatingPoint() {
    return false;
  }
  
  @Override
  public boolean isNative() {
    return false;
  }
  
  @Override
  public TypeReference getReturnType() {
    return this.getCompilationUnit().toTypeReference(this.getDelegate().getReturnType());
  }
  
  @Override
  public Visibility getVisibility() {
    return this.getCompilationUnit().toVisibility(this.getDelegate().getVisibility());
  }
  
  @Override
  public String getSimpleName() {
    return this.getDelegate().getName();
  }
  
  @Override
  public Expression getBody() {
    XExpression _expression = this.getDelegate().getExpression();
    boolean _tripleEquals = (_expression == null);
    if (_tripleEquals) {
      return null;
    }
    return this.getCompilationUnit().toExpression(this.getDelegate().getExpression());
  }
  
  @Override
  public boolean isVarArgs() {
    final Function1<XtendParameter, Boolean> _function = (XtendParameter it) -> {
      return Boolean.valueOf(this.isVarArgs());
    };
    return IterableExtensions.<XtendParameter>exists(this.getDelegate().getParameters(), _function);
  }
  
  @Override
  public Iterable<? extends TypeParameterDeclaration> getTypeParameters() {
    final Function1<JvmTypeParameter, XtendTypeParameterDeclarationImpl> _function = (JvmTypeParameter it) -> {
      return this.getCompilationUnit().toXtendTypeParameterDeclaration(it);
    };
    return ListExtensions.<JvmTypeParameter, XtendTypeParameterDeclarationImpl>map(this.getDelegate().getTypeParameters(), _function);
  }
  
  @Override
  public Iterable<? extends TypeReference> getExceptions() {
    final Function1<JvmTypeReference, TypeReference> _function = (JvmTypeReference it) -> {
      return this.getCompilationUnit().toTypeReference(it);
    };
    return ListExtensions.<JvmTypeReference, TypeReference>map(this.getDelegate().getExceptions(), _function);
  }
  
  @Override
  public Iterable<? extends ParameterDeclaration> getParameters() {
    final Function1<XtendParameter, XtendParameterDeclarationImpl> _function = (XtendParameter it) -> {
      return this.getCompilationUnit().toXtendParameterDeclaration(it);
    };
    return ListExtensions.<XtendParameter, XtendParameterDeclarationImpl>map(this.getDelegate().getParameters(), _function);
  }
}
