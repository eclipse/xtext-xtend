/*******************************************************************************
 * Copyright (c) 2012, 2016 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.ide;

import com.google.inject.Binder;
import com.google.inject.name.Names;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.xtend.core.linking.Linker;
import org.eclipse.xtend.core.macro.AbstractFileSystemSupport;
import org.eclipse.xtend.core.macro.ProcessorInstanceForJvmTypeProvider;
import org.eclipse.xtend.core.macro.declaration.IResourceChangeRegistry;
import org.eclipse.xtend.core.resource.XtendResourceDescriptionManager;
import org.eclipse.xtend.ide.autoedit.AutoEditStrategyProvider;
import org.eclipse.xtend.ide.autoedit.TokenTypeToPartitionMapper;
import org.eclipse.xtend.ide.builder.JavaProjectPreferencesInitializer;
import org.eclipse.xtend.ide.builder.SourceRelativeFileSystemAccess;
import org.eclipse.xtend.ide.builder.UIResourceChangeRegistry;
import org.eclipse.xtend.ide.builder.XtendParallelBuilderParticipant;
import org.eclipse.xtend.ide.builder.XtendUIResourceDescriptionManager;
import org.eclipse.xtend.ide.codetemplates.ui.highlighting.FlexerBasedTemplateBodyHighlighter;
import org.eclipse.xtend.ide.common.contentassist.antlr.DisabledInternalLexer;
import org.eclipse.xtend.ide.common.contentassist.antlr.FlexerBasedContentAssistContextFactory;
import org.eclipse.xtend.ide.common.contentassist.antlr.FlexerBasedContentAssistParser;
import org.eclipse.xtend.ide.common.contentassist.antlr.FlexerBasedPartialXtendContentAssistParser;
import org.eclipse.xtend.ide.common.contentassist.antlr.internal.InternalXtendLexer;
import org.eclipse.xtend.ide.common.editor.bracketmatching.XtendBracePairProvider;
import org.eclipse.xtend.ide.common.highlighting.XtendHighlightingCalculator;
import org.eclipse.xtend.ide.contentassist.EscapeSequenceAwarePrefixMatcher;
import org.eclipse.xtend.ide.contentassist.OperatorAwareComparator;
import org.eclipse.xtend.ide.contentassist.TemplateProposalProvider;
import org.eclipse.xtend.ide.contentassist.XtendContentAssistFactory;
import org.eclipse.xtend.ide.contentassist.XtendImportingTypesProposalProvider;
import org.eclipse.xtend.ide.contentassist.XtendTemplateContextType;
import org.eclipse.xtend.ide.contentassist.antlr.FlexProposalConflictHelper;
import org.eclipse.xtend.ide.editor.OccurrenceComputer;
import org.eclipse.xtend.ide.editor.OverrideIndicatorModelListener;
import org.eclipse.xtend.ide.editor.OverrideIndicatorRulerAction;
import org.eclipse.xtend.ide.editor.RichStringAwareSourceViewer;
import org.eclipse.xtend.ide.editor.RichStringAwareToggleCommentAction;
import org.eclipse.xtend.ide.editor.SingleLineCommentHelper;
import org.eclipse.xtend.ide.editor.XtendDoubleClickStrategyProvider;
import org.eclipse.xtend.ide.editor.XtendEditorErrorTickUpdater;
import org.eclipse.xtend.ide.editor.XtendFoldingRegionProvider;
import org.eclipse.xtend.ide.editor.XtendNatureAddingEditorCallback;
import org.eclipse.xtend.ide.editor.XtendSourceViewerConfiguration;
import org.eclipse.xtend.ide.editor.copyqualifiedname.XtendCopyQualifiedNameService;
import org.eclipse.xtend.ide.editor.model.XtendDocumentTokenSource;
import org.eclipse.xtend.ide.formatting.preferences.FormatterResourceProvider;
import org.eclipse.xtend.ide.highlighting.RichStringAwareTokenScanner;
import org.eclipse.xtend.ide.highlighting.TokenToAttributeIdMapper;
import org.eclipse.xtend.ide.highlighting.XtendHighlightingConfiguration;
import org.eclipse.xtend.ide.highlighting.XtendSyntaxColoringPreferencePage;
import org.eclipse.xtend.ide.hover.XtendAnnotationHover;
import org.eclipse.xtend.ide.hover.XtendHoverDocumentationProvider;
import org.eclipse.xtend.ide.hover.XtendHoverProvider;
import org.eclipse.xtend.ide.hover.XtendHoverSignatureProvider;
import org.eclipse.xtend.ide.hyperlinking.HyperLinkingLabelProvider;
import org.eclipse.xtend.ide.hyperlinking.XtendHyperlinkHelper;
import org.eclipse.xtend.ide.labeling.XtendLabelProvider;
import org.eclipse.xtend.ide.launching.XtendJavaElementDelegateJunitLaunch;
import org.eclipse.xtend.ide.macro.EclipseFileSystemSupportImpl;
import org.eclipse.xtend.ide.macro.JdtBasedProcessorProvider;
import org.eclipse.xtend.ide.outline.ShowSyntheticMembersContribution;
import org.eclipse.xtend.ide.outline.SwitchOutlineModeContribution;
import org.eclipse.xtend.ide.outline.XtendOutlineModes;
import org.eclipse.xtend.ide.outline.XtendOutlineNodeComparator;
import org.eclipse.xtend.ide.outline.XtendOutlineNodeFactory;
import org.eclipse.xtend.ide.outline.XtendOutlinePage;
import org.eclipse.xtend.ide.outline.XtendOutlineWithEditorLinker;
import org.eclipse.xtend.ide.outline.XtendQuickOutlineFilterAndSorter;
import org.eclipse.xtend.ide.preferences.XtendPreferenceStoreAccess;
import org.eclipse.xtend.ide.refactoring.XtendDependentElementsCalculator;
import org.eclipse.xtend.ide.refactoring.XtendExpressionUtil;
import org.eclipse.xtend.ide.refactoring.XtendJdtRenameParticipantProcessor;
import org.eclipse.xtend.ide.refactoring.XtendRefactoringPreferences;
import org.eclipse.xtend.ide.refactoring.XtendReferenceUpdater;
import org.eclipse.xtend.ide.refactoring.XtendRenameContextFactory;
import org.eclipse.xtend.ide.refactoring.XtendRenameElementProcessor;
import org.eclipse.xtend.ide.refactoring.XtendRenameStrategy;
import org.eclipse.xtend.ide.refactoring.XtendRenameStrategyProvider;
import org.eclipse.xtend.ide.refactoring.importer.StaticExtensionMethodImporter;
import org.eclipse.xtend.ide.refactoring.importer.StaticMethodImporter;
import org.eclipse.xtend.ide.validator.XtendResourceValidator;
import org.eclipse.xtend.ide.validator.XtendUIValidator;
import org.eclipse.xtend.ide.validator.preferences.XtendValidatorConfigurationBlock;
import org.eclipse.xtext.builder.EclipseOutputConfigurationProvider;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.builder.EclipseSourceFolderProvider;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.builder.JDTAwareSourceFolderProvider;
import org.eclipse.xtext.common.types.ui.refactoring.participant.JvmMemberRenameStrategy;
import org.eclipse.xtext.common.types.xtext.ui.ITypesProposalProvider;
import org.eclipse.xtext.generator.AbstractFileSystemAccess2;
import org.eclipse.xtext.generator.IContextualOutputConfigurationProvider;
import org.eclipse.xtext.generator.IShouldGenerate;
import org.eclipse.xtext.ide.LexerIdeBindings;
import org.eclipse.xtext.ide.editor.bracketmatching.IBracePairProvider;
import org.eclipse.xtext.ide.editor.contentassist.antlr.ContentAssistContextFactory;
import org.eclipse.xtext.ide.editor.contentassist.antlr.IContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;
import org.eclipse.xtext.ide.editor.partialEditing.IPartialEditingContentAssistParser;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.linking.ILinker;
import org.eclipse.xtext.parser.antlr.LexerProvider;
import org.eclipse.xtext.service.SingletonBinding;
import org.eclipse.xtext.ui.codetemplates.ui.highlighting.TemplateBodyHighlighter;
import org.eclipse.xtext.ui.editor.IXtextEditorCallback;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.XtextSourceViewer;
import org.eclipse.xtext.ui.editor.XtextSourceViewerConfiguration;
import org.eclipse.xtext.ui.editor.actions.IActionContributor;
import org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategy;
import org.eclipse.xtext.ui.editor.autoedit.AbstractEditStrategyProvider;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalComparator;
import org.eclipse.xtext.ui.editor.contentassist.IContentAssistantFactory;
import org.eclipse.xtext.ui.editor.contentassist.IContextInformationProvider;
import org.eclipse.xtext.ui.editor.contentassist.IProposalConflictHelper;
import org.eclipse.xtext.ui.editor.contentassist.ITemplateProposalProvider;
import org.eclipse.xtext.ui.editor.contentassist.PrefixMatcher;
import org.eclipse.xtext.ui.editor.doubleClicking.DoubleClickStrategyProvider;
import org.eclipse.xtext.ui.editor.embedded.IEditedResourceProvider;
import org.eclipse.xtext.ui.editor.findrefs.DelegatingReferenceFinder;
import org.eclipse.xtext.ui.editor.findrefs.IReferenceFinder;
import org.eclipse.xtext.ui.editor.folding.IFoldingRegionProvider;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.eclipse.xtext.ui.editor.hover.html.IEObjectHoverDocumentationProvider;
import org.eclipse.xtext.ui.editor.hyperlinking.HyperlinkLabelProvider;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;
import org.eclipse.xtext.ui.editor.model.DocumentTokenSource;
import org.eclipse.xtext.ui.editor.model.TerminalsTokenTypeToPartitionMapper;
import org.eclipse.xtext.ui.editor.occurrences.IOccurrenceComputer;
import org.eclipse.xtext.ui.editor.outline.IOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.actions.IOutlineContribution;
import org.eclipse.xtext.ui.editor.outline.actions.OutlineWithEditorLinker;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineFilterAndSorter.IComparator;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineNodeFactory;
import org.eclipse.xtext.ui.editor.outline.quickoutline.IQuickOutlineContribution;
import org.eclipse.xtext.ui.editor.outline.quickoutline.QuickOutlineFilterAndSorter;
import org.eclipse.xtext.ui.editor.preferences.IPreferenceStoreAccess;
import org.eclipse.xtext.ui.editor.preferences.IPreferenceStoreInitializer;
import org.eclipse.xtext.ui.editor.syntaxcoloring.AbstractAntlrTokenToAttributeIdMapper;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ShowWhitespaceCharactersActionContributor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.SyntaxColoringPreferencePage;
import org.eclipse.xtext.ui.editor.templates.XtextTemplateContextType;
import org.eclipse.xtext.ui.editor.toggleComments.ISingleLineCommentHelper;
import org.eclipse.xtext.ui.editor.toggleComments.ToggleSLCommentAction;
import org.eclipse.xtext.ui.generator.trace.ITraceForStorageProvider;
import org.eclipse.xtext.ui.generator.trace.TraceForStorageProvider;
import org.eclipse.xtext.ui.refactoring.IDependentElementsCalculator;
import org.eclipse.xtext.ui.refactoring.IReferenceUpdater;
import org.eclipse.xtext.ui.refactoring.IRenameStrategy;
import org.eclipse.xtext.ui.refactoring.impl.RenameElementProcessor;
import org.eclipse.xtext.ui.refactoring.ui.IRenameContextFactory;
import org.eclipse.xtext.ui.resource.IResourceUIServiceProvider;
import org.eclipse.xtext.ui.validation.AbstractValidatorConfigurationBlock;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.IssueSeveritiesProvider;
import org.eclipse.xtext.xbase.ui.contentassist.ParameterContextInformationProvider;
import org.eclipse.xtext.xbase.ui.editor.actions.IClipboardActionFactory;
import org.eclipse.xtext.xbase.ui.editor.actions.ImportsAwareClipboardAction;
import org.eclipse.xtext.xbase.ui.hover.XbaseDeclarativeHoverSignatureProvider;
import org.eclipse.xtext.xbase.ui.jvmmodel.refactoring.jdt.JdtRenameRefactoringParticipantProcessor;
import org.eclipse.xtext.xbase.ui.launching.JavaElementDelegateJunitLaunch;
import org.eclipse.xtext.xbase.ui.refactoring.ExpressionUtil;
import org.eclipse.xtext.xbase.ui.validation.XbaseIssueSeveritiesProvider;
import org.eclipse.xtext.xbase.ui.validation.XbaseUIValidator;

/** 
 * Use this class to register components to be used within the IDE.
 */
