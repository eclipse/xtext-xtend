/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package testdata;

import java.util.List;
import java.util.Map;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 * @since 2.4
 */
@SuppressWarnings("rawtypes")
public class MethodOverrides5 extends MethodOverrides3 {
	
	@Override
	public String m1(CharSequence t) {
		return "m1(t)";
	}
	
	@Override
	public String m2(Iterable t) {
		return "m2(t)";
	}
	
	@Override
	public String m3(CharSequence t) {
		return "m3(t)";
	}

	@Override
	public List<String> m4(Iterable t) {
		return null;
	}
	
	@Override
	public String m5(Iterable v) {
		return "m5(t)";
	}
	
	@Override
	void m6() {
	}
	
	@Override
	void m7(Comparable[] p1, List p2, Comparable p3, Map p4) {
	}
	
	@Override
	void m7() {
	}
	
	@Override
	void m8() {
	}
	
	@Override
	void m9() {
	}
}
