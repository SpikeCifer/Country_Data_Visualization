package uoi.DataVisualizer.resositories;

import uoi.DataVisualizer.models.entities.Indicator;
import org.springframework.data.repository.CrudRepository;

public interface IndicatorRepository extends CrudRepository<Indicator, String> {
    Iterable<Indicator> findAll();
}
