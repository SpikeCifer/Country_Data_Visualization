package uoi.DataVisualizer.models.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class IndicatorsBarChartRequest extends BarChartRequest {
    @Size(min=1, message="You must declare two countries for comparison")
    private List<String> countries;

    @Size(min=2, message="You must declare the base indicator")
    private List<String> indicators;

    @Min(value=0)
    private int startYear;

    @Min(value=0)
    private int endYear;

    private String timeLapse;
}
