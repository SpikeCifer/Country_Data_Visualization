package uoi.DataVisualizer.resositories;

import uoi.DataVisualizer.models.entities.MeasurementId;
import uoi.DataVisualizer.models.entities.Measurement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeasurementRepository extends CrudRepository<Measurement, MeasurementId> {
    List<Measurement> findByCountryInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqual(
            List<String> countryCodes, List<String> indicatorCodes, int startYear, int endYear);

    List<Measurement> findByCountryInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqualOrderByYear(
            List<String> countryCodes, List<String> indicatorCodes, int startYear, int endYear);

    List<Measurement> findByCountryAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqualOrderByYear(
            String countryCode, List<String> indicatorCodes, int startYear, int endYear);
}
