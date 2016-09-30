/**
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package observables;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.eclipse.xtend.lib.macro.AbstractClassProcessor;
import org.eclipse.xtend.lib.macro.TransformationContext;
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration;
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration;
import org.eclipse.xtend.lib.macro.declaration.TypeReference;
import org.eclipse.xtend2.lib.StringConcatenationClient;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class ObservableCompilationParticipant extends AbstractClassProcessor {
  @Override
  public void doTransform(final MutableClassDeclaration clazz, @Extension final TransformationContext context) {
    Iterable<? extends MutableFieldDeclaration> _declaredFields = clazz.getDeclaredFields();
    for (final MutableFieldDeclaration f : _declaredFields) {
      {
        final String fieldName = f.getSimpleName();
        final TypeReference fieldType = f.getType();
        String _firstUpper = StringExtensions.toFirstUpper(fieldName);
        String _plus = ("get" + _firstUpper);
        final Procedure1<MutableMethodDeclaration> _function = (MutableMethodDeclaration it) -> {
          it.setReturnType(fieldType);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append("return this.");
              _builder.append(fieldName, "");
              _builder.append(";");
            }
          };
          it.setBody(_client);
          context.setPrimarySourceElement(it, f);
        };
        clazz.addMethod(_plus, _function);
        String _firstUpper_1 = StringExtensions.toFirstUpper(fieldName);
        String _plus_1 = ("set" + _firstUpper_1);
        final Procedure1<MutableMethodDeclaration> _function_1 = (MutableMethodDeclaration it) -> {
          it.addParameter(fieldName, fieldType);
          StringConcatenationClient _client = new StringConcatenationClient() {
            @Override
            protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
              _builder.append(fieldType, "");
              _builder.append(" _oldValue = this.");
              _builder.append(fieldName, "");
              _builder.append(";");
              _builder.newLineIfNotEmpty();
              _builder.append("this.");
              _builder.append(fieldName, "");
              _builder.append(" = ");
              _builder.append(fieldName, "");
              _builder.append(";");
              _builder.newLineIfNotEmpty();
              _builder.append("_propertyChangeSupport.firePropertyChange(\"");
              _builder.append(fieldName, "");
              _builder.append("\", _oldValue, ");
              _builder.append(fieldName, "");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
            }
          };
          it.setBody(_client);
          context.setPrimarySourceElement(it, f);
        };
        clazz.addMethod(_plus_1, _function_1);
        f.markAsRead();
      }
    }
    final TypeReference changeSupportType = context.newTypeReference(PropertyChangeSupport.class);
    final Procedure1<MutableFieldDeclaration> _function = (MutableFieldDeclaration it) -> {
      it.setType(changeSupportType);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("new ");
          _builder.append(changeSupportType, "");
          _builder.append("(this)");
        }
      };
      it.setInitializer(_client);
      context.setPrimarySourceElement(it, clazz);
    };
    clazz.addField("_propertyChangeSupport", _function);
    final TypeReference propertyChangeListener = context.newTypeReference(PropertyChangeListener.class);
    final Procedure1<MutableMethodDeclaration> _function_1 = (MutableMethodDeclaration it) -> {
      it.addParameter("listener", propertyChangeListener);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("this._propertyChangeSupport.addPropertyChangeListener(listener);");
        }
      };
      it.setBody(_client);
      context.setPrimarySourceElement(it, clazz);
    };
    clazz.addMethod("addPropertyChangeListener", _function_1);
    final Procedure1<MutableMethodDeclaration> _function_2 = (MutableMethodDeclaration it) -> {
      it.addParameter("listener", propertyChangeListener);
      StringConcatenationClient _client = new StringConcatenationClient() {
        @Override
        protected void appendTo(StringConcatenationClient.TargetStringConcatenation _builder) {
          _builder.append("this._propertyChangeSupport.removePropertyChangeListener(listener);");
        }
      };
      it.setBody(_client);
      context.setPrimarySourceElement(it, clazz);
    };
    clazz.addMethod("removePropertyChangeListener", _function_2);
  }
}
