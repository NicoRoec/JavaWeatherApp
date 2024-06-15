import app.WeatherAppGui;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Die Klasse `TestWeatherAppGui` enthält Unit-Tests für die Klasse `app.WeatherAppGui`.
 *
 * @author Nico Röcker
 * @version 1.0
 *
 */
public class TestWeatherAppGui {

    /**
     * Testet die Initialisierung der `app.WeatherAppGui`-Klasse.
     * Überprüft, ob das GUI-Fenster korrekt initialisiert wurde.
     */
    @Test
    public void testWeatherAppGuiInitialization() {
        WeatherAppGui gui = new WeatherAppGui();
        assertNotNull(gui);
        assertEquals(JFrame.EXIT_ON_CLOSE, gui.getDefaultCloseOperation());
        assertEquals(450, gui.getWidth());
        assertEquals(650, gui.getHeight());
    }

    /**
     * Testet die Initialisierung des Suchfelds.
     * Überprüft, ob das Suchfeld korrekt initialisiert und positioniert wurde.
     */
    @Test
    public void testSearchFieldInitialization() {
        WeatherAppGui gui = new WeatherAppGui();
        JTextField searchField = gui.searchTextField;
        assertNotNull(searchField);
        assertEquals(75, searchField.getX());
        assertEquals(15, searchField.getY());
    }

    /**
     * Testet das Hinzufügen der GUI-Komponenten.
     * Überprüft, ob die wesentlichen GUI-Komponenten korrekt hinzugefügt wurden.
     */
    @Test
    public void testAddGuiComponents() {
        WeatherAppGui gui = new WeatherAppGui();
        assertNotNull(gui.temperatureText);
        assertNotNull(gui.weatherConditionDesc);
        assertNotNull(gui.humidityText);
        assertNotNull(gui.windspeedText);
    }
}
