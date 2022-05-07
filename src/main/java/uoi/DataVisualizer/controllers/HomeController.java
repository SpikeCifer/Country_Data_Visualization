package uoi.DataVisualizer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uoi.DataVisualizer.resositories.CountryRepository;
import uoi.DataVisualizer.resositories.IndicatorRepository;
import uoi.DataVisualizer.resositories.TimePeriodRepository;


@Controller
public class HomeController {
    private final CountryRepository countryRepo;
    private final IndicatorRepository indicatorRepo;
    private final TimePeriodRepository timeRepo;

    public HomeController(CountryRepository countryRepo, IndicatorRepository indicatorRepo, TimePeriodRepository timeRepo) {
        this.countryRepo = countryRepo;
        this.indicatorRepo = indicatorRepo;
        this.timeRepo = timeRepo;
    }

    @GetMapping
    public String showDataSelectionForm(Model model) {
        model.addAttribute("countries", countryRepo.findAll());
        model.addAttribute("indicators",indicatorRepo.findAll());
        model.addAttribute("years", timeRepo.findAll());
        return "home";
    }
}
