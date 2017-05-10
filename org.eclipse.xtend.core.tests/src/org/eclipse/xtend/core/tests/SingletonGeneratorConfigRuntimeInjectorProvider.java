/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests;

import org.eclipse.xtext.xbase.compiler.GeneratorConfig;
import org.eclipse.xtext.xbase.compiler.IGeneratorConfigProvider;
import org.eclipse.xtext.xbase.testing.SingletonGeneratorConfigModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Miro Spoenemann - Initial contribution and API
 */
public class SingletonGeneratorConfigRuntimeInjectorProvider extends RuntimeInjectorProvider {
	
	private final GeneratorConfig savedGeneratorConfig = new GeneratorConfig();
	
	@Override
	protected Injector internalCreateInjector() {
		return new RuntimeTestSetup() {
			
			@Override
			public Injector createInjector() {
				return Guice.createInjector(new XtendRuntimeTestModule(), new SingletonGeneratorConfigModule());
			}
			
		}.createInjectorAndDoEMFRegistration();
	}
	
	@Override
	public void setupRegistry() {
		super.setupRegistry();
		GeneratorConfig singletonGeneratorConfig = getInjector().getInstance(IGeneratorConfigProvider.class).get(null);
		savedGeneratorConfig.copy(singletonGeneratorConfig);
	}
	
	@Override
	public void restoreRegistry() {
		super.restoreRegistry();
		GeneratorConfig singletonGeneratorConfig = getInjector().getInstance(IGeneratorConfigProvider.class).get(null);
		singletonGeneratorConfig.copy(savedGeneratorConfig);
	}
	
}
