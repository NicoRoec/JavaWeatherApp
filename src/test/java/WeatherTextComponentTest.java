import app.WeatherTextComponent;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Die Klasse `WeatherTextComponentTest` testet die Funktionalität der `WeatherTextComponent`-Klasse.
 * Sie stellt sicher, dass die Textkomponente korrekt initialisiert wird und Text enthält.
 *
 * @author Nico
 * @version 1
 */
public class WeatherTextComponentTest {

    /**
     * Testet die Initialisierung der Textkomponente in `WeatherTextComponent`.
     * Überprüft, dass die Komponente und ihr Text nicht null sind.
     */
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
