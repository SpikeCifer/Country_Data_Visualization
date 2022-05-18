package uoi.DataVisualizer.models.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BarChartRequest extends Request{
    @NotNull(message="You have to clarify what you will compare")
    private String comparisonOption;
}
