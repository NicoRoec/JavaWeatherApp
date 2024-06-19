import app.WeatherApp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Die Klasse `WeatherAppTest` testet die Funktionalität der `WeatherApp`-Klasse.
 * Sie stellt sicher, dass die Methoden zum Abrufen von Wetter- und Standortdaten korrekt funktionieren.
 *
 * @author Nico
 * @version 1
 */
public class WeatherAppTest {

    /**
     * Testet die Methode `getWeatherData` der `WeatherApp`-Klasse.
     * Überprüft, dass die Methode Wetterdaten für einen gegebenen Ort zurückgibt und dass diese Daten die erwarteten Schlüssel enthalten.
     */
    @Test
    public void testGetWeatherData() {
        JSONObject weatherData = WeatherApp.getWeatherData("Berlin");
        assertNotNull(weatherData);
        assertTrue(weatherData.containsKey("temperature"));
        assertTrue(weatherData.containsKey("humidity"));
        assertTrue(weatherData.containsKey("windspeed"));
        assertTrue(weatherData.containsKey("weather_condition"));
    }

    /**
     * Testet die Methode `getLocationData` der `WeatherApp`-Klasse.
     * Überprüft, dass die Methode Standortdaten für einen gegebenen Ortsnamen zurückgibt und dass das Ergebnis nicht leer ist.
     */
    @Test
    public void testGetLocationData() {
        JSONArray locationData = WeatherApp.getLocationData("Berlin");
        assertNotNull(locationData);
        assertTrue(locationData.size() > 0);
    }
}
