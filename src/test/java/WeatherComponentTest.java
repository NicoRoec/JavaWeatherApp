import app.WeatherComponent;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Die Klasse `WeatherComponentTest` testet die Funktionalität der `WeatherComponent`-Klasse.
 * Sie stellt sicher, dass die abstrakte Methode `initialize` korrekt implementiert und die GUI-Komponente initialisiert wird.
 *
 * @author Nico
 * @version 1
 */
public class WeatherComponentTest {

    /**
     * Testet die Initialisierung der GUI-Komponente in `WeatherComponent`.
     * Überprüft, dass die Komponente nach der Initialisierung nicht null ist.
     */
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
