package uoi.DataVisualizer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uoi.DataVisualizer.models.entities.Measurement;
import uoi.DataVisualizer.models.requests.ScatterChartRequest;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class ScatterChartRequestController extends RequestController {
    @GetMapping("/scatterChart")
    public String showForm(Model model) {
        model.addAttribute("countries", countryRepo.findAll());
        model.addAttribute("indicators", indicatorRepo.findAll());
        model.addAttribute("years", timeRepo.findAll());

        model.addAttribute("scatterChartRequest", new ScatterChartRequest());
        return "forms/scatterChartForm";
    }

    @PostMapping("/scatterChart")
    public String processRequest(@Valid ScatterChartRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            collectAvailableData(model);
            model.addAttribute("error", "There was an error while validating the submitted form");
            return "forms/scatterChartForm";
        }

        List<Measurement> measurements = measurementRepo.findByCountryInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqualOrderByYear(
                request.getCountries(), request.getIndicators(), request.getStartYear(), request.getEndYear()
        );

        exchangeCodesWithNames(measurements);
        model.addAttribute("header", "For Country: "+measurements.get(0).getCountry());
        model.addAttribute("measurements", measurements);
        model.addAttribute("metric1", indicatorRepo.findByCode(request.getMetric1()).getName());
        model.addAttribute("metric2", indicatorRepo.findByCode(request.getMetric2()).getName());
        return "results/scatterChart";
    }
}
