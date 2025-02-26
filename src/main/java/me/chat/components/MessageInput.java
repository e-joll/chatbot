package me.chat.components;

import me.chat.utils.AutoWrapText;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;



public class MessageInput extends JPanel {

//    private final JTextArea messageInput;
    private final JTextPane messageInput;
    private final JButton sendButton;
    private final ChatPanel chatPanel;

    private int arcWidth = 20;
    private int arcHeight = 20;
    private int padding = 15;
    private int p = 5;
    private final Color backgroundColor = Color.LIGHT_GRAY;

    public MessageInput(ChatPanel chatPanel) {
        this.chatPanel = chatPanel;

        setLayout(new BorderLayout(0, 10));
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        //TODO: ajouter hauteur max

        messageInput = new JTextPane();
        messageInput.setOpaque(false);
        messageInput.setBorder(new EmptyBorder(5, 1, 5, 1));
        messageInput.setBackground(new Color(0, 0, 0, 0));
        messageInput.setSelectionColor(new Color(0, 0, 0, 0));
        messageInput.setSelectedTextColor(Color.WHITE);
        messageInput.setEditable(Boolean.TRUE);
        messageInput.setEditorKit(new AutoWrapText());

        ImageIcon originalIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon_send.png")));
        ImageIcon hoverIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("icon_send_red.png")));
        sendButton = new JButton(originalIcon);
        sendButton.setFocusPainted(false);
        sendButton.setBorderPainted(false);
        sendButton.setContentAreaFilled(false);
        sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sendButton.setPreferredSize(new Dimension(20, 20));
        sendButton.addActionListener(e -> sendMessage());
        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                sendButton.setIcon(hoverIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                sendButton.setIcon(originalIcon);
            }
        });

        add(messageInput, BorderLayout.CENTER);
        add(sendButton, BorderLayout.EAST);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, p, getWidth(), getHeight()-p*2, arcWidth, arcHeight);

        super.paintComponent(g);
        g2.dispose();
    }

    private void sendMessage() {
        String text = messageInput.getText().trim();
        if (!text.isEmpty()) {
            ChatItem2 message = new ChatItem2(text, "user");
            chatPanel.addMessage(message);
            messageInput.setText("");
        }
    }
}