public class XtendUiModule extends AbstractXtendUiModule {

	public XtendUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	public void configureDebugMode(Binder binder) {
		if (Boolean.getBoolean("org.eclipse.xtext.xtend.debug")) {
			binder.bindConstant().annotatedWith(Names.named(AbstractEditStrategy.DEBUG)).to(true);
		}
		// matches ID of org.eclipse.ui.contexts extension registered in plugin.xml
		binder.bindConstant().annotatedWith(Names.named(XtextEditor.KEY_BINDING_SCOPE)).to("org.eclipse.xtend.ide.XtendEditorScope");
	}

	public void configureOverrideIndicatorSupport(Binder binder) {
		binder.bind(IXtextEditorCallback.class).annotatedWith(Names.named("OverrideIndicatorModelListener")). // $NON-NLS-1$
		to(OverrideIndicatorModelListener.class);
		binder.bind(IActionContributor.class).annotatedWith(Names.named("OverrideIndicatorRulerAction")).to( // $NON-NLS-1$
		OverrideIndicatorRulerAction.class);
	}

	@Override
	public void configureHyperlinkLabelProvider(Binder binder) {
		binder.bind(ILabelProvider.class).annotatedWith(HyperlinkLabelProvider.class).to(
			HyperLinkingLabelProvider.class);
	}

