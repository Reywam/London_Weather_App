package app;

import app.model.*;
import app.repository.DatesRepository;
import app.repository.WeatherInfoRepository;
import app.utils.DataHelper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static app.utils.Constants.*;

@Component
public class SchedulesTasks {

    private WebClient.Builder builder = WebClient.builder();
    private WeatherInfoRepository weatherInfoRepository;
    private DatesRepository datesRepository;
    private DataHelper helper = new DataHelper();

    public SchedulesTasks(WeatherInfoRepository weatherInfoRepository, DatesRepository datesRepository) {
        this.weatherInfoRepository = weatherInfoRepository;
        this.datesRepository = datesRepository;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void getWeatherData() {
        String data = builder.build()
                .get()
                .uri(REQUEST_URI)
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

        WeatherInfo info = helper.createWeatherInfoFromJson(mainNode);

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
