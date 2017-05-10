/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.ide.macro;

import com.google.common.collect.Iterables;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.xtend.core.macro.ProcessorInstanceForJvmTypeProvider;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.resource.ResourceSetContext;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.internal.Log;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@Log
@SuppressWarnings("all")
public class JdtBasedProcessorProvider extends ProcessorInstanceForJvmTypeProvider {
  @Override
  public Object getProcessorInstance(final JvmType type) {
    try {
      final ClassLoader classLoader = this.getClassLoader(type);
      final Class<?> result = classLoader.loadClass(type.getIdentifier());
      return result.newInstance();
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        String _identifier = type.getIdentifier();
        String _plus = ("Problem during instantiation of " + _identifier);
        String _plus_1 = (_plus + " : ");
        String _message = e.getMessage();
        String _plus_2 = (_plus_1 + _message);
        throw new IllegalStateException(_plus_2, e);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  @Override
  public ClassLoader getClassLoader(final EObject ctx) {
    ResourceSet _resourceSet = ctx.eResource().getResourceSet();
    final XtextResourceSet rs = ((XtextResourceSet) _resourceSet);
    final boolean isBuilder = ResourceSetContext.get(rs).isBuilder();
    final boolean isEditor = ResourceSetContext.get(rs).isEditor();
    if (isBuilder) {
      final ProcessorInstanceForJvmTypeProvider.ProcessorClassloaderAdapter adapter = IterableExtensions.<ProcessorInstanceForJvmTypeProvider.ProcessorClassloaderAdapter>head(Iterables.<ProcessorInstanceForJvmTypeProvider.ProcessorClassloaderAdapter>filter(rs.eAdapters(), ProcessorInstanceForJvmTypeProvider.ProcessorClassloaderAdapter.class));
      if ((adapter != null)) {
        return adapter.getClassLoader();
      }
    }
    if (isEditor) {
      final ProcessorInstanceForJvmTypeProvider.ProcessorClassloaderAdapter adapter_1 = IterableExtensions.<ProcessorInstanceForJvmTypeProvider.ProcessorClassloaderAdapter>head(Iterables.<ProcessorInstanceForJvmTypeProvider.ProcessorClassloaderAdapter>filter(this.getEditorResource(ctx).eAdapters(), ProcessorInstanceForJvmTypeProvider.ProcessorClassloaderAdapter.class));
      if ((adapter_1 != null)) {
        ClassLoader _classLoader = adapter_1.getClassLoader();
        boolean _tripleEquals = (_classLoader == null);
        if (_tripleEquals) {
          this.getEditorResource(ctx).eAdapters().remove(adapter_1);
        } else {
          return adapter_1.getClassLoader();
        }
      }
    }
    Object _classpathURIContext = rs.getClasspathURIContext();
    final IJavaProject project = ((IJavaProject) _classpathURIContext);
    final URLClassLoader classloader = this.createClassLoaderForJavaProject(project);
    if (isBuilder) {
      EList<Adapter> _eAdapters = rs.eAdapters();
      ProcessorInstanceForJvmTypeProvider.ProcessorClassloaderAdapter _processorClassloaderAdapter = new ProcessorInstanceForJvmTypeProvider.ProcessorClassloaderAdapter(classloader);
      _eAdapters.add(_processorClassloaderAdapter);
    }
    if (isEditor) {
      EList<Adapter> _eAdapters_1 = this.getEditorResource(ctx).eAdapters();
      ProcessorInstanceForJvmTypeProvider.ProcessorClassloaderAdapter _processorClassloaderAdapter_1 = new ProcessorInstanceForJvmTypeProvider.ProcessorClassloaderAdapter(classloader);
      _eAdapters_1.add(_processorClassloaderAdapter_1);
    }
    return classloader;
  }
  
  private Resource getEditorResource(final EObject ctx) {
    return IterableExtensions.<Resource>head(ctx.eResource().getResourceSet().getResources());
  }
  
  /**
   * Construct a Classloader with the classpathentries from the provided and all upstream-projects,
   * except the output folders of the local project.
   */
  protected URLClassLoader createClassLoaderForJavaProject(final IJavaProject projectToUse) {
    final LinkedHashSet<URL> urls = CollectionLiterals.<URL>newLinkedHashSet();
    try {
      this.collectClasspathURLs(projectToUse, urls, this.isOutputFolderIncluded(), CollectionLiterals.<IJavaProject>newHashSet());
    } catch (final Throwable _t) {
      if (_t instanceof JavaModelException) {
        final JavaModelException e = (JavaModelException)_t;
        boolean _isDoesNotExist = e.isDoesNotExist();
        boolean _not = (!_isDoesNotExist);
        if (_not) {
          JdtBasedProcessorProvider.LOG.error(e.getMessage(), e);
        }
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    ClassLoader _parentClassLoader = this.getParentClassLoader();
    return new URLClassLoader(((URL[])Conversions.unwrapArray(urls, URL.class)), _parentClassLoader);
  }
  
  protected boolean isOutputFolderIncluded() {
    return false;
  }
  
  protected void collectClasspathURLs(final IJavaProject projectToUse, final LinkedHashSet<URL> result, final boolean includeOutputFolder, final Set<IJavaProject> visited) throws JavaModelException {
    try {
      if (((!projectToUse.getProject().isAccessible()) || (!visited.add(projectToUse)))) {
        return;
      }
      if (includeOutputFolder) {
        IPath path = projectToUse.getOutputLocation().addTrailingSeparator();
        String _string = URI.createPlatformResourceURI(path.toString(), true).toString();
        URL url = new URL(_string);
        result.add(url);
      }
      final IClasspathEntry[] resolvedClasspath = projectToUse.getResolvedClasspath(true);
      for (final IClasspathEntry entry : resolvedClasspath) {
        {
          URL url_1 = null;
          int _entryKind = entry.getEntryKind();
          switch (_entryKind) {
            case IClasspathEntry.CPE_SOURCE:
              if (includeOutputFolder) {
                final IPath path_1 = entry.getOutputLocation();
                if ((path_1 != null)) {
                  String _string_1 = URI.createPlatformResourceURI(path_1.addTrailingSeparator().toString(), true).toString();
                  URL _uRL = new URL(_string_1);
                  url_1 = _uRL;
                }
              }
              break;
            case IClasspathEntry.CPE_PROJECT:
              IPath path_2 = entry.getPath();
              final IResource project = this.getWorkspaceRoot(projectToUse).findMember(path_2);
              final IJavaProject referencedProject = JavaCore.create(project.getProject());
              this.collectClasspathURLs(referencedProject, result, true, visited);
              break;
            case IClasspathEntry.CPE_LIBRARY:
              IPath path_3 = entry.getPath();
              final IResource library = this.getWorkspaceRoot(projectToUse).findMember(path_3);
              URL _xifexpression = null;
              if ((library != null)) {
                _xifexpression = library.getRawLocationURI().toURL();
              } else {
                _xifexpression = path_3.toFile().toURI().toURL();
              }
              url_1 = _xifexpression;
              break;
            default:
              {
                IPath path_4 = entry.getPath();
                url_1 = path_4.toFile().toURI().toURL();
              }
              break;
          }
          if ((url_1 != null)) {
            result.add(url_1);
          }
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected ClassLoader getParentClassLoader() {
    final ClassLoader bundleClassLoader = TransformationContext.class.getClassLoader();
    return bundleClassLoader;
  }
  
  private IWorkspaceRoot getWorkspaceRoot(final IJavaProject javaProject) {
    return javaProject.getProject().getWorkspace().getRoot();
  }
  
  private final static Logger LOG = Logger.getLogger(JdtBasedProcessorProvider.class);
}
