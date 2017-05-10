/**
 * Copyright (c) 2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.tests.macro;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.eclipse.xtend.core.tests.macro.ToAnnoProcessor;
import org.eclipse.xtend.lib.macro.Active;

@Active(ToAnnoProcessor.class)
@Target(ElementType.TYPE)
public @interface ToAnno {
  public boolean defaultValue() default true;
}