	@Override
	public Class<? extends IAnnotationHover> bindIAnnotationHover() {
		return XtendAnnotationHover.class;
	}

	@Override
	public Class<? extends IHighlightingConfiguration> bindIHighlightingConfiguration() {
		return XtendHighlightingConfiguration.class;
	}

	@Override
	public Class<? extends AbstractAntlrTokenToAttributeIdMapper> bindAbstractAntlrTokenToAttributeIdMapper() {
		return TokenToAttributeIdMapper.class;
	}

	@Override
	public Class<? extends ITokenScanner> bindITokenScanner() {
		return RichStringAwareTokenScanner.class;
	}

	@Override
	public Class<? extends ISemanticHighlightingCalculator> bindIdeSemanticHighlightingCalculator() {
		return XtendHighlightingCalculator.class;
	}

	public Class<? extends TerminalsTokenTypeToPartitionMapper> bindTerminalsTokenTypeToPartitionMapper() {
		return TokenTypeToPartitionMapper.class;
	}

	@Override
	public Class<? extends AbstractEditStrategyProvider> bindAbstractEditStrategyProvider() {
		return AutoEditStrategyProvider.class;
	}

	public void configureIShowWhitespaceCharactersActionContributor(Binder binder) {
		binder.bind(IActionContributor.class).annotatedWith(Names.named("Show Whitespace")).to(ShowWhitespaceCharactersActionContributor.class);
	}

