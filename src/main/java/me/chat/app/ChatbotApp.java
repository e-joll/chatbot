package me.chat.app;

import me.chat.components.ChatItem;
import me.chat.components.ChatItem2;
import me.chat.components.ChatPanel;
import me.chat.components.MessageInput;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ChatbotApp {

    public static void main(String[] args) {
        // Création de la fenêtre
        JFrame frame = new JFrame("Chatbot");
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(
            ChatbotApp.class.getClassLoader().getResource("icon_chatbot.png")
        ));
        frame.setIconImage(icon.getImage());


        // Utilisation d'un panel principal avec BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // JPanel qui contient les messages du chat
        ChatPanel chatPanel = new ChatPanel();

        // Ajout d'exemples d'éléments
        ChatItem2 message1 = new ChatItem2("Bonjour, comment vas-tu ?", "INIT");
        ChatItem2 message2 = new ChatItem2("Ceci est un message long qui peut s'afficher sur plusieurs lignes si besoin. Ceci est un message long qui peut s'afficher sur plusieurs lignes si besoin.", "USER");
        ChatItem2 message3 = new ChatItem2("Bonjour, comment vas-tu ?", "ASSISTANT");
        ChatItem2 message4 = new ChatItem2("Ceci est un message long qui peut s'afficher sur plusieurs lignes si besoin. Ceci est un message long qui peut s'afficher sur plusieurs lignes si besoin.", "USER");
        ChatItem2 message5 = new ChatItem2("Praesent volutpat eleifend ligula. Cras pretium mollis leo, in cursus justo imperdiet vel. Proin lacinia dapibus blandit. Morbi vehicula pulvinar ligula. Donec odio arcu, rhoncus a purus a, tincidunt vehicula lacus. Nulla facilisi. Etiam et cursus tortor. Nam ullamcorper ornare elit, tempus cursus justo molestie et. Mauris at accumsan risus, at faucibus massa. Ut sit amet diam felis. Aliquam eu massa eget tortor vehicula mattis.\n" +
                "\n" +
                "Morbi at tellus sit amet mauris aliquet gravida. Nunc maximus sit amet tortor in hendrerit. Curabitur sit amet orci at ligula accumsan faucibus cursus sit amet nisi. Nam feugiat elementum ante, ac laoreet nisi vehicula ut. Nunc accumsan varius maximus. Aenean imperdiet libero eu nisi congue blandit. Nam non suscipit ex. In finibus id est vel ornare. Ut lobortis hendrerit pulvinar. Quisque aliquet risus id laoreet pellentesque. Praesent euismod nulla ut mi dignissim, at egestas metus ornare. Aenean lacinia gravida ipsum, nec fermentum diam sollicitudin eget. Duis commodo dui ut pretium ultrices. Quisque quis ligula molestie enim tincidunt blandit. Aliquam interdum, urna et sollicitudin porta, mauris felis placerat mi, in tincidunt risus nisl eget nulla.", "ASSISTANT");
        chatPanel.addMessage(message1);
        chatPanel.addMessage(message2);
        chatPanel.addMessage(message3);
        chatPanel.addMessage(message4);
        chatPanel.addMessage(message5);

        mainPanel.add(chatPanel, BorderLayout.CENTER);

        // Création du MessageInput en bas
        MessageInput messageInput = new MessageInput(chatPanel);
        mainPanel.add(messageInput, BorderLayout.SOUTH);

        // Ajout du mainPanel à la fenêtre
        frame.add(mainPanel);


        frame.setSize(350, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}