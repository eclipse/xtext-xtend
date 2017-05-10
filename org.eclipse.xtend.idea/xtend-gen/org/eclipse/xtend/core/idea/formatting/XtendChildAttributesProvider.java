/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.idea.formatting;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import com.intellij.formatting.Indent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend.core.services.XtendGrammarAccess;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.xbase.idea.formatting.XbaseChildAttributesProvider;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class XtendChildAttributesProvider extends XbaseChildAttributesProvider {
  @Inject
  @Extension
  private XtendGrammarAccess _xtendGrammarAccess;
  
  @Override
  protected Indent getIndentAfter(final EObject grammarElement) {
    boolean _matched = false;
    Keyword _colonKeyword_5_1 = this._xtendGrammarAccess.getXSwitchExpressionAccess().getColonKeyword_5_1();
    if (Objects.equal(grammarElement, _colonKeyword_5_1)) {
      _matched=true;
    }
    if (!_matched) {
      Keyword _equalsSignKeyword_2_0 = this._xtendGrammarAccess.getXVariableDeclarationAccess().getEqualsSignKeyword_2_0();
      if (Objects.equal(grammarElement, _equalsSignKeyword_2_0)) {
        _matched=true;
      }
    }
    if (_matched) {
      return Indent.getNormalIndent();
    }
    return super.getIndentAfter(grammarElement);
  }
}