	public Class<? extends DoubleClickStrategyProvider> bindDoubleClickStrategyProvider() {
		return XtendDoubleClickStrategyProvider.class;
	}

	@Override
	public Class<? extends IComparator> bindOutlineFilterAndSorter$IComparator() {
		return XtendOutlineNodeComparator.class;
	}

	public Class<? extends QuickOutlineFilterAndSorter> bindQuickOutlineFilterAndSorter() {
		return XtendQuickOutlineFilterAndSorter.class;
	}

	public Class<? extends IFoldingRegionProvider> bindIFoldingRegionProvider() {
		return XtendFoldingRegionProvider.class;
	}

	@Override
	public Class<? extends IContentOutlinePage> bindIContentOutlinePage() {
		return XtendOutlinePage.class;
	}

	@Override
	public Class<? extends IHyperlinkHelper> bindIHyperlinkHelper() {
		return XtendHyperlinkHelper.class;
	}

	@Override
	public Class<? extends IEObjectHoverProvider> bindIEObjectHoverProvider() {
		return XtendHoverProvider.class;
	}

	@Override
	public Class<? extends EclipseResourceFileSystemAccess2> bindEclipseResourceFileSystemAccess2() {
		return SourceRelativeFileSystemAccess.class;
	}

	@Override
	public Class<? extends EclipseSourceFolderProvider> bindEclipseSourceFolderProvider() {
		return JDTAwareSourceFolderProvider.class;
	}

