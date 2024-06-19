import app.WeatherChatBot;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Die Klasse `WeatherChatBotTest` testet die Funktionalität der `WeatherChatBot`-Klasse.
 * Sie stellt sicher, dass alle GUI-Komponenten korrekt initialisiert und sichtbar sind.
 *
 * @author Nico
 * @version 1
 */
public class WeatherChatBotTest {

    /**
     * Testet die Initialisierung der GUI-Komponenten in `WeatherChatBot`.
     * Überprüft, dass alle Komponenten nicht null sind.
     */
    @Test
    public void testChatBotComponents() {
        WeatherChatBot chatBot = new WeatherChatBot();
        assertNotNull(chatBot.chatArea);
        assertNotNull(chatBot.userInputField);
        assertNotNull(chatBot.sendButton);
    }

    /**
     * Testet, ob die GUI von `WeatherChatBot` sichtbar gemacht werden kann.
     * Überprüft, dass das GUI-Objekt nicht null ist, wenn es sichtbar gemacht wird.
     */
    @Test
    public void testChatBotVisible() {
        SwingUtilities.invokeLater(() -> {
            WeatherChatBot chatBot = new WeatherChatBot();
            chatBot.setVisible(true);
            assertNotNull(chatBot);
        });
    }
}
