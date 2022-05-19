package uoi.DataVisualizer.models.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Data
public class ScatterChartRequest {
    @NotNull
    private String metric1;
    @NotNull
    private String metric2;

    @NotEmpty
    private List<String> countries;

    @Min(value=0, message = "You must select a starting year")
    private int startYear;

    @Min(value=0, message = "You must select an ending year")
    private int endYear;

    public List<String> getIndicators() {
        return Arrays.asList(metric1, metric2);
    }
}