	@Override
	public Class<? extends IXtextBuilderParticipant> bindIXtextBuilderParticipant() {
		return XtendParallelBuilderParticipant.class;
	}

	@Override
	public Class<? extends ISingleLineCommentHelper> bindISingleLineCommentHelper() {
		return SingleLineCommentHelper.class;
	}

	public Class<? extends XtextSourceViewer.Factory> bindSourceViewerFactory() {
		return RichStringAwareSourceViewer.Factory.class;
	}

	public Class<? extends ToggleSLCommentAction.Factory> bindToggleCommentFactory() {
		return RichStringAwareToggleCommentAction.Factory.class;
	}

	public Class<? extends IOccurrenceComputer> bindIOccurrenceComputer() {
		return OccurrenceComputer.class;
	}

	@Override
	public Class<? extends IXtextEditorCallback> bindIXtextEditorCallback() {
		return XtendNatureAddingEditorCallback.class;
	}

	public Class<? extends IResourceUIServiceProvider> bindIResourceUIServiceProvider() {
		return XtendResourceUiServiceProvider.class;
	}

	/** 
	 * @since 2.8
	 */
	@Override
	@SingletonBinding public Class<? extends IBracePairProvider> bindIBracePairProvider() {
		return XtendBracePairProvider.class;
	}

	@Override
	public void configureIPreferenceStoreInitializer(Binder binder) {
		binder.bind(IPreferenceStoreInitializer.class).annotatedWith(Names.named("RefactoringPreferences")).to(XtendRefactoringPreferences.Initializer.class);
	}

	@Override
	public Class<? extends IRenameContextFactory> bindIRenameContextFactory() {
		return XtendRenameContextFactory.class;
	}

	public Class<? extends RenameElementProcessor> bindRenameElementProcessor() {
		return XtendRenameElementProcessor.class;
	}

	@Override
	public Class<? extends IRenameStrategy> bindIRenameStrategy() {
		return XtendRenameStrategy.class;
	}

	@Override
	public Class<? extends IDependentElementsCalculator> bindIDependentElementsCalculator() {
		return XtendDependentElementsCalculator.class;
	}

	@Override
	public void configureJvmMemberRenameStrategy$Provider$Delegate(Binder binder) {
		binder.bind(IRenameStrategy.Provider.class).annotatedWith(JvmMemberRenameStrategy.Provider.Delegate.class).to(XtendRenameStrategyProvider.class);
	}

	public Class<? extends JdtRenameRefactoringParticipantProcessor> bindJdtRenameRefactoringParticipantProcessor() {
		return XtendJdtRenameParticipantProcessor.class;
	}
	
	/**
	 * @since 2.12
	 */
	@Override
	public Class<? extends IReferenceUpdater> bindIReferenceUpdater() {
		return XtendReferenceUpdater.class;
	}

	public Class<? extends XbaseDeclarativeHoverSignatureProvider> bindXbaseDeclarativeHoverSignatureProvider() {
		return XtendHoverSignatureProvider.class;
	}

	@Override
	public Class<? extends IEObjectHoverDocumentationProvider> bindIEObjectHoverDocumentationProvider() {
		return XtendHoverDocumentationProvider.class;
	}

	@Override
	public Class<? extends ITemplateProposalProvider> bindITemplateProposalProvider() {
		return TemplateProposalProvider.class;
	}

