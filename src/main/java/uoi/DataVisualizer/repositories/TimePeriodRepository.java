package uoi.DataVisualizer.repositories;

import org.springframework.data.repository.CrudRepository;
import uoi.DataVisualizer.models.entities.TimePeriod;

import java.util.List;

public interface TimePeriodRepository extends CrudRepository<TimePeriod, Integer> {
    List<TimePeriod> findAll();
}
