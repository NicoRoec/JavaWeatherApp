package src.test;

import org.junit.jupiter.api.Test;
import src.WeatherChatBot;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class TestWeatherChatBot {

    @org.junit.Test
    @Test
    public void testChatBotInitialization() {
        WeatherChatBot bot = new WeatherChatBot();
        assertNotNull(bot);
        assertEquals(JFrame.DISPOSE_ON_CLOSE, bot.getDefaultCloseOperation());
        assertEquals(400, bot.getWidth());
        assertEquals(500, bot.getHeight());
    }

    @Test
    public void testChatAreaInitialization() {
        WeatherChatBot bot = new WeatherChatBot();
        JTextArea chatArea = bot.chatArea;
        assertNotNull(chatArea);
        assertFalse(chatArea.isEditable());
    }

    @Test
    public void testSendButtonInitialization() {
        WeatherChatBot bot = new WeatherChatBot();
        JButton sendButton = bot.sendButton;
        assertNotNull(sendButton);
        assertEquals("Senden", sendButton.getText());
    }

    @Test
    public void testAskQuestion() {
        WeatherChatBot bot = new WeatherChatBot();
        bot.askQuestion();
        assertTrue(bot.chatArea.getText().contains("Bot:"));
    }
}
