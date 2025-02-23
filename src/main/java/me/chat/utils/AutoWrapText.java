/*
 * Source code inspired by the GitHub repository of DJ-Raven:
 * Repository: java-swing-ui-chat-messenger
 * URL: https://github.com/DJ-Raven/java-swing-ui-chat-messenger
 * Licence : MIT License
 *
 * Copyright (c) 2022 Raven Laing
 */

package me.chat.utils;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class AutoWrapText extends StyledEditorKit {

    @Override
    public ViewFactory getViewFactory() {
        return new WarpColumnFactory();
    }

    private static class WarpColumnFactory implements ViewFactory {

        @Override
        public View create(Element elmnt) {
            String kind = elmnt.getName();
            if (kind != null) {
                switch (kind) {
                    case AbstractDocument.ContentElementName -> {
                        return new WarpLabelView(elmnt);
                    }
                    case AbstractDocument.ParagraphElementName -> {
                        return new ParagraphView(elmnt);
                    }
                    case AbstractDocument.SectionElementName -> {
                        return new BoxView(elmnt, View.Y_AXIS);
                    }
                    case StyleConstants.ComponentElementName -> {
                        return new ComponentView(elmnt);
                    }
                    case StyleConstants.IconElementName -> {
                        return new IconView(elmnt);
                    }
                }
            }
            return new LabelView(elmnt);
        }
    }

    private static class WarpLabelView extends LabelView {

        public WarpLabelView(Element elem) {
            super(elem);
        }

        @Override
        public float getMinimumSpan(int axis) {
            return switch (axis) {
                case View.X_AXIS -> 0;
                case View.Y_AXIS -> super.getMinimumSpan(axis);
                default -> throw new IllegalArgumentException("Invalid Axis:" + axis);
            };
        }
    }
}