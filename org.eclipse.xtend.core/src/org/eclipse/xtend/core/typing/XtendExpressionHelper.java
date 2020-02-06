/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.typing;

import org.eclipse.xtend.core.xtend.RichString;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.util.XExpressionHelper;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class XtendExpressionHelper extends XExpressionHelper {
	
	@Override
	public boolean hasSideEffects(XExpression expr) {
		if (expr instanceof RichString) {
			return false;
		}
		return super.hasSideEffects(expr);
	}
}
