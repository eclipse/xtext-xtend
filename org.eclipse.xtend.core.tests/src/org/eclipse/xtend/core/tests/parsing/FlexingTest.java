/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.parsing;

import java.io.StringReader;
import java.util.List;

import org.antlr.runtime.Token;
import org.eclipse.xtend.core.parser.antlr.internal.FlexTokenSource;
import org.eclipse.xtend.core.parser.antlr.internal.FlexerFactory;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.util.Pair;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class FlexingTest extends LexingTest {

	@SuppressWarnings("unchecked")
	@Override
	protected void assertLexing(String input, Pair<String,String>... expectedTokens) {
		FlexTokenSource tokenSource = new FlexerFactory().createTokenSource(new StringReader(input));
		XtextTokenStream tokenStream = new XtextTokenStream(tokenSource, getTokenDefProvider());
		List<?> tokens = tokenStream.getTokens();
		assertEquals(input + " / " + tokens, expectedTokens.length, tokens.size());
		for(int i = 0;i < tokens.size(); i++) {
			Token token = (Token) tokens.get(i);
			assertEquals(token.toString(), expectedTokens[i].getFirst(), token.getText());
			final String expected = expectedTokens[i].getSecond();
			String actual = getTokenDefProvider().getTokenDefMap().get(token.getType());
			assertEquals("expected "+expected+" but was "+actual, expected, actual);
		}
	}
	
}
