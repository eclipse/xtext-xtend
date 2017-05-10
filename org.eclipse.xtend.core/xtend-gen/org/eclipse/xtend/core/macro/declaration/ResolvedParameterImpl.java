/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.macro.declaration;

import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtend.lib.macro.declaration.ParameterDeclaration;
import org.eclipse.xtend.lib.macro.declaration.ResolvedParameter;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Pure;

@Data
@SuppressWarnings("all")
public class ResolvedParameterImpl implements ResolvedParameter {
  private final ParameterDeclaration declaration;
  
  private final TypeReference resolvedType;
  
  @Override
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(this.resolvedType);
    _builder.append(" ");
    String _simpleName = this.declaration.getSimpleName();
    _builder.append(_simpleName);
    return _builder.toString();
  }
  
  public ResolvedParameterImpl(final ParameterDeclaration declaration, final TypeReference resolvedType) {
    super();
    this.declaration = declaration;
    this.resolvedType = resolvedType;
  }
  
  @Override
  @Pure
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.declaration== null) ? 0 : this.declaration.hashCode());
    result = prime * result + ((this.resolvedType== null) ? 0 : this.resolvedType.hashCode());
    return result;
  }
  
  @Override
  @Pure
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ResolvedParameterImpl other = (ResolvedParameterImpl) obj;
    if (this.declaration == null) {
      if (other.declaration != null)
        return false;
    } else if (!this.declaration.equals(other.declaration))
      return false;
    if (this.resolvedType == null) {
      if (other.resolvedType != null)
        return false;
    } else if (!this.resolvedType.equals(other.resolvedType))
      return false;
    return true;
  }
  
  @Pure
  public ParameterDeclaration getDeclaration() {
    return this.declaration;
  }
  
  @Pure
  public TypeReference getResolvedType() {
    return this.resolvedType;
  }
}
