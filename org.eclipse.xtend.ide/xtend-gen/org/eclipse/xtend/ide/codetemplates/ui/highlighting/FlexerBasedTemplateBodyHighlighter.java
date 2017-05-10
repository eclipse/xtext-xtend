/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.ide.codetemplates.ui.highlighting;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.io.StringReader;
import org.antlr.runtime.Token;
import org.eclipse.xtend.core.parser.antlr.internal.FlexTokenSource;
import org.eclipse.xtend.core.parser.antlr.internal.FlexerFactory;
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.parser.antlr.TokenTool;
import org.eclipse.xtext.ui.codetemplates.ui.highlighting.TemplateBodyHighlighter;
import org.eclipse.xtext.ui.editor.syntaxcoloring.AbstractAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.xbase.lib.Extension;

/**
 * @author Anton Kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class FlexerBasedTemplateBodyHighlighter extends TemplateBodyHighlighter {
  @Inject
  @Extension
  private FlexerFactory _flexerFactory;
  
  @Inject
  @Extension
  private AbstractAntlrTokenToAttributeIdMapper _abstractAntlrTokenToAttributeIdMapper;
  
  @Override
  public void doProvideHighlightingFor(final String body, final IHighlightedPositionAcceptor acceptor) {
    StringReader _stringReader = new StringReader(body);
    final FlexTokenSource tokenSource = this._flexerFactory.createTokenSource(_stringReader);
    Token token = tokenSource.nextToken();
    while ((!Objects.equal(token, Token.EOF_TOKEN))) {
      {
        final String id = this._abstractAntlrTokenToAttributeIdMapper.getId(token.getType());
        final int offset = TokenTool.getOffset(token);
        final int length = TokenTool.getLength(token);
        acceptor.addPosition(offset, length, id);
        token = tokenSource.nextToken();
      }
    }
  }
}
