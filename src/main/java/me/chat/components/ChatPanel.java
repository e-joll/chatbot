package me.chat.components;

import me.chat.components.ui.ModernScrollBarUI;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    private final JPanel messagesPanel;
    private final JScrollPane scrollPane;

    public ChatPanel() {
        setLayout(new BorderLayout());

        // Panel contenant les messages
        messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.setOpaque(false);

        // Ajout du panel dans un JScrollPane
        scrollPane = new JScrollPane(messagesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scrollPane.getVerticalScrollBar().setUnitIncrement(ModernScrollBarUI.SCROLL_SPEED);
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());

        add(scrollPane, BorderLayout.CENTER);
    }

    public void addMessage(ChatItem2 message) {
        messagesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        message.setAlignmentX(Component.LEFT_ALIGNMENT);
        messagesPanel.add(message);
        messagesPanel.revalidate();
        messagesPanel.repaint();

        scrollToBottom();
    }

    private void scrollToBottom() {
        SwingUtilities.invokeLater(() -> scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum()));
    }
}