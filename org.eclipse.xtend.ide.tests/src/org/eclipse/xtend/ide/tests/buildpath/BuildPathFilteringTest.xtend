/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.ide.tests.buildpath

import org.eclipse.xtend.ide.tests.AbstractXtendUITestCase
import org.junit.Test

import static org.eclipse.xtend.ide.tests.WorkbenchTestHelper.*

import static extension org.eclipse.jdt.core.JavaCore.*
import static extension org.eclipse.xtext.ui.testing.util.IResourcesSetupUtil.*
import static extension org.eclipse.xtext.ui.testing.util.JavaProjectSetupUtil.*
import org.junit.After

/**
 * @author Anton Kosyakov - Initial contribution and API
 */
class BuildPathFilteringTest extends AbstractXtendUITestCase {

	@After
	override tearDown() throws Exception {
		cleanWorkspace
	}

	@Test
	def testExcludeNoneAndIncludeAll() {
		createPluginProject('testProject') => [
			create.addSourceFolder('filtered-src', null, null)
		]
		'testProject/filtered-src/Foo.xtend'.createFile('class Foo {}')
		waitForBuild
		assertNotNull('testProject/xtend-gen/Foo.java'.file)
	}
	
	@Test
	def testExcludeAllXtendFiles() {
		createPluginProject('testProject') => [
			create.addSourceFolder('filtered-src', null, #["**.xtend"])
		]
		'testProject/filtered-src/Foo.xtend'.createFile('class Foo {}')
		waitForBuild
		assertNull('testProject/xtend-gen/Foo.java'.file)
	}
	
	/**
	 * <p>
	 * We ignore inclusion pattern for now.
	 * </p>
	 */
	@Test
	def testIncludeOnlyFooXtendFile() {
		createPluginProject('testProject') => [
			create.addSourceFolder('filtered-src', #["Foo.xtend"], null)
		]
		'testProject/filtered-src/Foo.xtend'.createFile('class Foo {}')
		'testProject/filtered-src/Bar.xtend'.createFile('class Bar {}')
		'testProject/filtered-src/foo/Foo.xtend'.createFile('package foo class Foo {}')
		waitForBuild
		assertNotNull('testProject/xtend-gen/Foo.java'.file)
		assertNotNull('testProject/xtend-gen/Bar.java'.file)
		assertNotNull('testProject/xtend-gen/foo/Foo.java'.file)
	}
	
	@Test
	def testExcludeAllFooXtendFiles() {
		createPluginProject('testProject') => [
			create.addSourceFolder('filtered-src', null, #["**/Foo.xtend"])
		]
		'testProject/filtered-src/Foo.xtend'.createFile('class Foo {}')
		'testProject/filtered-src/Bar.xtend'.createFile('class Bar {}')
		'testProject/filtered-src/foo/Foo.xtend'.createFile('package foo class Foo {}')
		waitForBuild
		assertNull('testProject/xtend-gen/Foo.java'.file)
		assertNotNull('testProject/xtend-gen/Bar.java'.file)
		assertNull('testProject/xtend-gen/foo/Foo.java'.file)
	}

}
