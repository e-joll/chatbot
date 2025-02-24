package me.chat.components;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;



public class MessageInput extends JPanel {

    private final JTextArea messageInput;
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

        messageInput = new JTextArea();
        messageInput.setOpaque(true);
        messageInput.setBackground(backgroundColor);
        messageInput.setWrapStyleWord(true);  // Assurer le retour à la ligne
        messageInput.setLineWrap(true);       // Retour à la ligne automatique
        messageInput.setAlignmentX(Component.LEFT_ALIGNMENT);
//        messageInput.setMinimumSize(new Dimension(Integer.MAX_VALUE, messageInput.getFontMetrics(messageInput.getFont()).getHeight() + 4*p));
//        messageInput.setPreferredSize(new Dimension(getPreferredSize().width, 16));

        messageInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Quand du texte est ajouté, redimensionne si nécessaire
                adjustHeight();
                System.out.println("COUCOU  messageInput insertUpdate");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Quand du texte est retiré, redimensionne si nécessaire
                adjustHeight();
                System.out.println("COUCOU  messageInput removeUpdate");
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Utilisé pour les changements de style, mais souvent non nécessaire pour la taille
                adjustHeight();
                System.out.println("COUCOU  messageInput changedUpdate");
            }
        });

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
        //TODO: revoir hauteur (ouverture app)
        g2.fillRoundRect(0, p, getWidth(), getHeight()-p*2, arcWidth, arcHeight);

        super.paintComponent(g);
        g2.dispose();
    }

    private void sendMessage() {
        String text = messageInput.getText().trim();
        if (!text.isEmpty()) {
            ChatItem message = new ChatItem(text, "user");
            chatPanel.addMessage(message);
            messageInput.setText("");
        }
    }

    private void adjustHeight() {
        System.out.println("messageInput.getPreferredSize().width: " + messageInput.getPreferredSize().width);
        System.out.println("messageInput.getPreferredSize().height: " + messageInput.getPreferredSize().height);
        System.out.println("messageInput.getSize().height: " + messageInput.getSize().height);
        System.out.println("messageInput.getMaximumSize().height: " + messageInput.getMaximumSize().height);
        System.out.println("getPreferredSize().height: " + getPreferredSize().height);
        System.out.println("getMaximumSize().height: " + getMaximumSize().height);
        int lines = messageInput.getLineCount();
        int lineHeight = messageInput.getFontMetrics(messageInput.getFont()).getHeight();

        // Calculer la hauteur totale basée sur les lignes de texte
        int height = lines * lineHeight;

        // Mettre à jour la taille du conteneur
        setPreferredSize(new Dimension(getPreferredSize().width, height + 2*padding));

        revalidate();
        repaint();
    }
}