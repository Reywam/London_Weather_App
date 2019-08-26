package app;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static app.Constants.API_KEY;
import static app.Constants.API_URL;

@RestController
@RequestMapping("/weather")
public class MainController {
    private Validator validator = new Validator();
    private WebClient.Builder builder = WebClient.builder();

    @GetMapping("/{date}")
    public String getWeatherDataByDate(@PathVariable(value="date") String date)
    {
        if(!validator.isValidDate(date))
        {
            return "Date is not valid";
        }
        return "Return all weather data by date:".concat(date);
    }

    @Scheduled(fixedRate = 3000)
    public void getWeatherData()
    {
        String uri = API_URL.concat(API_KEY);
        String data = builder.build()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(data);
    }


}