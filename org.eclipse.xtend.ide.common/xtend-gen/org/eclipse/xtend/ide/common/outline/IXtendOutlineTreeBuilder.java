/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.ide.common.outline;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.ide.common.outline.IXtendOutlineContext;

/**
 * <p>
 * Builds an outline tree structure for the given model element.
 * </p>
 * <p>
 * An implementation should delegate to {@link IXtendOutlineNodeBuilder} in order to build platform specific outline nodes.
 * </p>
 * 
 * @author kosyakov - Initial contribution and API
 * @see IXtendOutlineNodeBuilder
 */
@SuppressWarnings("all")
public interface IXtendOutlineTreeBuilder {
  void build(final EObject modelElement, final IXtendOutlineContext context);
}
