package me.chat.components;

import me.chat.utils.AutoWrapText;

import javax.swing.*;
import java.awt.*;

public class MessageInput extends JTextPane {

    private int arcWidth = 20;
    private int arcHeight = 20;
    private int padding = 15;
    private int p = 5;
    private final Color backgroundColor = Color.LIGHT_GRAY;

    public MessageInput() {
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        setEditorKit(new AutoWrapText());
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setPreferredSize(new Dimension(getPreferredSize().width, getPreferredSize().height));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(backgroundColor);
        g2.fillRoundRect(p, p, getWidth()-p*2, getHeight()-p*2, arcWidth, arcHeight);

        super.paintComponent(g);
        g2.dispose();
    }

    //TODO: ajouter un message dans le ChatPanel
}