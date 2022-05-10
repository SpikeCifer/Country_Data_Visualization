package uoi.DataVisualizer.resositories;

import org.springframework.data.repository.CrudRepository;
import uoi.DataVisualizer.models.TimePeriod;

import java.util.List;

public interface TimePeriodRepository extends CrudRepository<TimePeriod, Integer> {
    List<TimePeriod> findAll();
}