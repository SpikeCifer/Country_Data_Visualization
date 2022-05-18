package uoi.DataVisualizer.models.requests;

import lombok.Data;

import java.util.List;

@Data
public class BarChartRequest extends Request{
    private String comparisonOption;
}
