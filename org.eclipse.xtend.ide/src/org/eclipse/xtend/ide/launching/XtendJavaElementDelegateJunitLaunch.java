/*******************************************************************************
 * Copyright (c) 2019, 2020 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.ide.launching;

import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.xtend.core.xtend.XtendFile;
import org.eclipse.xtend.core.xtend.XtendTypeDeclaration;
import org.eclipse.xtext.builder.EclipseOutputConfigurationProvider;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.util.jdt.IJavaElementFinder;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.FileExtensionProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.ui.launching.JavaElementDelegateJunitLaunch;

import com.google.inject.Inject;

/**
 * @author miklossy - Initial contribution and API
 */
public class XtendJavaElementDelegateJunitLaunch extends JavaElementDelegateJunitLaunch {

	@Inject
	private IJavaElementFinder elementFinder;

	@Inject
	private EclipseOutputConfigurationProvider outputConfigurationProvider;

	@Inject
	private FileExtensionProvider fileExtensionProvider;

	@Override
	protected IJavaElement findJavaElement(XtextResource resource, int offset) {
		IJavaElement javaElement = super.findJavaElement(resource, offset);
		if (javaElement == null) {
			javaElement = getXtendClass(resource);
		}

		return javaElement;
	}

	@Override
	protected IJavaElement getJavaElementForPackage(IPackageFragment packageFragment) {
		/*
		 * if the packageFragment contains only *.xtend files, return
		 * the corresponding package fragment in the xtend-gen folder
		 */
		if (containsOnlyXtendFiles(packageFragment)) {
			return getXtendGenPackageFragment(packageFragment);
		}
		return super.getJavaElementForPackage(packageFragment);
	}

	private boolean containsOnlyXtendFiles(IPackageFragment packageFragment) {
		boolean containsJavaResources = false;
		try {
			containsJavaResources = packageFragment.containsJavaResources();
		} catch (JavaModelException e) {
			return false;
		}
		if (containsJavaResources) {
			return false;
		}

		Object[] nonJavaResources = null;
		try {
			nonJavaResources = packageFragment.getNonJavaResources();
		} catch (JavaModelException e) {
			return false;
		}

		String xtendFileExtension = fileExtensionProvider.getPrimaryFileExtension();
		for (Object nonJavaResource : nonJavaResources) {
			if (nonJavaResource instanceof IFile) {
				IFile nonJavaFile = (IFile) nonJavaResource;
				if (xtendFileExtension.equals(nonJavaFile.getFileExtension())) {
					return true;
				}
			}
		}

		return false;
	}

	private IJavaElement getXtendGenPackageFragment(IPackageFragment packageFragment) {
		String packageName = packageFragment.getElementName();
		IJavaElement project = packageFragment.getAncestor(IJavaElement.JAVA_PROJECT);
		if (project instanceof IJavaProject) {
			IJavaProject javaProject = (IJavaProject) project;
			String outputFolder = getXtendOutputFolder(packageName);
			if (outputFolder != null) {
				IPath xtendGenpackagePath = new Path("/" + javaProject.getElementName() + "/" +outputFolder+ "/" + packageName.replace(".", "/"));
				IPackageFragment xtendGenPackageFragment = null;
				try {
					xtendGenPackageFragment = javaProject.findPackageFragment(xtendGenpackagePath);
				} catch (JavaModelException e) {
					return null;
				}
				return xtendGenPackageFragment;
			}
		}
		return null;
	}
	
	private String getXtendOutputFolder(String packageName) {
		Set<OutputConfiguration> outputConfigurations = outputConfigurationProvider.getOutputConfigurations();
		if (outputConfigurations.size() == 1) {
			OutputConfiguration outputConfiguration = outputConfigurations.iterator().next();
			return outputConfiguration.getOutputDirectory(packageName);
		}
		return null;
	}

	/**
	 * @param resource the Xtext resource to parse
	 * @return if the resource contains exactly one class, returns the IJavaElement associated with that class,
	 *			otherwise returns null
	 */
	private IJavaElement getXtendClass(XtextResource resource) {
		IParseResult parseResult = resource.getParseResult();
		if (parseResult == null) {
			return null;
		}
		EObject root = parseResult.getRootASTElement();
		if (root instanceof XtendFile) {
			XtendFile xtendFile = (XtendFile) root;
			EList<XtendTypeDeclaration> xtendTypes = xtendFile.getXtendTypes();
			if (xtendTypes.size() == 1) {
				XtendTypeDeclaration element = xtendTypes.get(0);
				JvmIdentifiableElement jvmElement = findAssociatedJvmElement(element);
				if (jvmElement == null) {
					return null;
				}
				IJavaElement javaElement = elementFinder.findElementFor(jvmElement);
				return javaElement;
			}
		}
		return null;
	}
}