/**
 * Copyright (c) 2015, 2017 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.macro;

import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;

@SuppressWarnings("all")
public class Bug464136Processor extends AbstractClassProcessor {
  @Override
  public void doTransform(final MutableClassDeclaration annotatedClass, @Extension final TransformationContext context) {
    final Procedure0 _function = () -> {
      throw new LinkageError("Just a test :-/");
    };
    context.validateLater(_function);
  }
}
