import app.WeatherApp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherAppTest {

    @Test
    public void testGetWeatherData() {
        JSONObject weatherData = WeatherApp.getWeatherData("Berlin");
        assertNotNull(weatherData);
        assertTrue(weatherData.containsKey("temperature"));
        assertTrue(weatherData.containsKey("humidity"));
        assertTrue(weatherData.containsKey("windspeed"));
        assertTrue(weatherData.containsKey("weather_condition"));
    }

    @Test
    public void testGetLocationData() {
        JSONArray locationData = WeatherApp.getLocationData("Berlin");
        assertNotNull(locationData);
        assertTrue(locationData.size() > 0);
    }
}
