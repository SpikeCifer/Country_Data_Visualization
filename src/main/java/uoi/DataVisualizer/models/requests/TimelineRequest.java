package uoi.DataVisualizer.models.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class TimelineRequest {
    @Size(min=1, message="You must select at least one country")
    private List<String> countries;

    @Size(min=1, message="You must select at least one indicator")
    private List<String> indicators;

    @Min(value=0, message="You must select a starting year")
    private int startYear;

    @Min(value=0, message="You must select an ending year")
    private int endYear;

    private String timeLapse;
}
