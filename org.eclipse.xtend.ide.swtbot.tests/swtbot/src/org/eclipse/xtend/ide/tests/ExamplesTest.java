/*******************************************************************************
 * Copyright (c) 2018 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.ide.tests;

import static org.eclipse.xtext.ui.swtbot.testing.api.EclipseAPI.*;
import static org.junit.Assert.*;

import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.xtext.ui.swtbot.testing.AbstractSwtBotTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests to create the Xtend examples using SWTBot.
 * 
 * @author Arne Deutsch - Initial contribution and API
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class ExamplesTest extends AbstractSwtBotTest {

	@Before
	public void setUp() {
		mainMenu().openJavaPerspective();
		mainMenu().openJunitView();
		mainMenu().openConsoleView();
		closeAllShells();
		deleteAllProjects();
	}

	@After
	public void tearDown() {
		closeAllShells();
		deleteAllProjects();
	}

	@Test
	public void xtendActiveAnnotationExamples() throws Exception {
		// create example projects
		mainMenu().openNewProjectWizard().selectXtendExample("Xtend Active Annotation Examples").finish();
		waitForBuild();

		// check example projects are created
		assertTrue(packageExplorer().projectExist("xtend-annotation-examples"));
		assertTrue(packageExplorer().projectExist("xtend-annotation-examples-client"));

		// check example projects are error free
		assertEquals(0, problemsView().errorCount());

		// run all unit tests and check there are no test failures
		packageExplorer().runJUnitTests("xtend-annotation-examples", "xtend-gen");
		junitView().waitForTestrunToFinish();
		assertTrue(junitView().isTestrunErrorFree());

	}

	@Test
	public void xtendIntroductoryExamples() throws Exception {
		standartXtendExample("Xtend Introductory Examples", "xtend-examples");
	}

	@Test
	public void xtendSolutionsForEuler() throws Exception {
		standartXtendExample("Xtend Solutions for Euler", "xtend-euler");
	}

	private void standartXtendExample(String exampleLabel, String projectName) throws Exception {
		// create example projects
		mainMenu().openNewProjectWizard().selectXtendExample(exampleLabel).finish();
		waitForBuild();

		// check example projects are created
		assertTrue(packageExplorer().projectExist(projectName));

		// check example projects are error free
		assertEquals(0, problemsView().errorCount());
	}

}
