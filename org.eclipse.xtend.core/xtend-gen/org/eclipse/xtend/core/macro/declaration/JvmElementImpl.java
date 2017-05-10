/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.macro.declaration;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtend.core.macro.ActiveAnnotationContexts;
import org.eclipse.xtend.core.macro.declaration.AbstractElementImpl;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public abstract class JvmElementImpl<T extends EObject> extends AbstractElementImpl<T> {
  public void remove() {
    this.checkMutable();
    Resource _eResource = this.getDelegate().eResource();
    boolean _tripleNotEquals = (_eResource != null);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("This element has already been removed: ");
    T _delegate = this.getDelegate();
    _builder.append(_delegate);
    Preconditions.checkState(_tripleNotEquals, _builder);
    this.getCompilationUnit().getJvmModelAssociator().removeAllAssociation(this.getDelegate());
    EcoreUtil.remove(this.getDelegate());
    Resource _eResource_1 = this.getDelegate().eResource();
    boolean _tripleEquals = (_eResource_1 == null);
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("Couldn\'t remove: ");
    T _delegate_1 = this.getDelegate();
    _builder_1.append(_delegate_1);
    Preconditions.checkState(_tripleEquals, _builder_1);
  }
  
  protected final void checkMutable() {
    ActiveAnnotationContexts.AnnotationCallback _lastPhase = this.getCompilationUnit().getLastPhase();
    boolean _notEquals = (!Objects.equal(_lastPhase, ActiveAnnotationContexts.AnnotationCallback.INFERENCE));
    if (_notEquals) {
      throw new IllegalStateException("Element cannot be modified outside the transformation phase");
    }
  }
}
