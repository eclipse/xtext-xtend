/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.compiler

import org.junit.Test

/**
 * @author miklossy - Initial contribution and API
 */
class CompilerBug399591Test extends AbstractXtendCompilerTest {

	@Test def visibility_override_protected() {
		'''
			class C extends junit.framework.TestCase {
				override setUp () {}
			}
		'''.assertCompilesTo('''
			import junit.framework.TestCase;
			
			@SuppressWarnings("all")
			public class C extends TestCase {
			  protected void setUp() {
			  }
			}
		''')
	}

	@Test def visibility_override_protected_protected() {
		'''
			class C extends junit.framework.TestCase {
				protected override setUp () {}
			}
		'''.assertCompilesTo('''
			import junit.framework.TestCase;
			
			@SuppressWarnings("all")
			public class C extends TestCase {
			  protected void setUp() {
			  }
			}
		''')
	}

	@Test def visibility_override_protected_public() {
		'''
			class C extends junit.framework.TestCase {
				public override setUp () {}
			}
		'''.assertCompilesTo('''
			import junit.framework.TestCase;
			
			@SuppressWarnings("all")
			public class C extends TestCase {
			  public void setUp() {
			  }
			}
		''')
	}

	@Test def visibility_override_public() {
		'''
			class C extends junit.framework.TestCase {
				override getName () {}
			}
		'''.assertCompilesTo('''
			import junit.framework.TestCase;
			
			@SuppressWarnings("all")
			public class C extends TestCase {
			  public String getName() {
			    return null;
			  }
			}
		''')
	}

	@Test def visibility_override_public_public() {
		'''
			class C extends junit.framework.TestCase {
				public override getName () {}
			}
		'''.assertCompilesTo('''
			import junit.framework.TestCase;
			
			@SuppressWarnings("all")
			public class C extends TestCase {
			  public String getName() {
			    return null;
			  }
			}
		''')
	}
}