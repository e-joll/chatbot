package me.chat.components;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

public class ChatItem2 extends JPanel {

    public enum ChatItemType {
        INIT, ASSISTANT, USER
    }

    private final int iconHeight = 52;

    public ChatItem2(String message, String role) {
        setLayout(new BorderLayout(10, 10));
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setOpaque(false);

        ImageIcon botIcon = new ImageIcon(Objects.requireNonNull(
            ChatItem.class.getClassLoader().getResource("icon_chatbot.png")
        ));

        // Appliquer l'icône redimensionnée
        JLabel iconLabel = new JLabel(botIcon);
        JPanel iconPanel = new JPanel(new BorderLayout());
        iconPanel.setOpaque(false);
        iconPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        iconPanel.setPreferredSize(new Dimension(iconHeight, iconHeight));
        if (role.equals(ChatItemType.INIT.toString())) {
            iconPanel.add(iconLabel, BorderLayout.NORTH);
        }

        // JTextPane pour les messages
        JTextPane textPane = new ChatMessageItem(role);
        textPane.setText(message);

        // Définir la taille préférée et minimale pour respecter l'icône
        int textHeight = textPane.getPreferredSize().height;
        if (role.equals(ChatItemType.INIT.toString())) {
            int finalHeight = Math.max(iconHeight, textHeight);
            setPreferredSize(new Dimension(0, finalHeight));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, finalHeight));
        } else {
            setPreferredSize(new Dimension(0, textHeight));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, textHeight));
        }

        JPanel textWrapper = new JPanel(new BorderLayout());
        textWrapper.add(textPane, BorderLayout.CENTER);
        textWrapper.setOpaque(false);

        JPanel textContainer = new JPanel();
        textContainer.setLayout(new BorderLayout());
//        textContainer.setBorder(new LineBorder(Color.red));
        if (role.equals(ChatItemType.INIT.toString())) {
            textContainer.add(textWrapper, BorderLayout.SOUTH);
        } else {
            textContainer.add(textWrapper, BorderLayout.CENTER);
        }
        textContainer.setOpaque(false);

        add(iconPanel, BorderLayout.WEST);
        add(textContainer, BorderLayout.CENTER);

//        setBorder(new LineBorder(Color.black));

        // Ajout d'un listener pour recalculer la hauteur
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int textHeight = textPane.getPreferredSize().height;
                if (!role.equals(ChatItemType.INIT.toString())) {
                    setPreferredSize(new Dimension(0, textHeight));
                    setMaximumSize(new Dimension(Integer.MAX_VALUE, textHeight));
                } else {
                    int finalHeight = Math.max(iconHeight, textHeight);
                    setPreferredSize(new Dimension(0, finalHeight));
                    setMaximumSize(new Dimension(Integer.MAX_VALUE, finalHeight));
                }
            }
        });
    }
}
