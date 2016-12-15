/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.idea.structureview;

import java.util.List;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class XtendShowInheritedNodeProvider /* implements NodeProvider<TreeElement>  */{
  public final static String ID = "XTEND_SHOW_INHERITED";
  
  @Override
  public List<Object> provideNodes(final /* TreeElement */Object node) {
    return CollectionLiterals.<Object>emptyList();
  }
  
  @Override
  public String getName() {
    return XtendShowInheritedNodeProvider.ID;
  }
  
  @Override
  public Object getPresentation() {
    throw new Error("Unresolved compilation problems:"
      + "\nActionPresentationData cannot be resolved."
      + "\nThe method or field IdeBundle is undefined"
      + "\nThe method or field AllIcons is undefined"
      + "\nmessage cannot be resolved"
      + "\nHierarchy cannot be resolved"
      + "\nSupertypes cannot be resolved");
  }
}
