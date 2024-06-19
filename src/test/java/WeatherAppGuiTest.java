import app.WeatherAppGui;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Die Klasse `WeatherAppGuiTest` testet die Funktionalität der `WeatherAppGui`-Klasse.
 * Sie stellt sicher, dass alle GUI-Komponenten korrekt initialisiert und sichtbar sind.
 *
 * @author Nico
 * @version 1
 */
public class WeatherAppGuiTest {

    /**
     * Testet die Initialisierung der GUI-Komponenten in `WeatherAppGui`.
     * Überprüft, dass alle Komponenten nicht null sind.
     */
    @Test
    public void testGuiComponents() {
        WeatherAppGui gui = new WeatherAppGui();
        assertNotNull(gui.searchTextField);
        assertNotNull(gui.weatherConditionImage);
        assertNotNull(gui.temperatureText);
        assertNotNull(gui.weatherConditionDesc);
        assertNotNull(gui.humidityText);
        assertNotNull(gui.windspeedText);
        assertNotNull(gui.searchButton);
    }

    /**
     * Testet, ob die GUI von `WeatherAppGui` sichtbar gemacht werden kann.
     * Überprüft, dass das GUI-Objekt nicht null ist, wenn es sichtbar gemacht wird.
     */
    @Test
    public void testGuiVisible() {
        SwingUtilities.invokeLater(() -> {
            WeatherAppGui gui = new WeatherAppGui();
            gui.setVisible(true);
            assertNotNull(gui);
        });
    }
}
