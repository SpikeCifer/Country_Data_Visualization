package uoi.DataVisualizer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import uoi.DataVisualizer.resositories.CountryRepository;
import uoi.DataVisualizer.resositories.IndicatorRepository;
import uoi.DataVisualizer.resositories.MeasurementRepository;
import uoi.DataVisualizer.resositories.TimePeriodRepository;

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
    public String processTimelineRequest(Model model) {
        return "result";
    }

    @PostMapping("/barChartRequest")
    public String processBarChartRequest(Model model) {
        return "result";
    }

    @PostMapping("/scatterChartRequest")
    public String scatterChartRequest(Model model) {
        return "result";
    }
}
