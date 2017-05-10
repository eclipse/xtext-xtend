/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package testdata;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 */
@SuppressWarnings("unused")
public class StaticVisibilitySubClass extends StaticVisibilitySuperType
{
  private String getSubPrivateProperty() { return null; }
	private String privateField;
	private void setPrivateProperty(String x) {}
}
