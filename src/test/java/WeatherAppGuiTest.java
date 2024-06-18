import app.WeatherAppGui;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeatherAppGuiTest {

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

    @Test
    public void testGuiVisible() {
        SwingUtilities.invokeLater(() -> {
            WeatherAppGui gui = new WeatherAppGui();
            gui.setVisible(true);
            assertNotNull(gui);
        });
    }
}
