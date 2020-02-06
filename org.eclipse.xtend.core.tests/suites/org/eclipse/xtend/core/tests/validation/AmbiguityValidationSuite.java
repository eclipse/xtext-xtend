/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.validation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@RunWith(Suite.class)
@SuiteClasses({
	AmbiguousPlainFeatureCallTest.class,
	AmbiguousExtensionFeatureCallTest.class,
	AmbiguousOperatorsTest.class,
	AmbiguousGenericFeatureCallTest.class,
	AmbiguousRawFeatureCallTest.class,
	AmbiguityBug421831Test.class,
	AmbiguityBug426779Test.class,
	AmbiguityBug427352Test.class,
	AmbiguityBug429623Test.class,
	AmbiguityBug438461Test.class,
	AmbiguityBug463407Test.class,
	AmbiguityGH389Test.class
})
public class AmbiguityValidationSuite {
}
