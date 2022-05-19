package uoi.DataVisualizer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uoi.DataVisualizer.models.entities.Measurement;
import uoi.DataVisualizer.models.requests.TimelineRequest;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class TimelineController extends RequestController {

    @GetMapping("/timeline")
    public String showForm(Model model) {
        model.addAttribute("countries", countryRepo.findAll());
        model.addAttribute("indicators", indicatorRepo.findAll());
        model.addAttribute("years", timeRepo.findAll());

        model.addAttribute("timelineRequest", new TimelineRequest());
        return "forms/timelineForm";
    }

    @PostMapping("/timeline")
    public String processRequest(@Valid TimelineRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            collectAvailableData(model);
            model.addAttribute("error", "There was an error while validating the submitted form");
            return "forms/timelineForm";
        }

        List<Measurement> measurements = measurementRepo.findByCountryInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqual(
                request.getCountries(), request.getIndicators(), request.getStartYear(), request.getEndYear()
        );

        exchangeCodesWithNames(measurements);
        model.addAttribute("measurements", measurements);
        return "results/timeline";
    }
}
