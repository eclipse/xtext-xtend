/*******************************************************************************
 * Copyright (c) 2016 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.xtext.util.StringInputStream;


/**
 * A {@link org.eclipse.emf.ecore.resource.URIConverter} that can create input streams
 * for synthetic URIs based on previously registered mappings.
 * 
 * @author Sebastian Zarnekow - Initial contribution and API
 * @since 2.8
 */
public class InMemoryURIConverter extends ExtensibleURIConverterImpl {
	private final Map<URI, InputStream> models = new HashMap<URI, InputStream>();

	public void addModel(String uri, String content) {
		models.put(URI.createURI(uri), new StringInputStream(content));
	}

	@Override
	public boolean exists(URI uri, Map<?, ?> options) {
	 	boolean result = models.containsKey(uri);
	 	if (!result) {
	 		for(URI knownUri: models.keySet()) {
	 			if (uri.toString().endsWith(knownUri.toString()))
	 				return true;
	 		}
	 	}
	 	return result;
	}

	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		return models.get(uri);
	}
}