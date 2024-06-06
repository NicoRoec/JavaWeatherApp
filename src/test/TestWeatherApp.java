package src.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import src.WeatherApp;

import static org.junit.jupiter.api.Assertions.*;
import java.net.HttpURLConnection;

public class TestWeatherApp {

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

    @Test
    public void testGetLocationData() {
        String location = "Berlin";
        JSONArray locationData = WeatherApp.getLocationData(location);
        assertNotNull(locationData);
        assertFalse(locationData.isEmpty());
    }

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

    @Test
    public void testFindIndexOfCurrentTime() {
        JSONArray timeList = new JSONArray();
        timeList.add("2024-06-06T00:00");
        timeList.add("2024-06-06T01:00");
        int index = WeatherApp.findIndexOfCurrentTime(timeList);
        assertTrue(index >= 0 && index < timeList.size());
    }

    @Test
    public void testConvertWeatherCode() {
        assertEquals("Klarer Himmel", WeatherApp.convertWeatherCode(0L));
        assertEquals("Bewölkt", WeatherApp.convertWeatherCode(2L));
        assertEquals("Regen", WeatherApp.convertWeatherCode(60L));
        assertEquals("Schnee", WeatherApp.convertWeatherCode(75L));
    }

    @Test
    public void testConvertIsDayOrNight() {
        assertEquals("Tag", WeatherApp.convertIsDayOrNight(1));
        assertEquals("Nacht", WeatherApp.convertIsDayOrNight(0));
    }
}
