/*******************************************************************************
 * Copyright (c) 2013, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.compiler;

import org.eclipse.xtext.testing.smoketest.ProcessedBy;
import org.eclipse.xtext.testing.smoketest.XtextSmokeTestRunner;
import org.junit.runner.RunWith;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@RunWith(XtextSmokeTestRunner.class)
@ProcessedBy(value = NoJRESmokeTester.class, processCompleteInput = true)
public class NoJRESuite extends CompilerSuite {
}
