package uoi.DataVisualizer.models.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Request {
    @NotEmpty
    @Size(min=1, message = "You must select at least one country")
    private List<String> countries;

    @NotEmpty
    @Size(min=1, message = "You must select at least one indicator")
    private List<String> indicators;

    @NotNull(message = "You must select a starting year")
    private int startYear;

    @NotNull(message = "You must select an ending year")
    private int endYear;
    private String timeLapse;
}
