/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.idea.structureview;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class SyntheticMemberFilter /* implements Filter  */{
  public final static String ID = "SHOW_NO_SYNTHETIC";
  
  @Override
  public boolean isReverted() {
    return true;
  }
  
  @Override
  public Object isVisible(final /* TreeElement */Object treeNode) {
    throw new Error("Unresolved compilation problems:"
      + "\nsynthetic cannot be resolved"
      + "\n! cannot be resolved");
  }
  
  @Override
  public String getName() {
    return SyntheticMemberFilter.ID;
  }
  
  @Override
  public Object getPresentation() {
    throw new Error("Unresolved compilation problems:"
      + "\nActionPresentationData cannot be resolved.");
  }
}
