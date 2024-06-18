import app.WeatherComponent;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeatherComponentTest {

    @Test
    public void testWeatherComponentInitialization() {
        WeatherComponent component = new WeatherComponent() {
            @Override
            public void initialize() {
                component = new JLabel("Test");
            }
        };
        component.initialize();
        assertNotNull(component.getComponent());
    }
}
