package src.test;

import org.junit.jupiter.api.Test;
import src.WeatherChatBot;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

/**
 * Die Klasse `TestWeatherChatBot` enthält Unit-Tests für die Klasse `WeatherChatBot`.
 *
 * @author Nico Röcker
 * @version 1.0
 *
 */
public class TestWeatherChatBot {

    /**
     * Testet die Initialisierung der `WeatherChatBot`-Klasse.
     * Überprüft, ob das Chatbot-Fenster korrekt initialisiert wurde.
     */
    @org.junit.Test
    @Test
    public void testChatBotInitialization() {
        WeatherChatBot bot = new WeatherChatBot();
        assertNotNull(bot);
        assertEquals(JFrame.DISPOSE_ON_CLOSE, bot.getDefaultCloseOperation());
        assertEquals(400, bot.getWidth());
        assertEquals(500, bot.getHeight());
    }

    /**
     * Testet die Initialisierung des Chatbereichs.
     * Überprüft, ob der Chatbereich korrekt initialisiert und nicht editierbar ist.
     */
    @Test
    public void testChatAreaInitialization() {
        WeatherChatBot bot = new WeatherChatBot();
        JTextArea chatArea = bot.chatArea;
        assertNotNull(chatArea);
        assertFalse(chatArea.isEditable());
    }

    /**
     * Testet die Initialisierung des Senden-Buttons.
     * Überprüft, ob der Senden-Button korrekt initialisiert und beschriftet ist.
     */
    @Test
    public void testSendButtonInitialization() {
        WeatherChatBot bot = new WeatherChatBot();
        JButton sendButton = bot.sendButton;
        assertNotNull(sendButton);
        assertEquals("Senden", sendButton.getText());
    }

    /**
     * Testet die `askQuestion`-Methode.
     * Überprüft, ob die Methode eine Frage im Chatbereich anzeigt.
     */
    @Test
    public void testAskQuestion() {
        WeatherChatBot bot = new WeatherChatBot();
        bot.askQuestion();
        assertTrue(bot.chatArea.getText().contains("Bot:"));
    }
}
