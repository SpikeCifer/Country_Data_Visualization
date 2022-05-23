package uoi.DataVisualizer.repositories;

import uoi.DataVisualizer.models.entities.Indicator;
import org.springframework.data.repository.CrudRepository;

public interface IndicatorRepository extends CrudRepository<Indicator, String> {
    Iterable<Indicator> findAll();
    Indicator findByCode(String code);
}
