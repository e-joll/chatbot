package me.chat.components;

import me.chat.utils.AutoWrapText;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class ChatMessageItem extends JTextPane {

    private final String role;

    public ChatMessageItem(String role) {
        this.role = role;

        setEditorKit(new AutoWrapText());
        setEditable(false);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.LIGHT_GRAY);

        // Revoir les bordures
        if (role.equals(ChatItem2.ChatItemType.ASSISTANT.toString())) {
            Area area = new Area(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
            area.subtract(new Area(new RoundRectangle2D.Double(3, 3, getWidth() - 3*2, getHeight() - 3*2, 20, 20)));
            g2.fill(area);
        } else if (role.equals(ChatItem2.ChatItemType.USER.toString())) {
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        } else if (role.equals(ChatItem2.ChatItemType.INIT.toString())) {
            Area area = new Area(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
            area.add(new Area(new Rectangle2D.Double(0, 0, 20, 20)));
            g2.draw(area);
        }

        g2.dispose();
        super.paintComponent(g);
    }
}
