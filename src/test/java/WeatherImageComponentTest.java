import app.WeatherImageComponent;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.MouseAdapter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeatherImageComponentTest {

    @Test
    public void testWeatherImageComponent() {
        WeatherImageComponent imageComponent = new WeatherImageComponent("assets/images/humidity2.png", 50, 50, new MouseAdapter() {});
        imageComponent.initialize();
        JLabel label = imageComponent.getComponent();
        assertNotNull(label);
        assertNotNull(label.getIcon());
    }
}
