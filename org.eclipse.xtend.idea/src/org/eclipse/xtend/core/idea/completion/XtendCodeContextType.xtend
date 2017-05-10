/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.idea.completion

import com.google.inject.Inject
import com.intellij.codeInsight.template.EverywhereContextType
import com.intellij.codeInsight.template.TemplateContextType
import com.intellij.lang.ParserDefinition
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.TokenSet
import java.util.HashSet
import java.util.concurrent.atomic.AtomicBoolean
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtend.core.idea.editorActions.XtendTokenSetProvider
import org.eclipse.xtend.core.idea.lang.XtendLanguage
import org.eclipse.xtend.core.services.XtendGrammarAccess
import org.eclipse.xtend.core.xtend.XtendPackage
import org.eclipse.xtext.AbstractElement
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElementComputer
import org.eclipse.xtext.ide.editor.contentassist.antlr.IContentAssistParser

/**
 * @author Sven Efftinge - Initial contribution and API
 */
abstract class XtendCodeContextType extends TemplateContextType {

	@Inject protected XtendGrammarAccess grammarAccess
	@Inject protected IContentAssistParser contentAssistParser
	@Inject protected FollowElementComputer followElementComputer
	@Inject protected ParserDefinition parserDefinition
	@Inject protected XtendTokenSetProvider tokenSetProvider

	protected HashSet<TokenSet> tokenSets = newHashSet
	protected HashSet<AbstractElement> followElements = newHashSet

	protected new(String id, String presentableName, Class<? extends TemplateContextType> parent) {
		super(id, presentableName, parent)
		XtendLanguage.INSTANCE.injectMembers(this)
	}

	protected def registerFollowElementsfor(EStructuralFeature eStructuralFeature) {
		followElementComputer.collectAbstractElements(grammarAccess.grammar, eStructuralFeature) [
			followElements += it
		]
	}

	final override isInContext(PsiFile file, int offset) {
		if (file.language !== XtendLanguage.INSTANCE)
			return false;
		return internalIsInContext(file, offset)
	}

	def boolean internalIsInContext(PsiFile file, int offset) {
		if (tokenSets.empty && followElements.empty)
			return true

		if (!tokenSets.empty) {
			val tokenSet = getTokenSet(file, offset)
			if (!tokenSets.contains(tokenSet))
				return false
			if (followElements.empty)
				return true
		}

		if (followElements.empty)
			return false

		val element = file.findElementAt(offset)
		if (element?.node === null)
			return false

		val elements = contentAssistParser.getFollowElements(file.text.substring(0, element.node.startOffset), false)
		val hasFollowElement = new AtomicBoolean(false)
		followElementComputer.computeFollowElements(elements) [
			if (!hasFollowElement.get && followElements.contains(it)) {
				hasFollowElement.set(true)
			}
		]
		return hasFollowElement.get
	}
	
	protected def getTokenSet(PsiFile file, int offset) {
		val lexer = parserDefinition.createLexer(file.project)
		lexer.start(file.text)
		while (lexer.tokenType !== null && lexer.tokenEnd <= offset)
			lexer.advance

		val tokenType = lexer.tokenType
		if (tokenType === null)
			return false

		tokenSetProvider.getTokenSet(tokenType)
	}

	override toString() {
		contextId
	}

	static class Generic extends XtendCodeContextType {

		new() {
			super("xtend.generic", "Xtend", EverywhereContextType)
		}

		override internalIsInContext(PsiFile file, int offset) {
			return true
		}

	}

	static class Statement extends XtendCodeContextType {

		new() {
			super("xtend.statement", "Statement", Generic)
			tokenSets += tokenSetProvider.defaultTokens
			followElements += grammarAccess.XVariableDeclarationAccess.valKeyword_0_0_1_0_0_1
		}

	}

	static class Expression extends XtendCodeContextType {

		new() {
			super("xtend.expression", "Expression", Statement)
			tokenSets += tokenSetProvider.defaultTokens
			followElements += grammarAccess.XPrimaryExpressionAccess.XFeatureCallParserRuleCall_4
		}

	}

	static class Member extends XtendCodeContextType {

		new() {
			super("xtend.member", "Member", Generic)
			tokenSets += tokenSetProvider.defaultTokens
			registerFollowElementsfor(XtendPackage.Literals.XTEND_TYPE_DECLARATION__MEMBERS)
		}

	}

	static class TemplateExpression extends XtendCodeContextType {

		new() {
			super("xtend.template", "Template", Generic)
			tokenSets += tokenSetProvider.richStringLiteralTokens
		}

	}

}