	public Class<? extends ITraceForStorageProvider> bindTraceInformation() {
		return TraceForStorageProvider.class;
	}

	public Class<? extends IEditedResourceProvider> bindIEditedResourceProvider() {
		return FormatterResourceProvider.class;
	}

	public void configureFilterSyntheticMembersContribution(Binder binder) {
		binder.bind(IOutlineContribution.class).annotatedWith(Names.named("FilterSyntheticsContribution")).to(
			ShowSyntheticMembersContribution.class);
	}

	@Override
	@SingletonBinding(eager=true) public Class<? extends XbaseUIValidator> bindXbaseUIValidator() {
		return XtendUIValidator.class;
	}

	@SingletonBinding(eager=true) public Class<? extends JavaProjectPreferencesInitializer> bindJavaProjectPreferencesInitializer() {
		return JavaProjectPreferencesInitializer.class;
	}

	@Override
	public void configureSmartCaretPreferenceInitializer(Binder binder) {
		binder.bind(IPreferenceStoreInitializer.class).annotatedWith(Names.named("smartCaretPreferenceInitializer")). // $NON-NLS-1$
		to(XtendPreferenceStoreInitializer.class);
	}

	public Class<? extends IssueSeveritiesProvider> bindIssueSeverityServiceProvider() {
		return XbaseIssueSeveritiesProvider.class;
	}

	public Class<? extends XtextSourceViewerConfiguration> bindSourceViewerConfiguration() {
		return XtendSourceViewerConfiguration.class;
	}

	public Class<? extends DocumentTokenSource> bindDocumentTokenSource() {
		return XtendDocumentTokenSource.class;
	}

	public Class<? extends AbstractValidatorConfigurationBlock> bindAbstractValidatorConfigurationBlock() {
		return XtendValidatorConfigurationBlock.class;
	}

	public Class<? extends ProcessorInstanceForJvmTypeProvider> bindProcessorInstanceForJvmTypeProvider() {
		return JdtBasedProcessorProvider.class;
	}

	@Override
	public Class<? extends IContentAssistantFactory> bindIContentAssistantFactory() {
		return XtendContentAssistFactory.class;
	}

	public Class<? extends IContextInformationProvider> bindIContextInformationProvider() {
		return ParameterContextInformationProvider.class;
	}

	public Class<? extends PrefixMatcher.CamelCase> bindCamelCasePrefixMatcher() {
		return EscapeSequenceAwarePrefixMatcher.class;
	}

	@Override
	public Class<? extends ILabelProvider> bindILabelProvider() {
		return XtendLabelProvider.class;
	}

	public Class<? extends IReferenceFinder> bindIReferenceFinder() {
		return DelegatingReferenceFinder.class;
	}

	public Class<? extends ICompletionProposalComparator> bindICompletionProposalComparator() {
		return OperatorAwareComparator.class;
	}

