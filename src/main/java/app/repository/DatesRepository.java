package app.repository;

import app.model.Dates;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface DatesRepository extends CrudRepository<Dates, Integer> {
    Dates findByDate(LocalDate date);
}
