/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.compiler;

import org.eclipse.xtext.generator.trace.AbstractStatefulTraceRegion;
import org.eclipse.xtext.generator.trace.LocationData;
import org.eclipse.xtext.generator.trace.SourceRelativeURI;
import org.eclipse.xtext.util.ITextRegionWithLineInformation;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class RootTraceRegionForTesting extends AbstractStatefulTraceRegion {

	public RootTraceRegionForTesting(ITextRegionWithLineInformation myLocation, ITextRegionWithLineInformation association) {
		super(myLocation, true, new LocationData(association, new SourceRelativeURI("uri")), null);
	}
	
}