	public Class<? extends AbstractFileSystemSupport> bindAbstractFileSystemSupport() {
		return EclipseFileSystemSupportImpl.class;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Class<? extends org.eclipse.xtext.ui.editor.copyqualifiedname.CopyQualifiedNameService> bindCopyQualifiedNameService() {
		return XtendCopyQualifiedNameService.class;
	}

	@Override
	public Class<? extends IContentAssistParser> bindIContentAssistParser() {
		return FlexerBasedContentAssistParser.class;
	}

	@Override
	public void configureContentAssistLexerProvider(Binder binder) {
		binder.bind(InternalXtendLexer.class).toProvider(LexerProvider.create(DisabledInternalLexer.class));
		binder.bind(DisabledInternalLexer.class).toProvider(LexerProvider.create(DisabledInternalLexer.class));
	}

	@Override
	public void configureContentAssistLexer(Binder binder) {
		binder.bind(Lexer.class).annotatedWith(Names.named(LexerIdeBindings.CONTENT_ASSIST)).to(DisabledInternalLexer.class);
	}

	public Class<? extends ContentAssistContextFactory> bindContentAssistContextFactory() {
		return FlexerBasedContentAssistContextFactory.class;
	}

	@Override
	public Class<? extends IProposalConflictHelper> bindIProposalConflictHelper() {
		return FlexProposalConflictHelper.class;
	}

	public Class<? extends TemplateBodyHighlighter> bindTemplateBodyHighlighter() {
		return FlexerBasedTemplateBodyHighlighter.class;
	}

	public Class<? extends IPreferenceStoreAccess> bindPreferenceStoreAccess() {
		return XtendPreferenceStoreAccess.class;
	}

	public Class<? extends ExpressionUtil> bindExpressionUtil() {
		return XtendExpressionUtil.class;
	}

	public Class<? extends OutlineNodeFactory> bindOutlineNodeFactory() {
		return XtendOutlineNodeFactory.class;
	}

	@Override
	public Class<? extends ITypesProposalProvider> bindITypesProposalProvider() {
		return XtendImportingTypesProposalProvider.class;
	}

	public Class<? extends IOutlineTreeProvider.ModeAware> bindIOutlineTreeProvider_ModeAware() {
		return XtendOutlineModes.class;
	}

	public void configureSwitchOutlineModeContribution(Binder binder) {
		binder.bind(IOutlineContribution.class).annotatedWith(Names.named("SwitchOutlineModeContribution")).to(SwitchOutlineModeContribution.class);
	}

	public void configureSwitchQuickOutlineModeContribution(Binder binder) {
		binder.bind(IQuickOutlineContribution.class).annotatedWith(Names.named("SwitchQuickOutlineModeContribution")).to(SwitchOutlineModeContribution.class);
	}

	@SingletonBinding(eager=true) public Class<? extends IResourceChangeRegistry> bindResourceChangeRegistry() {
		return UIResourceChangeRegistry.class;
	}

	@Override
	public Class<? extends IPartialEditingContentAssistParser> bindIPartialEditingContentAssistParser() {
		return FlexerBasedPartialXtendContentAssistParser.class;
	}

	public Class<? extends ILinker> bindILinker() {
		return Linker.class;
	}

	public Class<? extends OutlineWithEditorLinker> bindOutlineWithEditorLinker() {
		return XtendOutlineWithEditorLinker.class;
	}

	public Class<? extends XtendResourceDescriptionManager> bindXtendUIResourceDescriptionManager() {
		return XtendUIResourceDescriptionManager.class;
	}

	public Class<? extends IClipboardActionFactory> bindIClipboardActionFactory() {
		return ImportsAwareClipboardAction.Factory.class;
	}

	@Override
	public Class<? extends IContextualOutputConfigurationProvider> bindIContextualOutputConfigurationProvider() {
		return EclipseOutputConfigurationProvider.class;
	}

	@Override
	public Class<? extends AbstractFileSystemAccess2> bindAbstractFileSystemAccess2() {
		return EclipseResourceFileSystemAccess2.class;
	}

	public Class<? extends IResourceValidator> bindIResourceValidator() {
		return XtendResourceValidator.class;
	}

	@Override
	public Class<? extends XtextTemplateContextType> bindXtextTemplateContextType() {
		return XtendTemplateContextType.class;
	}

	public Class<? extends SyntaxColoringPreferencePage> bindSyntaxColoringPreferencePage() {
		return XtendSyntaxColoringPreferencePage.class;
	}
	
	public Class<? extends StaticMethodImporter> bindStaticMethodImporter() {
	    return StaticMethodImporter.class;
	}
	
	public Class<? extends StaticExtensionMethodImporter> bindStaticExtensionMethodImporter() {
	    return StaticExtensionMethodImporter.class;
	}

	@Override
	public Class<? extends IShouldGenerate> bindIShouldGenerate() {
		return IShouldGenerate.Always.class;
	}

	@Override
	public void configureXtextEditorErrorTickUpdater(Binder binder) {
		binder.bind(IXtextEditorCallback.class).annotatedWith(Names.named("IXtextEditorCallBack")).to(
			XtendEditorErrorTickUpdater.class);
	}

	public Class<? extends JavaElementDelegateJunitLaunch> bindJavaElementDelegateJunitLaunch() {
		return XtendJavaElementDelegateJunitLaunch.class;
	}

}
