package me.chat.components;

import me.chat.components.ui.RoundedBorder;
import me.chat.components.ui.RoundedBorderExceptTopLeft;
import me.chat.utils.AutoWrapText;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class ChatMessageItem extends JTextPane {

    private final String role;
    private final int radius = 15;

    public ChatMessageItem(String role) {
        this.role = role;

        setEditorKit(new AutoWrapText());
        setEditable(false);
        setOpaque(false);

        int padding = 5;
        int thickness = 2;
        if (role.equals(ChatItem2.ChatItemType.INIT.toString())) {
            setBorder(new CompoundBorder(
                new RoundedBorderExceptTopLeft(radius, thickness),
                BorderFactory.createEmptyBorder(padding, padding, padding, padding)
            ));
        } else if (role.equals(ChatItem2.ChatItemType.ASSISTANT.toString())) {
            setBorder(new CompoundBorder(
                new RoundedBorder(radius, thickness),
                BorderFactory.createEmptyBorder(padding, padding, padding, padding)
            ));
        } else if (role.equals(ChatItem2.ChatItemType.USER.toString())) {
            setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.LIGHT_GRAY);

        if (role.equals(ChatItem2.ChatItemType.USER.toString())) {
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        }

        g2.dispose();
        super.paintComponent(g);
    }
}