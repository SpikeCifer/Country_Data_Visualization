package uoi.DataVisualizer.models.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

@Data
public class ScatterChartRequest {
    @Size(min=2, message="You must select an indicator")
    private String metric1;
    @Size (min=2, message="You must select an indicator")
    private String metric2;

    @Size(min=3, message="You must select a country")
    private String country;

    @Min(value=0, message = "You must select a starting year")
    private int startYear;

    @Min(value=0, message = "You must select an ending year")
    private int endYear;

    private String timeLapse;
    public List<String> getIndicators() {
        return Arrays.asList(metric1, metric2);
    }
}
