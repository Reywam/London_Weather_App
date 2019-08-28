package app.model;

import com.fasterxml.jackson.databind.JsonNode;

public class MainData {

    public MainData(JsonNode mainDataJson, Integer visibility)
    {
        temperature = mainDataJson.get("temp").asDouble();
        pressure = mainDataJson.get("pressure").asInt();
        humidity = mainDataJson.get("humidity").asInt();
        tempMin = mainDataJson.get("temp_min").asDouble();
        tempMax = mainDataJson.get("temp_max").asDouble();
        this.visibility = visibility;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    private Double temperature;
    private Integer pressure;
    private Integer humidity;
    private Double tempMin;
    private Double tempMax;
    private Integer visibility;
}
