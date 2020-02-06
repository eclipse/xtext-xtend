/*******************************************************************************
 * Copyright (c) 2012, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package testdata;

import org.eclipse.xtext.xbase.lib.Functions;

/**
 * @author Sebastian Zarnekow
 * @since 2.3
 */
public class ClosureClient2 {

	private String value;

	@SuppressWarnings("unchecked")
	public ClosureClient2(Functions.Function0<String>... functions) {
		StringBuilder builder = new StringBuilder("varArgs:");
		for(Functions.Function0<String> function: functions) {
			builder.append(function.apply());
		}
		value = builder.toString();
	}
	
	public ClosureClient2(Functions.Function0<String> function1, Functions.Function0<String> function2) {
		StringBuilder builder = new StringBuilder("twoArgs:");
		builder.append(function1.apply());
		builder.append(function2.apply());
		value = builder.toString();
	}
	
	public String getValue() {
		return value;
	}
}
