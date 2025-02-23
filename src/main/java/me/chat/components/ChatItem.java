package me.chat.components;

import me.chat.app.ChatbotApp;
import me.chat.utils.AutoWrapText;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

public class ChatItem extends JPanel {

    public ChatItem(String message, String role) {
        setAlignmentX(Component.LEFT_ALIGNMENT);
        setLayout(new BorderLayout(10, 10));

        // Définir la couleur de fond en fonction du rôle
        if (role.equals("assistant")) {
            setBackground(Color.decode("#F7F7F7"));
        }

        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(
            ChatItem.class.getClassLoader().getResource(
                role.equals("assistant") ? "icon_chatbot.png" : "icon_fireman.png"
            )
        ));

        // TODO: revoir redimensionnement image
        // Taille souhaitée pour l'icône
        int iconWidth = 50;  // Largeur en pixels
        int iconHeight = 50; // Hauteur en pixels

        // Redimensionner l'image
        Image scaledImage = originalIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // Appliquer l'icône redimensionnée
        JLabel iconLabel = new JLabel(resizedIcon);
        iconLabel.setMaximumSize(new Dimension(iconWidth, iconHeight)); // Optionnel, mais peut aider

        JPanel iconPanel = new JPanel(new BorderLayout());
        iconPanel.setOpaque(false);
        iconPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        iconPanel.add(iconLabel, BorderLayout.NORTH);

        add(iconPanel, BorderLayout.WEST);

//        System.out.println("Image2 width: " + resizedIcon.getIconWidth());
//        System.out.println("Image2 height: " + resizedIcon.getIconHeight());

        // JTextPane pour les messages
        JTextPane textPane = new JTextPane();
        textPane.setEditorKit(new AutoWrapText());
        textPane.setText(message);
        textPane.setEditable(false);
        textPane.setOpaque(false);
        textPane.setBorder(null);
        add(textPane, BorderLayout.CENTER);

        // Définir la taille préférée et minimale pour respecter l'icône
        int minHeight = iconHeight + 20; // +20 pour le padding
        int textHeight = textPane.getPreferredSize().height + 20;
        int finalHeight = Math.max(minHeight, textHeight);
//        System.out.println("textHeight: " + textHeight);

        setPreferredSize(new Dimension(0, finalHeight)); // Largeur flexible
        setMaximumSize(new Dimension(Integer.MAX_VALUE, finalHeight)); // Permet l'extension
//        System.out.println("finalHeight: " + finalHeight);

        // 📌 **Ajout d'un listener pour recalculer la hauteur**
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int minHeight = iconHeight + 20; // +20 pour le padding
                int textHeight = textPane.getPreferredSize().height;
                int finalHeight = Math.max(minHeight, textHeight);
//                System.out.println("preferredHeight textPane: " + textPane.getPreferredSize().getHeight());
//                System.out.println("Height textPane: " + textPane.getHeight());
//                System.out.println("finalHeight textPane: " + finalHeight);
                setPreferredSize(new Dimension(0, finalHeight));
                setMaximumSize(new Dimension(Integer.MAX_VALUE, finalHeight));
            }
        });

        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.dispose();
        super.paintComponent(g);
    }
}