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

import static app.utils.Constants.*;

@RestController
@RequestMapping("/weather")
public class MainController {
    private Validator validator = new Validator();
    private DataHelper helper = new DataHelper();

    @Autowired
    WeatherInfoRepository weatherInfoRepository;
    @Autowired
    DatesRepository datesRepository;

    @GetMapping("/{date}")
    public List<WeatherInfo> getWeatherDataByDate(@PathVariable(value="date") String date) throws Exception {
        if(!validator.isValidDate(date, helper)) {
            throw new Exception("It's not valid date value. Date pattern is ".concat(DATE_PATTERN));
        }

        LocalDate localDate = helper.getLocalDateFromDateString(date);
        Dates dateObject = datesRepository.findByDate(localDate);
        return weatherInfoRepository.findByIdDate(dateObject);
    }
}