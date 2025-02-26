package me.chat.components;

import me.chat.utils.AutoWrapText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Area;
import java.util.Objects;

public class ChatItem2 extends JPanel {

    public enum ChatItemType {
        INIT, ASSISTANT, USER
    }

    public ChatItem2(String message, String role) {
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setLayout(new BorderLayout(10, 10));
        setOpaque(false);

        ImageIcon botIcon = new ImageIcon(Objects.requireNonNull(
            ChatItem.class.getClassLoader().getResource("icon_chatbot.png")
        ));

        // TODO: revoir redimensionnement image
        // Taille souhaitée pour l'icône
        int iconWidth = 50;  // Largeur en pixels
        int iconHeight = 200; // Hauteur en pixels

        // Appliquer l'icône redimensionnée
        JLabel iconLabel = new JLabel(botIcon);
        JPanel iconPanel = new JPanel(new BorderLayout());
        iconPanel.setOpaque(false);
        iconPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        iconPanel.setPreferredSize(new Dimension(iconWidth, iconHeight));
        if (role.equals(ChatItemType.INIT.toString())) {
            iconPanel.add(iconLabel, BorderLayout.NORTH);
        }

        add(iconPanel, BorderLayout.WEST);

        // JTextPane pour les messages
        JTextPane textPane = new ChatMessageItem(role);
        textPane.setText(message);
        add(textPane, BorderLayout.CENTER);

        // Définir la taille préférée et minimale pour respecter l'icône
        int minHeight = iconHeight + 20; // +20 pour le padding
        int textHeight = textPane.getPreferredSize().height + 20;
        if (role.equals(ChatItemType.INIT.toString())) {
            int finalHeight = Math.max(minHeight, textHeight);
            setPreferredSize(new Dimension(0, finalHeight));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, finalHeight));
        } else {
            setPreferredSize(new Dimension(0, textHeight));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, textHeight));
        }

        // Ajout d'un listener pour recalculer la hauteur
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int textHeight = textPane.getPreferredSize().height;
                setPreferredSize(new Dimension(0, textHeight));
                setMaximumSize(new Dimension(Integer.MAX_VALUE, textHeight));
            }
        });
    }
}
