package app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class MainController {

    private Validator validator = new Validator();

    @GetMapping("/{date}")
    public String getWeatherDataByDate(@PathVariable(value="date") String date)
    {
        if(!validator.isValidDate(date))
        {
            return "Date is not valid";
        }
        return "Return all weather data by date:".concat(date);
    }


}