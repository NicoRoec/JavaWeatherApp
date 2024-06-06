package src.test;

import org.junit.jupiter.api.Test;
import src.WeatherAppGui;

import static org.junit.jupiter.api.Assertions.*;
import javax.swing.*;

public class TestWeatherAppGui {

    @org.junit.Test
    @Test
    public void testWeatherAppGuiInitialization() {
        WeatherAppGui gui = new WeatherAppGui();
        assertNotNull(gui);
        assertEquals(JFrame.EXIT_ON_CLOSE, gui.getDefaultCloseOperation());
        assertEquals(450, gui.getWidth());
        assertEquals(650, gui.getHeight());
    }

    @Test
    public void testSearchFieldInitialization() {
        WeatherAppGui gui = new WeatherAppGui();
        JTextField searchField = gui.searchTextField;
        assertNotNull(searchField);
        assertEquals(75, searchField.getX());
        assertEquals(15, searchField.getY());
    }

    @Test
    public void testAddGuiComponents() {
        WeatherAppGui gui = new WeatherAppGui();
        assertNotNull(gui.temperatureText);
        assertNotNull(gui.weatherConditionDesc);
        assertNotNull(gui.humidityText);
        assertNotNull(gui.windspeedText);
    }
}
