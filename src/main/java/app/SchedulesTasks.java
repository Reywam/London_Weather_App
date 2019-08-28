package app;

import app.model.*;
import app.repository.DatesRepository;
import app.repository.WeatherInfoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static app.utils.Constants.API_KEY;
import static app.utils.Constants.API_URL;

@Component
public class SchedulesTasks {

    private WebClient.Builder builder = WebClient.builder();
    private WeatherInfoRepository weatherInfoRepository;
    private DatesRepository datesRepository;

    public SchedulesTasks(WeatherInfoRepository weatherInfoRepository, DatesRepository datesRepository) {
        this.weatherInfoRepository = weatherInfoRepository;
        this.datesRepository = datesRepository;
    }

    @Scheduled(fixedRate = 3000)
    public void getWeatherData() {
        String uri = API_URL.concat(API_KEY);
        String data = builder.build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode mainNode = null;
        try {
            mainNode = mapper.readTree(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonNode coordinatesDataJson = mainNode.get("coord");
        JsonNode mainDataJson = mainNode.get("main");
        Integer visibility = mainNode.get("visibility").asInt();
        JsonNode windDataJson = mainNode.get("wind");
        JsonNode cloudsJson = mainNode.get("clouds");
        Integer clouds = cloudsJson.get("all").asInt();

        Double rain = null;
        if(mainNode.has("rain"))
        {
            rain = mainNode.get("rain").asDouble();
        }

        JsonNode systemDataJson = mainNode.get("sys");
        Integer timezone = mainNode.get("timezone").asInt();
        String cityName = mainNode.get("name").asText();

        JsonNode weatherDataJsonArray = mainNode.withArray("weather");
        JsonNode weatherDataJson = weatherDataJsonArray.get(0);

        CoordinatesData coordinatesData = new CoordinatesData(coordinatesDataJson);
        MainData mainData = new MainData(mainDataJson, visibility);
        WeatherData weatherData = new WeatherData(weatherDataJson);
        WindData windData = new WindData(windDataJson, clouds, rain);
        SystemData systemData = new SystemData(systemDataJson, timezone, cityName);

        WeatherInfo info = new WeatherInfo(
                coordinatesData
                , mainData
                , weatherData
                , windData
                , systemData
        );

        Dates date = new Dates();
        Dates existingDate = datesRepository.findByDate(date.getDate());
        if(existingDate == null)
        {
            date = datesRepository.save(date);
            info.setIdDate(date);
        }
        else
        {
            info.setIdDate(existingDate);
        }

        weatherInfoRepository.save(info);
    }
}
