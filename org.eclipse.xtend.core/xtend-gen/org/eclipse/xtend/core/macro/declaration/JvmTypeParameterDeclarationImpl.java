/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.macro.declaration;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.core.macro.declaration.TypeParameterDeclarationImpl;
import org.eclipse.xtend.lib.macro.declaration.AnnotationTarget;
import org.eclipse.xtend.lib.macro.declaration.MemberDeclaration;
import org.eclipse.xtend.lib.macro.declaration.Type;
import org.eclipse.xtend.lib.macro.declaration.TypeParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeParameterDeclarator;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtext.common.types.JvmExecutable;

@SuppressWarnings("all")
public class JvmTypeParameterDeclarationImpl extends TypeParameterDeclarationImpl implements AnnotationTarget, TypeParameterDeclaration {
  @Override
  public TypeParameterDeclarator getTypeParameterDeclarator() {
    EObject _eContainer = this.getDelegate().eContainer();
    MemberDeclaration _memberDeclaration = this.getCompilationUnit().toMemberDeclaration(((JvmExecutable) _eContainer));
    return ((TypeParameterDeclarator) _memberDeclaration);
  }
  
  @Override
  public boolean isAssignableFrom(final Type otherType) {
    if ((otherType == null)) {
      return false;
    }
    final TypeReference thisTypeRef = this.getCompilationUnit().getTypeReferenceProvider().newTypeReference(this);
    final TypeReference thatTypeRef = this.getCompilationUnit().getTypeReferenceProvider().newTypeReference(otherType);
    return thisTypeRef.isAssignableFrom(thatTypeRef);
  }
  
  @Override
  public String getQualifiedName() {
    return this.getDelegate().getIdentifier();
  }
}
