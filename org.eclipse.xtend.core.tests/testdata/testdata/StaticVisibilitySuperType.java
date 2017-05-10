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
public class StaticVisibilitySuperType {
	static private String staticPrivateField;
	static protected String staticProtectedField;
	static public String staticPublicField;
	
	static private void staticPrivateMethod(){}
	static protected void staticProtectedMethod(){}
	static public void staticPublicMethod(){}
	
	static private void setStaticPrivateProperty(String x) {}
	static protected void setStaticProtectedProperty(String x) {}
	static public void setStaticPublicProperty(String x) {}
	
	private String privateField;
	protected String protectedField;
	public String publicField;
	
	private void privateMethod(){}
	protected void protectedMethod(){}
	public void publicMethod(){}
	
	private void setPrivateProperty(String x) {}
	protected void setProtectedProperty(String x) {}
	public void setPublicProperty(String x) {}
}
