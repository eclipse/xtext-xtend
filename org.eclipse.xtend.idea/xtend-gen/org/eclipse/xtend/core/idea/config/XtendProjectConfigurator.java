/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.idea.config;

import com.google.inject.Inject;
import org.eclipse.xtend.core.idea.config.GradleBuildFileUtility;
import org.eclipse.xtend.core.idea.facet.XtendFacetConfiguration;
import org.eclipse.xtext.idea.config.XtextProjectConfigurator;
import org.eclipse.xtext.xbase.idea.facet.XbaseGeneratorConfigurationState;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;

/**
 * @author dhuebner - Initial contribution and API
 */
@SuppressWarnings("all")
public class XtendProjectConfigurator extends XtextProjectConfigurator {
  @Inject
  @Extension
  private GradleBuildFileUtility _gradleBuildFileUtility;
  
  public void setupOutputConfiguration(final /* ModifiableRootModel */Object rootModel, final XtendFacetConfiguration conf) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method presetGradleOutputDirectories(XbaseGeneratorConfigurationState, ModifiableRootModel) from the type XtendProjectConfigurator refers to the missing type ModifiableRootModel"
      + "\nThe method presetPlainJavaOutputDirectories(XbaseGeneratorConfigurationState, ModifiableRootModel) from the type XtendProjectConfigurator refers to the missing type ModifiableRootModel"
      + "\nmodule cannot be resolved"
      + "\nlocateBuildFile cannot be resolved"
      + "\nisGradleedModule cannot be resolved"
      + "\n&& cannot be resolved"
      + "\n!== cannot be resolved");
  }
  
  public XtendFacetConfiguration createOrGetXtendFacetConf(final /* Module */Object module) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method createOrGetFacetConf(Module, Object) is undefined"
      + "\nThe method or field ID is undefined for the type XtendLanguage");
  }
  
  public void presetGradleOutputDirectories(final XbaseGeneratorConfigurationState state, final /* ModifiableRootModel */Object rootModel) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field testSource is undefined"
      + "\nThe method or field url is undefined"
      + "\nThe method or field testSource is undefined"
      + "\nThe method or field url is undefined"
      + "\nWriteCommandAction.Simple cannot be resolved."
      + "\nThe method run() of type new Object(){} must override a superclass method."
      + "\nfindSourceFolder cannot be resolved"
      + "\n! cannot be resolved"
      + "\n&& cannot be resolved"
      + "\nendsWith cannot be resolved"
      + "\nfindSourceFolder cannot be resolved"
      + "\n&& cannot be resolved"
      + "\nendsWith cannot be resolved"
      + "\n!== cannot be resolved"
      + "\n|| cannot be resolved"
      + "\n!== cannot be resolved"
      + "\n?: cannot be resolved"
      + "\npath cannot be resolved"
      + "\n?: cannot be resolved"
      + "\npath cannot be resolved"
      + "\ncontentRoots cannot be resolved"
      + "\nhead cannot be resolved"
      + "\npath cannot be resolved"
      + "\nmodule cannot be resolved"
      + "\nisAndroidGradleModule cannot be resolved"
      + "\nmodule cannot be resolved"
      + "\nlocateBuildFile cannot be resolved"
      + "\n!== cannot be resolved"
      + "\nproject cannot be resolved"
      + "\nexecute cannot be resolved"
      + "\nmodule cannot be resolved"
      + "\nsetupGradleBuild cannot be resolved");
  }
  
  public void presetPlainJavaOutputDirectories(final XbaseGeneratorConfigurationState state, final /* ModifiableRootModel */Object model) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field testSource is undefined"
      + "\nThe method or field file is undefined"
      + "\nThe method or field testSource is undefined"
      + "\nThe method or field file is undefined"
      + "\nThe method or field VfsUtil is undefined"
      + "\nfindSourceFolder cannot be resolved"
      + "\n! cannot be resolved"
      + "\n&& cannot be resolved"
      + "\nexists cannot be resolved"
      + "\nfindSourceFolder cannot be resolved"
      + "\n&& cannot be resolved"
      + "\nexists cannot be resolved"
      + "\n=== cannot be resolved"
      + "\n&& cannot be resolved"
      + "\n=== cannot be resolved"
      + "\nfindBestContentRoot cannot be resolved"
      + "\npath cannot be resolved"
      + "\n+ cannot be resolved"
      + "\nVFS_SEPARATOR_CHAR cannot be resolved"
      + "\n+ cannot be resolved"
      + "\n?: cannot be resolved"
      + "\nsiblingPath cannot be resolved"
      + "\n?: cannot be resolved"
      + "\nsiblingPath cannot be resolved");
  }
  
  public void createOutputFolders(final /* ModifiableRootModel */Object rootModel, final XbaseGeneratorConfigurationState state) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field VfsUtil is undefined"
      + "\nThe method or field JavaSourceRootType is undefined"
      + "\nThe method or field VfsUtil is undefined"
      + "\nThe method or field JavaSourceRootType is undefined"
      + "\ncreateDirectoryIfMissing cannot be resolved"
      + "\n!== cannot be resolved"
      + "\naddAsSourceFolder cannot be resolved"
      + "\nSOURCE cannot be resolved"
      + "\ncreateDirectoryIfMissing cannot be resolved"
      + "\n!== cannot be resolved"
      + "\naddAsSourceFolder cannot be resolved"
      + "\nTEST_SOURCE cannot be resolved");
  }
  
  /**
   * For single contentRoot use it
   * 	For multiple use first that not contains the module config file
   * 	For any use project baseDir
   */
  private /* VirtualFile */Object findBestContentRoot(final /* ModifiableRootModel */Object model) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field VfsUtil is undefined"
      + "\nmodule cannot be resolved"
      + "\ncontentRoots cannot be resolved"
      + "\nsize cannot be resolved"
      + "\n== cannot be resolved"
      + "\nget cannot be resolved"
      + "\nsize cannot be resolved"
      + "\n> cannot be resolved"
      + "\nmoduleFile cannot be resolved"
      + "\n!== cannot be resolved"
      + "\nfindFirst cannot be resolved"
      + "\nisAncestor cannot be resolved"
      + "\nmoduleFile cannot be resolved"
      + "\n! cannot be resolved"
      + "\nhead cannot be resolved"
      + "\nproject cannot be resolved"
      + "\nbaseDir cannot be resolved");
  }
  
  private /* VirtualFile */Object findSourceFolder(final /* ModifiableRootModel */Object rootModel, final /* Function1<? super SourceFolder, ? extends Boolean> */Object fun) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field sourceFolders is undefined"
      + "\ncontentEntries cannot be resolved"
      + "\nfindFirst cannot be resolved"
      + "\nfindFirst cannot be resolved"
      + "\n!== cannot be resolved"
      + "\n!== cannot be resolved"
      + "\nsourceFolders cannot be resolved"
      + "\nfindFirst cannot be resolved"
      + "\nfile cannot be resolved");
  }
  
  public void addAsSourceFolder(final /* ModifiableRootModel */Object rootModel, final /* VirtualFile */Object xtendGenMain, final /* JavaSourceRootType */Object type) {
    throw new Error("Unresolved compilation problems:"
      + "\nJavaSourceRootProperties cannot be resolved to a type."
      + "\nThe method or field VfsUtil is undefined"
      + "\nThe method or field file is undefined for the type Object"
      + "\nThe method or field file is undefined for the type Object"
      + "\nThe method or field VfsUtil is undefined"
      + "\nThe method or field file is undefined for the type Object"
      + "\nThe method or field JpsJavaExtensionService is undefined"
      + "\nThe method or field VfsUtil is undefined"
      + "\nThe method or field url is undefined for the type Object"
      + "\ncontentEntries cannot be resolved"
      + "\nfindFirst cannot be resolved"
      + "\nisAncestor cannot be resolved"
      + "\n!== cannot be resolved"
      + "\nexcludeFolders cannot be resolved"
      + "\nfindFirst cannot be resolved"
      + "\n!== cannot be resolved"
      + "\n&& cannot be resolved"
      + "\nisAncestor cannot be resolved"
      + "\n!== cannot be resolved"
      + "\nremoveExcludeFolder cannot be resolved"
      + "\ngetInstance cannot be resolved"
      + "\ncreateSourceRootProperties cannot be resolved"
      + "\nsourceFolders cannot be resolved"
      + "\nfindFirst cannot be resolved"
      + "\nisEqualOrAncestor cannot be resolved"
      + "\nurl cannot be resolved"
      + "\n!== cannot be resolved"
      + "\njpsElement cannot be resolved"
      + "\nproperties cannot be resolved"
      + "\napplyChanges cannot be resolved"
      + "\naddSourceFolder cannot be resolved");
  }
  
  private String siblingPath(final /* VirtualFile */Object sibling, final String path) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field VfsUtil is undefined"
      + "\nparent cannot be resolved"
      + "\npath cannot be resolved"
      + "\n+ cannot be resolved"
      + "\nVFS_SEPARATOR_CHAR cannot be resolved"
      + "\n+ cannot be resolved");
  }
}
