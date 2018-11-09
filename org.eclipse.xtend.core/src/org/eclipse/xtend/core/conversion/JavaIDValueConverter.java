/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.conversion;

import org.antlr.runtime.TokenSource;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.ValueConverterWithValueException;
import org.eclipse.xtext.conversion.impl.IDValueConverter;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.util.JavaStringConverter;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class JavaIDValueConverter extends IDValueConverter {
	
	@Override
	public String toValue(String string, INode node) {
		if (string == null)
			return null;
		try {
			if (string.charAt(0) == '^') {
				string = string.substring(1);
			}
			String result = convertFromJavaIdentifier(string, node);
			return result;
		} catch (IllegalArgumentException e) {
			throw new ValueConverterException(e.getMessage(), node, e);
		}
	}
	
	@Override
	protected void assertValidValue(String value) {
		super.assertValidValue(value);
		if (!isValidIdentifierStart(value.charAt(0))) {
			throw new ValueConverterException(value + " is not a valid identifier.", null, null);
		}
		for(int i = 1, length = value.length(); i < length; i++) {
			if (!isValidIdentifierPart(value.charAt(i))) {
				throw new ValueConverterException(value + " is not a valid identifier.", null, null);
			}
		}
	}
	
	@Override
	protected void assertTokens(String value, String result) {
		// ok
	}
	
	@Override
	protected TokenSource getTokenSource(String escapedValue) {
		throw new UnsupportedOperationException();
	}
	
	public static boolean isValidIdentifierStart(char c) {
		return Character.isJavaIdentifierStart(c);
	}
	
	public static boolean isValidIdentifierPart(char c) {
		return Character.isJavaIdentifierPart(c);
	}

	public static String convertFromJavaIdentifier(String identifier, INode node) {
		int idx = identifier.indexOf('\\');
		if (idx < 0) {
			return identifier;
		}
		Implementation converter = new Implementation();
		String result = converter.convertFromJavaString(identifier, idx);
		if (converter.error) {
			throw new ValueConverterWithValueException("Illegal escape sequence in identifier '" + identifier + "'", node, result, null);
		}
		if (converter.badChar) {
			if (result.length() != 0)
				throw new ValueConverterWithValueException("Illegal character in identifier '" + result + "' (" + identifier + ")", node, result, null);
			else
				throw new ValueConverterWithValueException("Illegal character in identifier '" + identifier + "'", node, null, null);
		}
		return result;
	}

	protected static class Implementation extends JavaStringConverter {
		boolean error = false;
		boolean badChar = false;
		
		protected Implementation() {}
		
		/**
		 * Converts a string with valid or invalid escape sequences to a semantic value.
		 * If the escape sequences are invalid, a {@link ValueConverterException} is thrown
		 * with detailed information about the broken character combination.
		 */
		public String convertFromJavaString(String identifier, int firstEscapeSequence) throws ValueConverterException {
			StringBuilder result = new StringBuilder(identifier.length());
			result.append(identifier, 0, firstEscapeSequence);
			return convertFromJavaString(identifier, true, firstEscapeSequence, result);
		}
		
		@Override
		protected boolean isInvalidUnicodeEscapeSequence(String string, int index) {
			return super.isInvalidUnicodeEscapeSequence(string, index) || !isHexSequence(string, index, 4);
		}
		
		@Override
		protected int handleInvalidUnicodeEscapeSequnce(String string, int index, StringBuilder result) {
			result.append('u');
			error = true;
			return index;
		}
		
		@Override
		protected int doUnescapeCharAndAppendTo(String string, boolean useUnicode, int index, StringBuilder result) {
			if (string.length() == index) {
				badChar = true;
				return index;
			}
			return super.doUnescapeCharAndAppendTo(string, useUnicode, index, result);
		}
		
		@Override
		protected int handleUnknownEscapeSequence(String string, char c, boolean useUnicode, int index, StringBuilder result) {
			badChar = true;
			return index;
		}
		
		@Override
		protected boolean validate(StringBuilder result, char c) {
			if (result.length() == 0) {
				if (!isValidIdentifierStart(c)) {
					badChar = true;
					return false;
				}
			} else {
				if (!isValidIdentifierPart(c)) {
					badChar = true;
					return false;
				}
			}
			return super.validate(result, c);
		}
		
	}
	
}
