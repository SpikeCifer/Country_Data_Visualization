package uoi.DataVisualizer.models.requests;

import lombok.Data;
import uoi.DataVisualizer.models.entities.Country;
import uoi.DataVisualizer.models.entities.Indicator;

import java.util.List;

@Data
public class ScatterChartRequest {
    private String comparisonOption;
    private List<Country> countries;
    private List<Indicator> indicators;
    private int startYear;
    private int endYear;
}
