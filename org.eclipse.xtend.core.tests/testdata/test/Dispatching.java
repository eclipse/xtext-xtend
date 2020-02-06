/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class Dispatching {

	public CharSequence doDispatch(Object o) {
		return null;
	}
	
	public StringBuilder _doDispatch(StringBuilder sb) {
		return null;
	}
	
	public String _doDispatch(Object o) {
		return null;
	}
	
	public StringBuffer _doDispatch(StringBuffer sb) {
		return null;
	}
	
}
