package uoi.DataVisualizer.models.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class BarChartRequest {
    @NotNull(message="You have to clarify what you will compare")
    private String comparisonOption;

    @Size(min=1, message = "You must select at least one country")
    private List<String> countries;

    @Size(min=1, message = "You must select at least one country")
    private List<String> indicators;

    @Min(value=0, message="You must select a starting year")
    private int startYear;

    @Min(value=0, message="You must select an ending year")
    private int endYear;

    private String timeLapse;
}
