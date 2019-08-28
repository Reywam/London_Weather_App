package app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;

@Entity
public class WeatherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_date")
    @JsonIgnore
    private Dates idDate;

    // Координаты
    private Double lon;
    private Double lat;

    // Описание погоды
    private String main;
    private String description;
    // Различные числовые показатели погоды
    private Double temp;
    private Integer pressure;
    private Integer humidity;
    private Double tempMin;
    private Double tempMax;

    private Integer visibility;

    private Double windSpeed;
    private Integer windDeg;

    private Integer clouds;

    private String sunrise;
    private String sunset;

    private Integer timezone;
    private String name;

    public WeatherInfo()
    {

    }

    public WeatherInfo(
            CoordinatesData coordinatesData
            , MainData mainData
            , WeatherData weatherData
            , WindData windData
            , SystemData systemData)
    {
        lon = coordinatesData.getLon();
        lat = coordinatesData.getLat();

        main = weatherData.getMain();
        description = weatherData.getDescription();

        temp = mainData.getTemperature();
        pressure = mainData.getPressure();
        humidity = mainData.getPressure();
        tempMin = mainData.getTempMin();
        tempMax = mainData.getTempMax();
        visibility = mainData.getVisibility();

        windSpeed = windData.getSpeed();
        windDeg = windData.getDeg();
        clouds = windData.getClouds();

        sunrise = systemData.getSunrise();
        sunset = systemData.getSunset();

        timezone = systemData.getTimezone();
        name = systemData.getName();
    }

    public Dates getIdDate() {
        return idDate;
    }

    public void setIdDate(Dates idDate) {
        this.idDate = idDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
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

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
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

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(Integer windDeg) {
        this.windDeg = windDeg;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}