package org.eclipse.xtend.ide.labeling;

import com.google.inject.Inject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmEnumerationLiteral;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.ui.labeling.XbaseLabelProvider;
import org.eclipse.xtext.xbase.validation.UIStrings;

/**
 * Provides labels for Xtend elements.
 * 
 * @author Dennis Huebner
 */
@SuppressWarnings("all")
public class XtendJvmLabelProvider extends XbaseLabelProvider {
  @Inject
  private UIStrings uiStrings;
  
  @Inject
  public XtendJvmLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  @Override
  protected String text(final JvmGenericType element) {
    String _xblockexpression = null;
    {
      final boolean local = element.isLocal();
      if (local) {
        final JvmTypeReference supertype = IterableExtensions.<JvmTypeReference>last(element.getSuperTypes());
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("new ");
        String _simpleName = supertype.getSimpleName();
        _builder.append(_simpleName);
        _builder.append("() {...}");
        return _builder.toString();
      }
      String _simpleName_1 = element.getSimpleName();
      String _xifexpression = null;
      boolean _isEmpty = element.getTypeParameters().isEmpty();
      if (_isEmpty) {
        _xifexpression = "";
      } else {
        _xifexpression = this.uiStrings.typeParameters(element.getTypeParameters());
      }
      _xblockexpression = (_simpleName_1 + _xifexpression);
    }
    return _xblockexpression;
  }
  
  @Override
  protected Object text(final JvmField element) {
    String _simpleName = element.getSimpleName();
    StyledString _styledString = new StyledString(_simpleName);
    String _simpleName_1 = element.getType().getSimpleName();
    String _plus = (" : " + _simpleName_1);
    StyledString _styledString_1 = new StyledString(_plus, StyledString.DECORATIONS_STYLER);
    return _styledString.append(_styledString_1);
  }
  
  protected String text(final JvmEnumerationLiteral element) {
    return element.getSimpleName();
  }
}
