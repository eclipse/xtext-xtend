/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.macro.declaration;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend.core.macro.ConditionUtils;
import org.eclipse.xtend.core.macro.declaration.JvmInterfaceDeclarationImpl;
import org.eclipse.xtend.lib.macro.declaration.AnnotationTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ConstructorDeclaration;
import org.eclipse.xtend.lib.macro.declaration.EnumerationTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.FieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.InterfaceDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MemberDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableAnnotationTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableConstructorDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableEnumerationTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableInterfaceDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMemberDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableTypeParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtext.common.types.JvmTypeParameter;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmUpperBound;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.xbase.lib.Conversions;

@SuppressWarnings("all")
public class MutableJvmInterfaceDeclarationImpl extends JvmInterfaceDeclarationImpl implements MutableInterfaceDeclaration {
  @Override
  public void markAsRead() {
    this.checkMutable();
    this.getCompilationUnit().getReadAndWriteTracking().markReadAccess(this.getDelegate());
  }
  
  @Override
  public MutableMethodDeclaration findDeclaredMethod(final String name, final TypeReference... parameterTypes) {
    MethodDeclaration _findDeclaredMethod = super.findDeclaredMethod(name, parameterTypes);
    return ((MutableMethodDeclaration) _findDeclaredMethod);
  }
  
  @Override
  public MutableFieldDeclaration findDeclaredField(final String name) {
    FieldDeclaration _findDeclaredField = super.findDeclaredField(name);
    return ((MutableFieldDeclaration) _findDeclaredField);
  }
  
  @Override
  public MutableTypeDeclaration findDeclaredType(final String name) {
    TypeDeclaration _findDeclaredType = super.findDeclaredType(name);
    return ((MutableTypeDeclaration) _findDeclaredType);
  }
  
  @Override
  public MutableConstructorDeclaration findDeclaredConstructor(final TypeReference... parameterTypes) {
    ConstructorDeclaration _findDeclaredConstructor = super.findDeclaredConstructor(parameterTypes);
    return ((MutableConstructorDeclaration) _findDeclaredConstructor);
  }
  
  @Override
  public Iterable<? extends MutableMethodDeclaration> getDeclaredMethods() {
    Iterable<? extends MethodDeclaration> _declaredMethods = super.getDeclaredMethods();
    return ((Iterable<? extends MutableMethodDeclaration>) _declaredMethods);
  }
  
  @Override
  public Iterable<? extends MutableFieldDeclaration> getDeclaredFields() {
    Iterable<? extends FieldDeclaration> _declaredFields = super.getDeclaredFields();
    return ((Iterable<? extends MutableFieldDeclaration>) _declaredFields);
  }
  
  @Override
  public Iterable<? extends MutableClassDeclaration> getDeclaredClasses() {
    Iterable<? extends ClassDeclaration> _declaredClasses = super.getDeclaredClasses();
    return ((Iterable<? extends MutableClassDeclaration>) _declaredClasses);
  }
  
  @Override
  public Iterable<? extends MutableConstructorDeclaration> getDeclaredConstructors() {
    Iterable<? extends ConstructorDeclaration> _declaredConstructors = super.getDeclaredConstructors();
    return ((Iterable<? extends MutableConstructorDeclaration>) _declaredConstructors);
  }
  
  @Override
  public Iterable<? extends MutableInterfaceDeclaration> getDeclaredInterfaces() {
    Iterable<? extends InterfaceDeclaration> _declaredInterfaces = super.getDeclaredInterfaces();
    return ((Iterable<? extends MutableInterfaceDeclaration>) _declaredInterfaces);
  }
  
  @Override
  public Iterable<? extends MutableAnnotationTypeDeclaration> getDeclaredAnnotationTypes() {
    Iterable<? extends AnnotationTypeDeclaration> _declaredAnnotationTypes = super.getDeclaredAnnotationTypes();
    return ((Iterable<? extends MutableAnnotationTypeDeclaration>) _declaredAnnotationTypes);
  }
  
  @Override
  public Iterable<? extends MutableEnumerationTypeDeclaration> getDeclaredEnumerationTypes() {
    Iterable<? extends EnumerationTypeDeclaration> _declaredEnumerationTypes = super.getDeclaredEnumerationTypes();
    return ((Iterable<? extends MutableEnumerationTypeDeclaration>) _declaredEnumerationTypes);
  }
  
  @Override
  public Iterable<? extends MutableTypeDeclaration> getDeclaredTypes() {
    Iterable<? extends TypeDeclaration> _declaredTypes = super.getDeclaredTypes();
    return ((Iterable<? extends MutableTypeDeclaration>) _declaredTypes);
  }
  
  @Override
  public MutableTypeDeclaration getDeclaringType() {
    TypeDeclaration _declaringType = super.getDeclaringType();
    return ((MutableTypeDeclaration) _declaringType);
  }
  
  @Override
  public Iterable<? extends MutableMemberDeclaration> getDeclaredMembers() {
    Iterable<? extends MemberDeclaration> _declaredMembers = super.getDeclaredMembers();
    return ((Iterable<? extends MutableMemberDeclaration>) _declaredMembers);
  }
  
  @Override
  public Iterable<? extends MutableTypeParameterDeclaration> getTypeParameters() {
    Iterable<? extends TypeParameterDeclaration> _typeParameters = super.getTypeParameters();
    return ((Iterable<? extends MutableTypeParameterDeclaration>) _typeParameters);
  }
  
  @Override
  public void setExtendedInterfaces(final Iterable<? extends TypeReference> superinterfaces) {
    this.checkMutable();
    ConditionUtils.checkIterable(superinterfaces, "superinterfaces");
    this.getDelegate().getSuperTypes().clear();
    for (final TypeReference typeRef : superinterfaces) {
      {
        boolean _isInferred = typeRef.isInferred();
        if (_isInferred) {
          throw new IllegalArgumentException("Cannot use inferred type as extended interface.");
        }
        EList<JvmTypeReference> _superTypes = this.getDelegate().getSuperTypes();
        JvmTypeReference _jvmTypeReference = this.getCompilationUnit().toJvmTypeReference(typeRef);
        _superTypes.add(_jvmTypeReference);
      }
    }
  }
  
  @Override
  public void setStrictFloatingPoint(final boolean isStrictFloatingPoint) {
    this.checkMutable();
    this.getDelegate().setStrictFloatingPoint(isStrictFloatingPoint);
  }
  
  @Override
  public MutableTypeParameterDeclaration addTypeParameter(final String name, final TypeReference... upperBounds) {
    this.checkMutable();
    ConditionUtils.checkJavaIdentifier(name, "name");
    ConditionUtils.checkIterable(((Iterable<?>)Conversions.doWrapArray(upperBounds)), "upperBounds");
    ConditionUtils.checkInferredTypeReferences("parameter type", upperBounds);
    final JvmTypeParameter param = TypesFactory.eINSTANCE.createJvmTypeParameter();
    param.setName(name);
    this.getDelegate().getTypeParameters().add(param);
    for (final TypeReference upper : upperBounds) {
      {
        final JvmTypeReference typeRef = this.getCompilationUnit().toJvmTypeReference(upper);
        final JvmUpperBound jvmUpperBound = TypesFactory.eINSTANCE.createJvmUpperBound();
        jvmUpperBound.setTypeReference(typeRef);
        param.getConstraints().add(jvmUpperBound);
      }
    }
    TypeParameterDeclaration _typeParameterDeclaration = this.getCompilationUnit().toTypeParameterDeclaration(param);
    return ((MutableTypeParameterDeclaration) _typeParameterDeclaration);
  }
}
