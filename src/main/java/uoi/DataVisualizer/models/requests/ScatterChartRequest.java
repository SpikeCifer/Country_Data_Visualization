package uoi.DataVisualizer.models.requests;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ScatterChartRequest {
    private String metric1;
    private String metric2;
    private String comparisonOption;
    private List<String> countries;
    private int startYear;
    private int endYear;

    public List<String> getIndicators() {
        return Arrays.asList(metric1, metric2);
    }
}
