package app.model;

import com.fasterxml.jackson.databind.JsonNode;

public class WindData {
    private Double speed;
    private Integer deg;
    private Double rain;
    private Integer clouds;

    public WindData(JsonNode windData, Integer clouds, Double rain)
    {
        speed = windData.get("speed").asDouble();
        deg = null;
        if(windData.has("deg"))
        {
            deg = windData.get("deg").asInt();
        }
        this.clouds = clouds;
        this.rain = rain;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }
}
