/*******************************************************************************
 * Copyright (c) 2011, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package testdata;

public class OuterClass {
	public static class InnerClass {
		public static final String SINGLETON = "SINGLETON";
		
		public String toUpperCase(String x) {
			return x.toUpperCase();
		}
	}
}
