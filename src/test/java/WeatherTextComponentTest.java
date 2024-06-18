import app.WeatherTextComponent;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeatherTextComponentTest {

    @Test
    public void testWeatherTextComponent() {
        Font bebasFont = new Font("SansSerif", Font.PLAIN, 16);
        WeatherTextComponent textComponent = new WeatherTextComponent("Test", 0, 0, 100, 30, bebasFont);
        textComponent.initialize();
        JLabel label = textComponent.getComponent();
        assertNotNull(label);
        assertNotNull(label.getText());
    }
}
