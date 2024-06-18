import app.WeatherChatBot;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeatherChatBotTest {

    @Test
    public void testChatBotComponents() {
        WeatherChatBot chatBot = new WeatherChatBot();
        assertNotNull(chatBot.chatArea);
        assertNotNull(chatBot.userInputField);
        assertNotNull(chatBot.sendButton);
    }

    @Test
    public void testChatBotVisible() {
        SwingUtilities.invokeLater(() -> {
            WeatherChatBot chatBot = new WeatherChatBot();
            chatBot.setVisible(true);
            assertNotNull(chatBot);
        });
    }
}
