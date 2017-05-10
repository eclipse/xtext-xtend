/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.ide.outline

import com.google.inject.Inject
import com.google.inject.Provider
import org.eclipse.xtend.core.jvmmodel.IXtendJvmAssociations
import org.eclipse.xtend.core.xtend.XtendFunction
import org.eclipse.xtext.common.types.JvmFeature
import org.eclipse.xtext.common.types.JvmMember

import static org.eclipse.xtend.core.jvmmodel.XtendJvmModelInferrer.*

/**
 * @author kosyakov - Initial contribution and API
 */
class EclipseXtendOutlineSourceContext extends EclipseXtendOutlineContext {

	@Inject
	extension IXtendJvmAssociations
	
	@Inject
	Provider<EclipseXtendOutlineSourceContext> xtendOutlineContextProvider

	override markAsProcessed(JvmMember member) {
		super.markAsProcessed(member)
		markCreateExtensionJvmFeaturesAsProcessed(member)
		this
	}

	protected def markCreateExtensionJvmFeaturesAsProcessed(JvmMember member) {
		val function = member.primarySourceElement
		if (function instanceof XtendFunction) {
			if (function.createExtensionInfo !== null) {
				for (jvmFeature : function.jvmElements.filter(JvmFeature).filter[it != member].filter [
					simpleName.startsWith(CREATE_CHACHE_VARIABLE_PREFIX) ||
						simpleName.startsWith(CREATE_INITIALIZER_PREFIX)
				]) {
					super.markAsProcessed(jvmFeature)
				}
			}
		}
	}

	override protected EclipseXtendOutlineContext cloneContext() {
		cloneContext(xtendOutlineContextProvider.get)
	}

}