package app.repository;

import app.model.Dates;
import app.model.WeatherInfo;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeatherInfoRepository extends CrudRepository<WeatherInfo, Integer> {
    List<WeatherInfo> findByIdDate(Dates idDate);
}
