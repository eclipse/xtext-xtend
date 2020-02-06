/*******************************************************************************
 * Copyright (c) 2019 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.ide.buildpath;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.xtext.util.MergeableManifest2;

/**
 * @author Jan Koehnlein - Initial contribution and API
 * @author vivien.jovet
 */
public abstract class AbstractLibClasspathAdder {
	
	private static final Logger LOG = Logger.getLogger(XtendLibClasspathAdder.class);

	private static final String PLUGIN_NATURE = "org.eclipse.pde.PluginNature";
	
	public void addLibsToClasspath(IJavaProject javaProject, IProgressMonitor monitor) {
		try {
			SubMonitor progress = SubMonitor.convert(monitor, 2);
			IProject project = javaProject.getProject();
			if (!project.hasNature(PLUGIN_NATURE) || !addToPluginManifest(project, progress.newChild(1)))
				addToClasspath(javaProject, progress.newChild(1));
		} catch (Exception exc) {
			LOG.error("Error adding Xtend libs to classpath", exc);
		}
	}

	protected abstract IClasspathEntry createContainerClasspathEntry();

	protected abstract String[] getBundleIds();

	protected boolean addToClasspath(IJavaProject javaProject, IProgressMonitor monitor) throws JavaModelException {
		IClasspathEntry containerEntry = createContainerClasspathEntry();
		IClasspathEntry[] rawClasspath = javaProject.getRawClasspath();
		IClasspathEntry[] newRawClasspath = new IClasspathEntry[rawClasspath.length + 1];
		for (int i = 0; i < rawClasspath.length; ++i) {
			IClasspathEntry entry = rawClasspath[i];
			if (entry.getEntryKind() == IClasspathEntry.CPE_CONTAINER && entry.getPath().equals(containerEntry.getPath())) {
				return false;
			}
			newRawClasspath[i + 1] = entry;
		}
		newRawClasspath[0] = containerEntry;
		javaProject.setRawClasspath(newRawClasspath, monitor);
		return true;
	}

	protected boolean addToPluginManifest(IProject project, IProgressMonitor monitor) throws IOException, CoreException {
		IResource manifestFile = project.findMember("META-INF/MANIFEST.MF");
		if (manifestFile != null && manifestFile.isAccessible() && !manifestFile.getResourceAttributes().isReadOnly()
				&& manifestFile instanceof IFile) {
			OutputStream output = null;
			InputStream input = null;
			try {
				MergeableManifest2 manifest = createMergableManifest(manifestFile);
				manifest.addRequiredBundles(getBundleIds());
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				output = new BufferedOutputStream(out);
				manifest.write(output);
				ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
				input = new BufferedInputStream(in);
				((IFile) manifestFile).setContents(input, true, true, monitor);
				return true;
			} finally {
				if (output != null)
					output.close();
				if (input != null)
					input.close();
			}
		}
		return false;
	}

	private MergeableManifest2 createMergableManifest(IResource manifestFile) throws IOException, CoreException {
		InputStream originalManifest = ((IFile) manifestFile).getContents();
		try {
			return new MergeableManifest2(originalManifest);
		} finally {
			originalManifest.close();
		}
	}

}
