package app.utils;

import app.model.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static app.utils.Constants.DATE_PATTERN;

public class DataHelper {

    public DataHelper() {}

    public LocalDate getLocalDateFromDateString(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(date, formatter);
    }

    public WeatherInfo createWeatherInfoFromJson(JsonNode json) {
        JsonNode coordinatesDataJson = json.get("coord");
        JsonNode mainDataJson = json.get("main");
        Integer visibility = json.get("visibility").asInt();
        JsonNode windDataJson = json.get("wind");
        JsonNode cloudsJson = json.get("clouds");
        Integer clouds = cloudsJson.get("all").asInt();

        Double rain = null;
        if(json.has("rain")) {
            rain = json.get("rain").asDouble();
        }

        JsonNode systemDataJson = json.get("sys");
        Integer timezone = json.get("timezone").asInt();
        String cityName = json.get("name").asText();

        JsonNode weatherDataJsonArray = json.withArray("weather");
        JsonNode weatherDataJson = weatherDataJsonArray.get(0);

        CoordinatesData coordinatesData = new CoordinatesData(coordinatesDataJson);
        MainData mainData = new MainData(mainDataJson, visibility);
        WeatherData weatherData = new WeatherData(weatherDataJson);
        WindData windData = new WindData(windDataJson, clouds, rain);
        SystemData systemData = new SystemData(systemDataJson, timezone, cityName);

        return new WeatherInfo(
                coordinatesData
                , mainData
                , weatherData
                , windData
                , systemData);
    }
}