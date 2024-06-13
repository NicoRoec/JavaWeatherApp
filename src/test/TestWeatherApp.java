package src.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import src.WeatherApp;

import static org.junit.jupiter.api.Assertions.*;
import java.net.HttpURLConnection;

/**
 * Die Klasse `TestWeatherApp` enthält Unit-Tests für die Methoden der Klasse `WeatherApp`.
 *
 * @author Nico Röcker
 * @version X.0 (wurde nachträglich eingefügt, daher ist Version unbekannt)
 *
 */
public class TestWeatherApp {

    /**
     * Testet die Methode `getWeatherData` der Klasse `WeatherApp`.
     * Überprüft, ob Wetterdaten erfolgreich abgerufen und alle erforderlichen Schlüssel vorhanden sind.
     */
    @org.junit.Test
    @Test
    public void testGetWeatherData() {
        String location = "Berlin";
        JSONObject weatherData = WeatherApp.getWeatherData(location);
        assertNotNull(weatherData);
        assertTrue(weatherData.containsKey("temperature"));
        assertTrue(weatherData.containsKey("weather_condition"));
        assertTrue(weatherData.containsKey("humidity"));
        assertTrue(weatherData.containsKey("windspeed"));
        assertTrue(weatherData.containsKey("is_day"));
    }

    /**
     * Testet die Methode `getLocationData` der Klasse `WeatherApp`.
     * Überprüft, ob Standortdaten erfolgreich abgerufen und nicht leer sind.
     */
    @Test
    public void testGetLocationData() {
        String location = "Berlin";
        JSONArray locationData = WeatherApp.getLocationData(location);
        assertNotNull(locationData);
        assertFalse(locationData.isEmpty());
    }

    /**
     * Testet die Methode `fetchApiResponse` der Klasse `WeatherApp`.
     * Überprüft, ob die API-Verbindung erfolgreich hergestellt und der Statuscode 200 zurückgegeben wird.
     */
    @Test
    public void testFetchApiResponse() {
        String urlString = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.405&hourly=temperature_2m";
        HttpURLConnection conn = WeatherApp.fetchApiResponse(urlString);
        assertNotNull(conn);
        try {
            assertEquals(200, conn.getResponseCode());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    /**
     * Testet die Methode `findIndexOfCurrentTime` der Klasse `WeatherApp`.
     * Überprüft, ob der Index der aktuellen Zeit korrekt gefunden wird.
     */
    @Test
    public void testFindIndexOfCurrentTime() {
        JSONArray timeList = new JSONArray();
        timeList.add("2024-06-06T00:00");
        timeList.add("2024-06-06T01:00");
        int index = WeatherApp.findIndexOfCurrentTime(timeList);
        assertTrue(index >= 0 && index < timeList.size());
    }

    /**
     * Testet die Methode `convertWeatherCode` der Klasse `WeatherApp`.
     * Überprüft, ob Wettercodes korrekt in lesbare Wetterbedingungen umgewandelt werden.
     */
    @Test
    public void testConvertWeatherCode() {
        assertEquals("Klarer Himmel", WeatherApp.convertWeatherCode(0L));
        assertEquals("Bewölkt", WeatherApp.convertWeatherCode(2L));
        assertEquals("Regen", WeatherApp.convertWeatherCode(60L));
        assertEquals("Schnee", WeatherApp.convertWeatherCode(75L));
    }

    /**
     * Testet die Methode `convertIsDayOrNight` der Klasse `WeatherApp`.
     * Überprüft, ob der Tag- oder Nachtstatus korrekt in lesbare Werte umgewandelt wird.
     */
    @Test
    public void testConvertIsDayOrNight() {
        assertEquals("Tag", WeatherApp.convertIsDayOrNight(1));
        assertEquals("Nacht", WeatherApp.convertIsDayOrNight(0));
    }
}
