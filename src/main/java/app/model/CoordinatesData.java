package app.model;

import com.fasterxml.jackson.databind.JsonNode;

public class CoordinatesData {

    public CoordinatesData(JsonNode coordinatesJson)
    {
        lon = coordinatesJson.get("lon").asDouble();
        lat = coordinatesJson.get("lat").asDouble();
    }

    private Double lon;
    private Double lat;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
