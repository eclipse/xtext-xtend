/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.ide.tests.builder;

import com.google.inject.Inject;
import java.util.ArrayList;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.xtend.ide.tests.WorkbenchTestHelper;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.junit.Assert;

/**
 * @author Anton Kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class JavaEditorExtension {
  private final static Boolean VERBOSE = Boolean.FALSE;
  
  @Inject
  @Extension
  private WorkbenchTestHelper _workbenchTestHelper;
  
  public ITextEditor reconcile(final String fileName, final String oldText, final String newText) {
    return this.reconcile(this.openJavaEditor(fileName), oldText, newText);
  }
  
  public ITextEditor reconcile(final ITextEditor editor, final String oldText, final String newText) {
    final Function1<ITextEditor, ITextEditor> _function = (ITextEditor it) -> {
      return this.changeContent(it, oldText, newText);
    };
    return this.reconcile(editor, _function);
  }
  
  public ITextEditor reconcile(final ITextEditor editor, final Function1<? super ITextEditor, ? extends ITextEditor> consumer) {
    ITextEditor _xblockexpression = null;
    {
      if ((consumer == null)) {
        return editor;
      }
      final Procedure0 _function = () -> {
        consumer.apply(editor);
      };
      this.waitForPostReconcileEvent(_function);
      _xblockexpression = editor;
    }
    return _xblockexpression;
  }
  
  public ITextEditor save(final ITextEditor editor) {
    return this.save(editor, null);
  }
  
  public ITextEditor save(final ITextEditor editor, final Function1<? super ITextEditor, ? extends ITextEditor> consumer) {
    ITextEditor _xblockexpression = null;
    {
      final Procedure0 _function = () -> {
        if ((consumer != null)) {
          consumer.apply(editor);
        }
        Assert.assertTrue(this._workbenchTestHelper.saveEditor(editor, false));
      };
      this.waitForPostChangeEvent(_function);
      _xblockexpression = editor;
    }
    return _xblockexpression;
  }
  
  public ITextEditor close(final ITextEditor editor) {
    return this.close(editor, null);
  }
  
  public ITextEditor close(final ITextEditor editor, final Function1<? super ITextEditor, ? extends ITextEditor> consumer) {
    ITextEditor _xblockexpression = null;
    {
      final Procedure0 _function = () -> {
        if ((consumer != null)) {
          consumer.apply(editor);
        }
        Assert.assertTrue(this._workbenchTestHelper.closeEditor(editor, false));
      };
      this.waitForPostChangeEvent(_function);
      _xblockexpression = editor;
    }
    return _xblockexpression;
  }
  
  public String waitForPostReconcileEvent(final Procedure0 producer) {
    return this.waitForElementChangedEvent(ElementChangedEvent.POST_RECONCILE, producer);
  }
  
  public String waitForPostChangeEvent(final Procedure0 producer) {
    return this.waitForElementChangedEvent(ElementChangedEvent.POST_CHANGE, producer);
  }
  
  public String waitForElementChangedEvent(final int eventMask, final Procedure0 producer) {
    String _xblockexpression = null;
    {
      if ((JavaEditorExtension.VERBOSE).booleanValue()) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("start waiting for an element changed event: ");
        _builder.append(eventMask);
        InputOutput.<String>println(_builder.toString());
      }
      final ArrayList<Boolean> changed = CollectionLiterals.<Boolean>newArrayList(Boolean.valueOf(false));
      final IElementChangedListener _function = new IElementChangedListener() {
        @Override
        public void elementChanged(final ElementChangedEvent it) {
          JavaCore.removeElementChangedListener(this);
          Boolean _head = IterableExtensions.<Boolean>head(changed);
          boolean _not = (!(_head).booleanValue());
          if (_not) {
            changed.set(0, Boolean.valueOf(true));
            if ((JavaEditorExtension.VERBOSE).booleanValue()) {
              InputOutput.<ElementChangedEvent>println(it);
            }
          }
        }
      };
      JavaCore.addElementChangedListener(_function, eventMask);
      producer.apply();
      while ((!(IterableExtensions.<Boolean>head(changed)).booleanValue())) {
      }
      String _xifexpression = null;
      if ((JavaEditorExtension.VERBOSE).booleanValue()) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("end waiting for an element changed event: ");
        _builder_1.append(eventMask);
        _xifexpression = InputOutput.<String>println(_builder_1.toString());
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public ITextEditor openJavaEditor(final String fileName) {
    try {
      return this._workbenchTestHelper.openLikeTextEditor(this._workbenchTestHelper.getFile(fileName));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public ITextEditor changeContent(final ITextEditor editor, final String oldText, final String newText) {
    try {
      ITextEditor _xblockexpression = null;
      {
        final IDocument document = editor.getDocumentProvider().getDocument(editor.getEditorInput());
        final String model = document.get();
        document.replace(model.indexOf(oldText), oldText.length(), newText);
        _xblockexpression = editor;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
