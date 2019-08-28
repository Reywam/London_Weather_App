package app.repository;

import app.model.WeatherInfo;
import org.springframework.data.repository.CrudRepository;

public interface WeatherInfoRepository extends CrudRepository<WeatherInfo, Integer> {
}
