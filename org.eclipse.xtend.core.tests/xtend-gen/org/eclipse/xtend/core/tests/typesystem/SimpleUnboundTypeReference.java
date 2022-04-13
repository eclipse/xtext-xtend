/**
 * Copyright (c) 2012, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.typesystem;

import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.xbase.typesystem.references.ITypeReferenceOwner;
import org.eclipse.xtext.xbase.typesystem.references.UnboundTypeReference;

@SuppressWarnings("all")
public class SimpleUnboundTypeReference extends UnboundTypeReference {
  public SimpleUnboundTypeReference(final ITypeReferenceOwner owner, final JvmTypeParameter typeParameter, final Object handle) {
    super(owner, null, typeParameter, handle);
  }

  @Override
  protected UnboundTypeReference createCopy(final ITypeReferenceOwner owner) {
    JvmTypeParameter _typeParameter = this.getTypeParameter();
    Object _handle = this.getHandle();
    return new SimpleUnboundTypeReference(owner, _typeParameter, _handle);
  }
}
