import app.WeatherImageComponent;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.MouseAdapter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Die Klasse `WeatherImageComponentTest` testet die Funktionalität der `WeatherImageComponent`-Klasse.
 * Sie stellt sicher, dass die Bildkomponente korrekt initialisiert wird und ein Icon enthält.
 *
 * @author Nico
 * @version 1
 */
public class WeatherImageComponentTest {

    /**
     * Testet die Initialisierung der Bildkomponente in `WeatherImageComponent`.
     * Überprüft, dass die Komponente und ihr Icon nicht null sind.
     */
    @Test
    public void testWeatherImageComponent() {
        WeatherImageComponent imageComponent = new WeatherImageComponent("assets/images/humidity2.png", 50, 50, new MouseAdapter() {});
        imageComponent.initialize();
        JLabel label = imageComponent.getComponent();
        assertNotNull(label);
        assertNotNull(label.getIcon());
    }
}
