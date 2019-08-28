package app.repository;

import app.model.WeatherData;
import org.springframework.data.repository.CrudRepository;

public interface WeatherDataRepository extends CrudRepository<WeatherData, Integer> {
}
