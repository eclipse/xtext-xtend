/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.macro.declaration;

import org.eclipse.xtend.core.macro.declaration.XtendMemberDeclarationImpl;
import org.eclipse.xtend.core.xtend.XtendField;
import org.eclipse.xtend.lib.macro.declaration.AnnotationTypeElementDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend.lib.macro.expression.Expression;
import org.eclipse.xtext.xbase.XExpression;

@SuppressWarnings("all")
public class XtendAnnotationTypeElementDeclarationImpl extends XtendMemberDeclarationImpl<XtendField> implements AnnotationTypeElementDeclaration {
  @Override
  public String getSimpleName() {
    return this.getDelegate().getName();
  }
  
  @Override
  public Object getDefaultValue() {
    Object _xblockexpression = null;
    {
      XExpression _initialValue = this.getDelegate().getInitialValue();
      boolean _tripleEquals = (_initialValue == null);
      if (_tripleEquals) {
        return null;
      }
      _xblockexpression = this.getCompilationUnit().evaluate(this.getDelegate().getInitialValue(), this.getDelegate().getType());
    }
    return _xblockexpression;
  }
  
  @Override
  public Expression getDefaultValueExpression() {
    Expression _xblockexpression = null;
    {
      XExpression _initialValue = this.getDelegate().getInitialValue();
      boolean _tripleEquals = (_initialValue == null);
      if (_tripleEquals) {
        return null;
      }
      _xblockexpression = this.getCompilationUnit().toExpression(this.getDelegate().getInitialValue());
    }
    return _xblockexpression;
  }
  
  @Override
  public TypeReference getType() {
    return this.getCompilationUnit().toTypeReference(this.getDelegate().getType());
  }
}
