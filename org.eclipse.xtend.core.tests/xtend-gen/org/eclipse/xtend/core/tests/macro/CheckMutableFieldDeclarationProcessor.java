/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.macro;

import org.eclipse.xtend.core.tests.macro.MutableAssert;
import org.eclipse.xtend.lib.macro.AbstractFieldProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;

@SuppressWarnings("all")
public class CheckMutableFieldDeclarationProcessor extends AbstractFieldProcessor {
  @Override
  public void doTransform(final MutableFieldDeclaration annotatedField, @Extension final TransformationContext context) {
    final Procedure0 _function = () -> {
      annotatedField.setInitializer(((CompilationStrategy) null));
    };
    MutableAssert.<IllegalArgumentException>assertThrowable(IllegalArgumentException.class, "initializer cannot be null", _function);
    final Procedure0 _function_1 = () -> {
      annotatedField.setType(null);
    };
    MutableAssert.<IllegalArgumentException>assertThrowable(IllegalArgumentException.class, "type cannot be null", _function_1);
  }
}
