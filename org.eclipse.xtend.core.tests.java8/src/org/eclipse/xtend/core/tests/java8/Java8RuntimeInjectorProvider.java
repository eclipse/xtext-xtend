/*******************************************************************************
 * Copyright (c) 2015, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.java8;

import org.eclipse.xtend.core.tests.RuntimeInjectorProvider;
import org.eclipse.xtend.core.tests.RuntimeTestSetup;
import org.eclipse.xtext.util.JavaVersion;
import org.eclipse.xtext.xbase.testing.JavaVersionModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Miro Spoenemann - Initial contribution and API
 */
public class Java8RuntimeInjectorProvider extends RuntimeInjectorProvider {
	
	@Override
	protected Injector internalCreateInjector() {
		return new RuntimeTestSetup() {
			
			@Override
			public Injector createInjector() {
				return Guice.createInjector(new XtendRuntimeTestModule() {
					@Override
					public ClassLoader bindClassLoaderToInstance() {
						return Java8RuntimeInjectorProvider.class.getClassLoader();
					}
				}, new JavaVersionModule(JavaVersion.JAVA8));
			}
			
		}.createInjectorAndDoEMFRegistration();
	}
	
}
