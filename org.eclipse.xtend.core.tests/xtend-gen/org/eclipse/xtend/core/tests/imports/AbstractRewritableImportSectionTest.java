package org.eclipse.xtend.core.tests.imports;

import com.google.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend.core.tests.AbstractXtendTestCase;
import org.eclipse.xtend.core.xtend.XtendFile;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.formatting.IWhitespaceInformationProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.ReplaceRegion;
import org.eclipse.xtext.xbase.imports.RewritableImportSection;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public abstract class AbstractRewritableImportSectionTest extends AbstractXtendTestCase {
  @Inject
  @Extension
  private RewritableImportSection.Factory _factory;
  
  @Inject
  @Extension
  private TypeReferences _typeReferences;
  
  private XtendFile xtendFile;
  
  private String model;
  
  @Inject
  private IWhitespaceInformationProvider whitespaceInformationProvider;
  
  @Test
  public void testSimpleAdd() {
    final RewritableImportSection section = this.getSection(Set.class);
    this.addImport(section, List.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.Set");
    _builder.newLine();
    _builder.append("import java.util.List");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testSimpleAddAsString() {
    final RewritableImportSection section = this.getSection(Set.class);
    section.addImport("java.util.List");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.Set");
    _builder.newLine();
    _builder.append("import java.util.List");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testSimpleAddAsString_1() {
    final RewritableImportSection section = this.getSection(Set.class);
    section.addImport("org.eclipse.xtext.xbase.lib.InputOutput");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.Set");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testAddNullMemberAsString() {
    final RewritableImportSection section = this.getSection(Set.class);
    try {
      section.addStaticImport("java.util.List", null);
      Assert.fail();
    } catch (final Throwable _t) {
      if (_t instanceof IllegalArgumentException) {
        final IllegalArgumentException e = (IllegalArgumentException)_t;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    try {
      section.addStaticExtensionImport("java.util.List", null);
      Assert.fail();
    } catch (final Throwable _t_1) {
      if (_t_1 instanceof IllegalArgumentException) {
        final IllegalArgumentException e_1 = (IllegalArgumentException)_t_1;
      } else {
        throw Exceptions.sneakyThrow(_t_1);
      }
    }
  }
  
  @Test
  public void testAddNullTypeAsString() {
    final RewritableImportSection section = this.getSection(Set.class);
    try {
      section.addStaticExtensionImport(((String) null), "");
      Assert.fail();
    } catch (final Throwable _t) {
      if (_t instanceof IllegalArgumentException) {
        final IllegalArgumentException e = (IllegalArgumentException)_t;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    try {
      section.addStaticImport(((String) null), "");
      Assert.fail();
    } catch (final Throwable _t_1) {
      if (_t_1 instanceof IllegalArgumentException) {
        final IllegalArgumentException e_1 = (IllegalArgumentException)_t_1;
      } else {
        throw Exceptions.sneakyThrow(_t_1);
      }
    }
  }
  
  @Test
  public void testVariousAdd() {
    final RewritableImportSection section = this.getSection();
    this.addExtensionImport(section, Set.class);
    this.addStaticImport(section, Collections.class);
    this.addImport(section, List.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static extension java.util.Set.*");
    _builder.newLine();
    _builder.append("import static java.util.Collections.*");
    _builder.newLine();
    _builder.append("import java.util.List");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testVariousAdd_2() {
    final RewritableImportSection section = this.getSection();
    this.addExtensionImport(section, Set.class);
    this.addStaticImport(section, Set.class);
    this.addImport(section, Set.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static extension java.util.Set.*");
    _builder.newLine();
    _builder.append("import java.util.Set");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testVariousAdd_3() {
    final RewritableImportSection section = this.getSection();
    this.addStaticImport(section, Set.class);
    this.addExtensionImport(section, Set.class);
    this.addImport(section, Set.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static extension java.util.Set.*");
    _builder.newLine();
    _builder.append("import java.util.Set");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testVariousAddAsString() {
    final RewritableImportSection section = this.getSection();
    section.addStaticExtensionImport("java.util.Set", "*");
    section.addStaticImport("java.util.Collections", "*");
    section.addStaticImport("org.eclipse.xtext.xbase.lib.InputOutput", "println");
    section.addImport("java.util.List");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static extension java.util.Set.*");
    _builder.newLine();
    _builder.append("import static java.util.Collections.*");
    _builder.newLine();
    _builder.append("import java.util.List");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testVariousAddAsString_2() {
    final RewritableImportSection section = this.getSection();
    section.addStaticExtensionImport("java.util.Set", "*");
    section.addStaticImport("java.util.Collections", "*");
    section.addStaticImport("org.eclipse.xtext.xbase.lib.InputOutput", "println");
    section.addImport("java.util.List");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static extension java.util.Set.*");
    _builder.newLine();
    _builder.append("import static java.util.Collections.*");
    _builder.newLine();
    _builder.append("import java.util.List");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testVariousAddAsString_3() {
    final RewritableImportSection section = this.getSection();
    section.addStaticExtensionImport("com.google.common.base.Strings", "*");
    section.addStaticImport("com.google.common.base.Strings", "*");
    section.addStaticImport("com.google.common.base.Strings", "emptyToNull");
    section.addImport("com.google.common.base.Strings");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static extension com.google.common.base.Strings.*");
    _builder.newLine();
    _builder.append("import com.google.common.base.Strings");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testVariousAddAsString_4() {
    final RewritableImportSection section = this.getStaticSection(Collections.class);
    section.addStaticImport("java.util.Collections", "*");
    section.addStaticImport("java.util.Collections", "sort");
    section.addImport("java.util.Collections");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static java.util.Collections.*");
    _builder.newLine();
    _builder.append("import java.util.Collections");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testDoubleAdd() {
    final RewritableImportSection section = this.getSection(List.class);
    this.addImport(section, List.class);
    this.addImport(section, List.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.List");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testDoubleAdd_2() {
    final RewritableImportSection section = this.getSection();
    this.addExtensionImport(section, Collections.class);
    this.addExtensionImport(section, Collections.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static extension java.util.Collections.*");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testDoubleAdd_3() {
    final RewritableImportSection section = this.getSection();
    this.addStaticImport(section, Collections.class);
    this.addStaticImport(section, Collections.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static java.util.Collections.*");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testDoubleAddAsString() {
    final RewritableImportSection section = this.getSection(List.class);
    section.addImport("java.util.List");
    section.addImport("java.util.List");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.List");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testDoubleAddAsString_2() {
    final RewritableImportSection section = this.getSection();
    section.addStaticExtensionImport("java.util.Collections", "*");
    section.addStaticExtensionImport("java.util.Collections", "*");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static extension java.util.Collections.*");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testDoubleAddAsString_3() {
    final RewritableImportSection section = this.getSection();
    section.addStaticImport("java.util.Collections", "*");
    section.addStaticImport("java.util.Collections", "*");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static java.util.Collections.*");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testDoubleAddAsString_4() {
    final RewritableImportSection section = this.getSection(Collections.class);
    section.addStaticImport("java.util.Collections", "*");
    section.addStaticImport("java.util.Collections", "sort");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.Collections");
    _builder.newLine();
    _builder.append("import static java.util.Collections.*");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testDoubleAddAsString_5() {
    final RewritableImportSection section = this.getSection(Collections.class);
    section.addStaticImport("java.util.Collections", "sort");
    section.addStaticImport("java.util.Collections", "*");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.Collections");
    _builder.newLine();
    _builder.append("import static java.util.Collections.sort");
    _builder.newLine();
    _builder.append("import static java.util.Collections.*");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testDoubleAddAsString_6() {
    final RewritableImportSection section = this.getSection(Collections.class);
    section.addStaticExtensionImport("java.util.Collections", "*");
    section.addStaticExtensionImport("java.util.Collections", "sort");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.Collections");
    _builder.newLine();
    _builder.append("import static extension java.util.Collections.*");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testDoubleAddAsString_7() {
    final RewritableImportSection section = this.getSection(Collections.class);
    section.addStaticExtensionImport("java.util.Collections", "sort");
    section.addStaticExtensionImport("java.util.Collections", "*");
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.Collections");
    _builder.newLine();
    _builder.append("import static extension java.util.Collections.sort");
    _builder.newLine();
    _builder.append("import static extension java.util.Collections.*");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testSort() {
    final RewritableImportSection section = this.getSection();
    this.addExtensionImport(section, Set.class);
    this.addImport(section, Set.class);
    this.addStaticImport(section, Collections.class);
    this.addImport(section, List.class);
    section.setSort(true);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.List");
    _builder.newLine();
    _builder.append("import java.util.Set");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import static java.util.Collections.*");
    _builder.newLine();
    _builder.newLine();
    _builder.append("import static extension java.util.Set.*");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testSimpleRemove() {
    final RewritableImportSection section = this.getSection(List.class, Set.class);
    this.removeImport(section, Set.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.List");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testRemoveAll() {
    final RewritableImportSection section = this.getSection(List.class, Set.class);
    this.removeImport(section, Set.class);
    this.removeImport(section, List.class);
    StringConcatenation _builder = new StringConcatenation();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testRemoveAdd() {
    final RewritableImportSection section = this.getSection(List.class);
    this.removeImport(section, List.class);
    this.addImport(section, List.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.List");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testRemoveAdd_2() {
    final RewritableImportSection section = this.getSection(List.class, Set.class);
    this.removeImport(section, List.class);
    this.addImport(section, List.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.Set");
    _builder.newLine();
    _builder.append("import java.util.List");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testRemoveAddRemove() {
    final RewritableImportSection section = this.getSection(List.class);
    this.removeImport(section, List.class);
    this.addImport(section, List.class);
    this.removeImport(section, List.class);
    StringConcatenation _builder = new StringConcatenation();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testRemoveAddRemoveAdd() {
    final RewritableImportSection section = this.getSection(List.class);
    this.removeImport(section, List.class);
    this.addImport(section, List.class);
    this.removeImport(section, List.class);
    this.addImport(section, List.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.List");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testRemoveAdd_3() {
    final RewritableImportSection section = this.getSection(List.class);
    this.removeImport(section, List.class);
    this.addStaticImport(section, List.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static java.util.List.*");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testRemoveAdd_4() {
    final RewritableImportSection section = this.getSection(List.class);
    this.removeImport(section, List.class);
    this.addStaticImport(section, List.class);
    this.addExtensionImport(section, List.class);
    this.removeStaticImport(section, List.class);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import static extension java.util.List.*");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  @Test
  public void testRenameRefactoringScenario() {
    final RewritableImportSection section = this.getSection(List.class);
    final JvmDeclaredType importedType = IterableExtensions.<JvmDeclaredType>head(section.getImportedTypes("List"));
    Assert.assertNotNull(importedType);
    importedType.setSimpleName("NewList");
    section.removeImport(importedType);
    section.addImport(importedType);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import java.util.NewList");
    _builder.newLine();
    this.assertEquals(section, _builder);
  }
  
  protected RewritableImportSection getImportSection(final CharSequence model) {
    try {
      Resource _eResource = this.file(model.toString()).eResource();
      return this._factory.parse(((XtextResource) _eResource));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected boolean addImport(final RewritableImportSection section, final Class<?> javaClass) {
    return section.addImport(this.jvmType(javaClass));
  }
  
  protected boolean removeImport(final RewritableImportSection section, final Class<?> javaClass) {
    return section.removeImport(this.jvmType(javaClass));
  }
  
  protected boolean addStaticImport(final RewritableImportSection section, final Class<?> javaClass) {
    return section.addStaticImport(this.jvmType(javaClass));
  }
  
  protected boolean removeStaticImport(final RewritableImportSection section, final Class<?> javaClass) {
    return section.removeStaticImport(this.jvmType(javaClass), null);
  }
  
  protected boolean addExtensionImport(final RewritableImportSection section, final Class<?> javaClass) {
    return section.addStaticExtensionImport(this.jvmType(javaClass));
  }
  
  protected boolean removeExtensionImport(final RewritableImportSection section, final Class<?> javaClass) {
    return section.removeStaticExtensionImport(this.jvmType(javaClass), null);
  }
  
  protected JvmDeclaredType jvmType(final Class<?> javaClass) {
    JvmDeclaredType _xblockexpression = null;
    {
      final JvmType type = this._typeReferences.findDeclaredType(javaClass, this.xtendFile);
      Assert.assertTrue((type instanceof JvmDeclaredType));
      _xblockexpression = ((JvmDeclaredType) type);
    }
    return _xblockexpression;
  }
  
  protected RewritableImportSection getSection(final Class<?>... types) {
    try {
      RewritableImportSection _xblockexpression = null;
      {
        this.model = this.getModel(false, types).toString();
        this.xtendFile = this.file(this.model);
        Resource _eResource = this.xtendFile.eResource();
        _xblockexpression = this._factory.parse(((XtextResource) _eResource));
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected RewritableImportSection getStaticSection(final Class<?>... types) {
    try {
      RewritableImportSection _xblockexpression = null;
      {
        this.model = this.getModel(true, types).toString();
        this.xtendFile = this.file(this.model);
        Resource _eResource = this.xtendFile.eResource();
        _xblockexpression = this._factory.parse(((XtextResource) _eResource));
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected void assertEquals(final RewritableImportSection section, final CharSequence sectionAsString) {
    final StringBuilder builder = new StringBuilder(this.model);
    final List<ReplaceRegion> changes = section.rewrite();
    final Function1<ReplaceRegion, Integer> _function = (ReplaceRegion it) -> {
      return Integer.valueOf(it.getOffset());
    };
    List<ReplaceRegion> _reverse = ListExtensions.<ReplaceRegion>reverse(IterableExtensions.<ReplaceRegion, Integer>sortBy(changes, _function));
    for (final ReplaceRegion it : _reverse) {
      int _offset = it.getOffset();
      int _offset_1 = it.getOffset();
      int _length = it.getLength();
      int _plus = (_offset_1 + _length);
      builder.replace(_offset, _plus, it.getText());
    }
    Assert.assertEquals(this.processLinebreaks(this.getExpectedModel(sectionAsString.toString())), this.processLinebreaks(builder));
  }
  
  protected String processLinebreaks(final CharSequence sequence) {
    String _xblockexpression = null;
    {
      final String lineSeparator = this.whitespaceInformationProvider.getLineSeparatorInformation(this.xtendFile.eResource().getURI()).getLineSeparator();
      String _xifexpression = null;
      boolean _isIgnoreLinebreaks = this.isIgnoreLinebreaks();
      if (_isIgnoreLinebreaks) {
        _xifexpression = sequence.toString().replaceAll((("(" + lineSeparator) + ")+"), " ");
      } else {
        _xifexpression = sequence.toString();
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  protected boolean isIgnoreLinebreaks() {
    return false;
  }
  
  protected abstract CharSequence getModel(final boolean isStatic, final Class<?>[] types);
  
  protected abstract CharSequence getExpectedModel(final String sectionAsString);
}
