package uoi.DataVisualizer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import uoi.DataVisualizer.resositories.CountryRepository;
import uoi.DataVisualizer.resositories.IndicatorRepository;
import uoi.DataVisualizer.resositories.TimePeriodRepository;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    private CountryRepository countryRepo;
    @Autowired
    private IndicatorRepository indicatorRepo;
    @Autowired
    private TimePeriodRepository timeRepo;

    @GetMapping("/")
    public String showDataSelectionForm(Model model) {
        model.addAttribute("countries", countryRepo.findAll());
        model.addAttribute("indicators",indicatorRepo.findAll());
        model.addAttribute("years", timeRepo.findAll());
        return "home";
    }
}
