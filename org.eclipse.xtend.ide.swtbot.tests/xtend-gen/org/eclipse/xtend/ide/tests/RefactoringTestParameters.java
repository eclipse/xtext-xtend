/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.ide.tests;

import org.eclipse.xtend.lib.Property;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class RefactoringTestParameters {
  @Property
  private boolean _useInlineRefactoring = true;
  
  @Property
  private boolean _saveAllBeforeRefactoring = true;
  
  @Property
  private boolean _usePreview = false;
  
  @Override
  public String toString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("(");
    {
      boolean _isUseInlineRefactoring = this.isUseInlineRefactoring();
      if (_isUseInlineRefactoring) {
        _builder.append("inline");
      } else {
        _builder.append("dialog");
      }
    }
    _builder.append(", ");
    {
      boolean _isSaveAllBeforeRefactoring = this.isSaveAllBeforeRefactoring();
      if (_isSaveAllBeforeRefactoring) {
        _builder.append("save all");
      } else {
        _builder.append("no save");
      }
    }
    _builder.append(", ");
    {
      boolean _isUsePreview = this.isUsePreview();
      if (_isUsePreview) {
        _builder.append("preview");
      } else {
        _builder.append("no preview");
      }
    }
    _builder.append(")");
    return _builder.toString();
  }
  
  @Pure
  public boolean isUseInlineRefactoring() {
    return this._useInlineRefactoring;
  }
  
  public void setUseInlineRefactoring(final boolean useInlineRefactoring) {
    this._useInlineRefactoring = useInlineRefactoring;
  }
  
  @Pure
  public boolean isSaveAllBeforeRefactoring() {
    return this._saveAllBeforeRefactoring;
  }
  
  public void setSaveAllBeforeRefactoring(final boolean saveAllBeforeRefactoring) {
    this._saveAllBeforeRefactoring = saveAllBeforeRefactoring;
  }
  
  @Pure
  public boolean isUsePreview() {
    return this._usePreview;
  }
  
  public void setUsePreview(final boolean usePreview) {
    this._usePreview = usePreview;
  }
}
