/*******************************************************************************
 * Copyright (c) 2010-2017 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.idea.lang.pom;

import org.eclipse.xtend.core.idea.lang.XtendLanguage;
import org.eclipse.xtext.idea.pom.AbstractXtextPomDeclarationSearcher;

public class XtendPomDeclarationSearcher extends AbstractXtextPomDeclarationSearcher {

	public XtendPomDeclarationSearcher() {
		super(XtendLanguage.INSTANCE);
	}
}
