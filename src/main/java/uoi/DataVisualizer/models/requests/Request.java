package uoi.DataVisualizer.models.requests;

import lombok.Data;

import java.util.List;

@Data
public class Request {
    private List<String> countries;
    private List<String> indicators;
    private int startYear;
    private int endYear;
    private String timeLapse;
}
