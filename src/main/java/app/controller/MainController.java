package app.controller;

import app.model.*;
import app.utils.DataHelper;
import app.utils.Validator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import app.repository.DatesRepository;
import app.repository.WeatherInfoRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static app.utils.Constants.API_KEY;
import static app.utils.Constants.API_URL;

@RestController
@RequestMapping("/weather")
public class MainController {
    private Validator validator = new Validator();
    private WebClient.Builder builder = WebClient.builder();
    private DataHelper helper = new DataHelper();

    @Autowired
    WeatherInfoRepository weatherInfoRepository;
    @Autowired
    DatesRepository datesRepository;

    @GetMapping("/{date}")
    public List<WeatherInfo> getWeatherDataByDate(@PathVariable(value="date") String date) throws Exception {
        if(!validator.isValidDate(date, helper)) {
            throw new Exception("It's not valid date value. Date pattern is dd.mm.yyyy");
        }

        LocalDate localDate = helper.getLocalDateFromDateString(date);
        Dates dateObject = datesRepository.findByDate(localDate);
        return weatherInfoRepository.findByIdDate(dateObject);
    }

    @Scheduled(fixedRate = 10000)
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