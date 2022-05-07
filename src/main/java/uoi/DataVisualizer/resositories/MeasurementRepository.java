package uoi.DataVisualizer.resositories;

import uoi.DataVisualizer.models.MeasurementId;
import uoi.DataVisualizer.models.Measurement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeasurementRepository extends CrudRepository<Measurement, MeasurementId> {
    List<Measurement> findByCountryInAndIndicator(List<String> countryCodes, String indicatorCode);

    List<Measurement> findByCountryInAndIndicatorIn(List<String> countryCodes, List<String> indicatorCodes);
}
