/*******************************************************************************
 * Copyright (c) 2018 Universite de Technologie de Belfort Montbeliard, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.compiler

import com.google.inject.Inject
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test

class InlineInXtendTest extends AbstractXtendCompilerTest {
	
	@Inject
	extension ValidationTestHelper
	
	@Test
	def testDataClasses_01() { 
		assertCompilesTo('''
			import org.eclipse.xtext.xbase.lib.Inline;
			class Foo {
				@Inline(value = "3", constantExpression = true)
				def int fct() {
					return 3
				}
				def int fct2() {
					return fct() + 1
				}
			}
		''', '''
			import org.eclipse.xtext.xbase.lib.Inline;
			
			@SuppressWarnings("all")
			public class Foo {
				@Inline(value = "3", constantExpression = true)
				public int fct() {
					return 3;
				}

				public int fct2() {
					return 3 + 1
				}
			}
		''')
	}

}
