package app.model;

import com.fasterxml.jackson.databind.JsonNode;

public class WeatherData {
    private String main;
    private String description;

    public WeatherData(JsonNode weatherData) {
        main = weatherData.get("main").asText();
        description = weatherData.get("description").asText();
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
