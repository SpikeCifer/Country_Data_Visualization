package uoi.DataVisualizer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import uoi.DataVisualizer.models.entities.Measurement;
import uoi.DataVisualizer.models.requests.BarChartRequest;
import uoi.DataVisualizer.models.requests.Request;
import uoi.DataVisualizer.models.requests.ScatterChartRequest;
import uoi.DataVisualizer.models.requests.TimelineRequest;
import uoi.DataVisualizer.resositories.CountryRepository;
import uoi.DataVisualizer.resositories.IndicatorRepository;
import uoi.DataVisualizer.resositories.MeasurementRepository;
import uoi.DataVisualizer.resositories.TimePeriodRepository;

import java.util.List;

@Controller
@Slf4j
public class HomeController {
    private final CountryRepository countryRepo;
    private final IndicatorRepository indicatorRepo;
    private final TimePeriodRepository timeRepo;
    private final MeasurementRepository measurementRepo;

    public HomeController(CountryRepository countryRepo, IndicatorRepository indicatorRepo,
                          TimePeriodRepository timeRepo, MeasurementRepository measurementRepo) {
        this.countryRepo = countryRepo;
        this.indicatorRepo = indicatorRepo;
        this.timeRepo = timeRepo;
        this.measurementRepo = measurementRepo;
    }

    @GetMapping
    public String showDataSelectionForm(Model model) {
        model.addAttribute("countries", countryRepo.findAll());
        model.addAttribute("indicators",indicatorRepo.findAll());
        model.addAttribute("years", timeRepo.findAll());
        return "home";
    }

    @PostMapping("/timelineRequest")
    public String processTimelineRequest(TimelineRequest request, Model model) {
        // TODO: Add form Validation
        List<Measurement> measurements = measurementRepo.findByCountryInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqual(
                request.getCountries(), request.getIndicators(), request.getStartYear(), request.getEndYear()
        );
        formatMeasurementsForChart(measurements);
        model.addAttribute("measurements", measurements);
        return "timeline";
    }

    @PostMapping("/barChartRequest")
    public String processBarChartRequest(BarChartRequest request, Model model) {
        // TODO: Add form Validation
        List<Measurement> measurements = measurementRepo.findByCountryInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqual(
                request.getCountries(), request.getIndicators(), request.getStartYear(), request.getEndYear()
        );
        formatMeasurementsForChart(measurements);
        model.addAttribute("measurements", measurements);
        model.addAttribute("comparisonElements", request.getComparisonOption());
        model.addAttribute("header", formatHeader(measurements.get(0), request.getComparisonOption()));
        return "barChart";
    }

    @PostMapping("/scatterChartRequest")
    public String processScatterChartRequest(ScatterChartRequest request, Model model) {
        // TODO: Add form Validation

        List<Measurement> measurements;
        measurements = measurementRepo.findByCountryInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqualOrderByYear(
                request.getCountries(), request.getIndicators(), request.getStartYear(), request.getEndYear()
        );
        formatMeasurementsForChart(measurements);
        model.addAttribute("header", "For Country: "+measurements.get(0).getCountry());
        model.addAttribute("measurements", measurements);
        model.addAttribute("metric1", indicatorRepo.findByCode(request.getMetric1()).getName());
        model.addAttribute("metric2", indicatorRepo.findByCode(request.getMetric2()).getName());
        return "scatterChart";
    }

    /*
    * Exchange Countries' and Indicators' codes with their names
    */
    private void formatMeasurementsForChart(List<Measurement> measurements) {
        for (Measurement m: measurements) {
            m.setCountry(countryRepo.findByCode(m.getCountry()).getName());
            m.setIndicator(indicatorRepo.findByCode(m.getIndicator()).getName());
        }
    }

    private String formatHeader(Measurement m, String base) {
        String header = "Base was ";

        if (base.equals("countries"))
           return header + m.getIndicator();

        return header + m.getCountry();
    }
}
