/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.ide.codebuilder;

import org.eclipse.jdt.core.IType;
import org.eclipse.xtend.ide.codebuilder.AbstractMethodBuilder;
import org.eclipse.xtend.ide.codebuilder.ICodeBuilder;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.xbase.compiler.ISourceAppender;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
@SuppressWarnings("all")
public class JavaMethodBuilder extends AbstractMethodBuilder implements ICodeBuilder.Java {
  @Override
  public boolean isValid() {
    return (super.isValid() && (this.getMethodName() != null));
  }
  
  @Override
  public ISourceAppender build(final ISourceAppender appendable) {
    ISourceAppender _xblockexpression = null;
    {
      boolean _isOverrideFlag = this.isOverrideFlag();
      if (_isOverrideFlag) {
        appendable.append("@Override").newLine();
      }
      this.appendVisibility(appendable, this.getVisibility(), JvmVisibility.DEFAULT);
      boolean _isAbstractFlag = this.isAbstractFlag();
      if (_isAbstractFlag) {
        appendable.append("abstract ");
      }
      boolean _isStaticFlag = this.isStaticFlag();
      if (_isStaticFlag) {
        appendable.append("static ");
      }
      boolean _isSynchronizedFlag = this.isSynchronizedFlag();
      if (_isSynchronizedFlag) {
        appendable.append("synchronized ");
      }
      this.appendThrowsClause(this.appendParameters(this.appendType(this.appendTypeParameters(appendable, this.getTypeParameters()), this.getReturnType(), "void").append(" ").append(this.getMethodName())));
      boolean _isAbstractFlag_1 = this.isAbstractFlag();
      if (_isAbstractFlag_1) {
        appendable.append(";");
      } else {
        this.appendBody(appendable, ";");
      }
      _xblockexpression = appendable;
    }
    return _xblockexpression;
  }
  
  @Override
  public IType getIType() {
    Object _ownerSource = this.getOwnerSource();
    return ((IType) _ownerSource);
  }
}
