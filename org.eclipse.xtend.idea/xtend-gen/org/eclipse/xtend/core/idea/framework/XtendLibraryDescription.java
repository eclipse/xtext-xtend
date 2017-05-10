/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.idea.framework;

import com.google.common.collect.Lists;
import com.intellij.openapi.roots.OrderRootType;
import com.intellij.openapi.roots.libraries.LibraryKind;
import com.intellij.openapi.roots.libraries.LibraryPresentationProvider;
import com.intellij.openapi.roots.libraries.NewLibraryConfiguration;
import com.intellij.openapi.roots.ui.configuration.libraries.CustomLibraryDescription;
import com.intellij.openapi.roots.ui.configuration.libraryEditor.LibraryEditor;
import com.intellij.openapi.roots.ui.configuration.projectRoot.LibrariesContainer;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.PathUtil;
import com.intellij.util.containers.ContainerUtil;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javax.swing.JComponent;
import org.eclipse.xtend.core.idea.framework.XtendLibraryPresentationProvider;
import org.eclipse.xtend.lib.annotations.Data;
import org.eclipse.xtend.lib.macro.Active;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class XtendLibraryDescription extends CustomLibraryDescription {
  public final static Set<LibraryKind> LIBRARY_KINDS = IterableExtensions.<LibraryKind>toSet(ListExtensions.<XtendLibraryPresentationProvider, LibraryKind>map(XtendLibraryDescription.getProviders(), ((Function1<XtendLibraryPresentationProvider, LibraryKind>) (XtendLibraryPresentationProvider it) -> {
    return it.getKind();
  })));
  
  public final static String XTEND_LIBRARY_NAME = "Xtend Library";
  
  @Override
  public NewLibraryConfiguration createNewLibrary(final JComponent parentComponent, final VirtualFile contextDirectory) {
    return this.createLibraryDescription();
  }
  
  public NewLibraryConfiguration createLibraryDescription() {
    abstract class __XtendLibraryDescription_1 extends NewLibraryConfiguration {
      __XtendLibraryDescription_1(@NotNull final String defaultLibraryName) {
        super(defaultLibraryName);
      }
      
      HashMap<OrderRootType, List<String>> roots;
    }
    
    final XtendLibraryPresentationProvider provider = IterableExtensions.<XtendLibraryPresentationProvider>head(XtendLibraryDescription.getProviders());
    if ((provider == null)) {
      return null;
    }
    return new __XtendLibraryDescription_1(XtendLibraryDescription.XTEND_LIBRARY_NAME) {
      {
        roots = XtendLibraryDescription.this.libraryRoots();
      }
      @Override
      public void addRoots(final LibraryEditor editor) {
        final BiConsumer<OrderRootType, List<String>> _function = (OrderRootType k, List<String> v) -> {
          final Consumer<String> _function_1 = (String it) -> {
            editor.addRoot(it, k);
          };
          v.forEach(_function_1);
        };
        this.roots.forEach(_function);
      }
    };
  }
  
  @Override
  public Set<? extends LibraryKind> getSuitableLibraryKinds() {
    return XtendLibraryDescription.LIBRARY_KINDS;
  }
  
  public HashMap<OrderRootType, List<String>> libraryRoots() {
    final Function1<Class<?>, String> _function = (Class<?> it) -> {
      return this.getUrlForLibraryRoot(it);
    };
    List<String> _map = ListExtensions.map(XtendLibraryDescription.getDetectorClasses(), _function);
    Pair<OrderRootType, List<String>> _mappedTo = Pair.<OrderRootType, List<String>>of(OrderRootType.CLASSES, _map);
    return CollectionLiterals.<OrderRootType, List<String>>newHashMap(_mappedTo);
  }
  
  public static List<? extends Class<?>> getDetectorClasses() {
    return Collections.<Class<?>>unmodifiableList(CollectionLiterals.<Class<?>>newArrayList(Lists.class, ToStringBuilder.class, Active.class, Data.class));
  }
  
  @Override
  public LibrariesContainer.LibraryLevel getDefaultLevel() {
    return LibrariesContainer.LibraryLevel.PROJECT;
  }
  
  protected String getUrlForLibraryRoot(final Class<?> clazz) {
    String _xblockexpression = null;
    {
      String _jarPathForClass = PathUtil.getJarPathForClass(clazz);
      final File libraryRoot = new File(_jarPathForClass);
      LocalFileSystem.getInstance().refreshAndFindFileByIoFile(libraryRoot);
      _xblockexpression = VfsUtil.getUrlForLibraryRoot(libraryRoot);
    }
    return _xblockexpression;
  }
  
  protected static List<XtendLibraryPresentationProvider> getProviders() {
    return ContainerUtil.<LibraryPresentationProvider, XtendLibraryPresentationProvider>findAll(LibraryPresentationProvider.EP_NAME.getExtensions(), XtendLibraryPresentationProvider.class);
  }
}
