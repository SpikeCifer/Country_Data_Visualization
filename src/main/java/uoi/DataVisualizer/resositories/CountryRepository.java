package uoi.DataVisualizer.resositories;

import uoi.DataVisualizer.models.entities.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, String > {
    Iterable<Country> findAll();
}
