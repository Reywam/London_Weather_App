package app.model;

import com.fasterxml.jackson.databind.JsonNode;

public class SystemData {
    private String country;
    private String sunrise;
    private String sunset;
    private Integer timezone;
    private String name;

    public SystemData(JsonNode systemDataJson, Integer timeZone, String name) {
        country = systemDataJson.get("country").asText();
        sunrise = systemDataJson.get("sunrise").asText();
        sunset = systemDataJson.get("sunset").asText();
        this.name = name;
        this.timezone = timeZone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
