/*******************************************************************************
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.compiler

import org.junit.Test

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
class CompilerBug424839Test extends AbstractXtendCompilerTest {
	
	@Test def void testBug_424839_01() {
		assertCompilesTo('''
			class C {
				val a = java.util.List
				val b = java.awt.List
			}
		''', '''
			import java.util.List;
			
			@SuppressWarnings("all")
			public class C {
			  private final Class<List> a = List.class;
			  
			  private final Class<java.awt.List> b = java.awt.List.class;
			}
		''')
	}
	
}
