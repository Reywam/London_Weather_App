package app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class MainController {
    @GetMapping("/{date}")
    public String getWeatherDataByDate(@PathVariable(value="date") String date)
    {
        return "Return all weather data by date:".concat(date);
    }


}