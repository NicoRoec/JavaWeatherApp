package src.main.java;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Die Klasse `WeatherApp` bietet Methoden zum Abrufen und Verarbeiten von Wetterdaten aus einer API.
 * Das Backend holt die neuesten Wetterdaten von der API und die GUI zeigt diese Daten dem Benutzer an.
 *
 * @author Nico Röcker
 * @version 1.0
 *
 */
public class WeatherApp {

    /**
     * Holt die Wetterdaten für einen gegebenen Ort.
     *
     * @param locationName der Name des Ortes
     * @return ein JSONObject mit den Wetterdaten
     */
    public static JSONObject getWeatherData(String locationName) {
        JSONArray locationData = getLocationData(locationName);
        JSONObject location = (JSONObject) locationData.get(0);
        double latitude = (double) location.get("latitude");
        double longitude = (double) location.get("longitude");

        String urlString = "https://api.open-meteo.com/v1/forecast?" +
                "latitude=" + latitude + "&longitude=" + longitude +
                "&hourly=temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m,is_day&timezone=Europe%2FBerlin";

        try {
            HttpURLConnection conn = fetchApiResponse(urlString);

            if (conn.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return null;
            }

            StringBuilder resultJson = new StringBuilder();
            Scanner scanner = new Scanner(conn.getInputStream());
            while (scanner.hasNext()) {
                resultJson.append(scanner.nextLine());
            }

            scanner.close();
            conn.disconnect();

            JSONParser parser = new JSONParser();
            JSONObject resultJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));
            JSONObject hourly = (JSONObject) resultJsonObj.get("hourly");
            JSONArray time = (JSONArray) hourly.get("time");
            int index = findIndexOfCurrentTime(time);

            JSONArray temperatureData = (JSONArray) hourly.get("temperature_2m");
            double temperature = (double) temperatureData.get(index);

            JSONArray weathercode = (JSONArray) hourly.get("weather_code");
            String weatherCondition = convertWeatherCode((long) weathercode.get(index));

            JSONArray relativeHumidity = (JSONArray) hourly.get("relative_humidity_2m");
            long humidity = (long) relativeHumidity.get(index);

            JSONArray windspeedData = (JSONArray) hourly.get("wind_speed_10m");
            double windspeed = (double) windspeedData.get(index);

            JSONArray isDayData = (JSONArray) hourly.get("is_day");
            long isDayOrNight = (long) isDayData.get(index);

            String dayOrNight = convertIsDayOrNight(isDayOrNight);

            JSONObject weatherData = new JSONObject();
            weatherData.put("temperature", temperature);
            weatherData.put("weather_condition", weatherCondition);
            weatherData.put("humidity", humidity);
            weatherData.put("windspeed", windspeed);
            weatherData.put("is_day", dayOrNight);

            return weatherData;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Holt die geografischen Koordinaten für einen gegebenen Ortsnamen.
     *
     * @param locationName der Name des Ortes
     * @return ein JSONArray mit den Koordinaten des Ortes
     */
    public static JSONArray getLocationData(String locationName) {
        locationName = locationName.replaceAll(" ", "+");
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                locationName + "&count=10&language=en&format=json";

        try {
            HttpURLConnection conn = fetchApiResponse(urlString);

            if (conn.getResponseCode() != 200) {
                System.out.println("Error: Could not connect to API");
                return null;
            } else {
                StringBuilder resultJson = new StringBuilder();
                Scanner scanner = new Scanner(conn.getInputStream());
                while (scanner.hasNext()) {
                    resultJson.append(scanner.nextLine());
                }

                scanner.close();
                conn.disconnect();

                JSONParser parser = new JSONParser();
                JSONObject resultsJsonObj = (JSONObject) parser.parse(String.valueOf(resultJson));
                JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
                return locationData;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Stellt eine Verbindung zur API her und holt die Antwort.
     *
     * @param urlString die URL der API
     * @return ein HttpURLConnection-Objekt
     */
    public static HttpURLConnection fetchApiResponse(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            return conn;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Findet den Index der aktuellen Zeit in der Zeitliste.
     *
     * @param timeList die Liste der Zeiten
     * @return der Index der aktuellen Zeit
     */
    public static int findIndexOfCurrentTime(JSONArray timeList) {
        String currentTime = getCurrentTime();

        for (int i = 0; i < timeList.size(); i++) {
            String time = (String) timeList.get(i);
            if (time.equalsIgnoreCase(currentTime)) {
                return i;
            }
        }

        return 0;
    }

    /**
     * Holt die aktuelle Zeit im benötigten Format.
     *
     * @return die aktuelle Zeit als String
     */
    public static String getCurrentTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");
        return currentDateTime.format(formatter);
    }

    /**
     * Konvertiert den Wettercode in eine lesbare Wetterbedingung.
     *
     * @param weathercode der Wettercode
     * @return die Wetterbedingung als String
     */
    public static String convertWeatherCode(long weathercode) {
        String weatherCondition = "";
        if (weathercode == 0L) {
            weatherCondition = "Klarer Himmel";
        } else if (weathercode > 0L && weathercode <= 3L) {
            weatherCondition = "Bewölkt";
        } else if ((weathercode >= 51L && weathercode <= 67L) || (weathercode >= 80L && weathercode <= 99L)) {
            weatherCondition = "Regen";
        } else if (weathercode >= 71L && weathercode <= 77L) {
            weatherCondition = "Schnee";
        }
        return weatherCondition;
    }

    /**
     * Konvertiert den Tag- oder Nachtstatus in ein lesbares Format.
     *
     * @param isDayOrNight der Tag- oder Nachtstatus
     * @return "Tag" oder "Nacht" als String
     */
    public static String convertIsDayOrNight(long isDayOrNight) {
        if (isDayOrNight == 0) {
            return "Nacht";
        } else if (isDayOrNight == 1) {
            return "Tag";
        }
        return "";
    }
}