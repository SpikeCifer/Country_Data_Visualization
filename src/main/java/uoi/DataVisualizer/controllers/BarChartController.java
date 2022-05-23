package uoi.DataVisualizer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import uoi.DataVisualizer.models.entities.Measurement;
import uoi.DataVisualizer.models.requests.BarChartRequest;
import uoi.DataVisualizer.models.requests.CountriesBarChartRequest;
import uoi.DataVisualizer.models.requests.IndicatorsBarChartRequest;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class BarChartController extends RequestController {
    @GetMapping("/barChart")
    public String showForm(Model model) {
        model.addAttribute("countries", countryRepo.findAll());
        model.addAttribute("indicators", indicatorRepo.findAll());
        model.addAttribute("years", timeRepo.findAll());

        model.addAttribute("barChartRequest", new BarChartRequest());
        return "forms/barChartForm";
    }

    @PostMapping("/barChart")
    public String processRequest(@Valid BarChartRequest request, BindingResult result, Model model) {
        collectAvailableData(model);
        model.addAttribute("error", "There was an error while validating the submitted form");
        return "forms/barChartForm";
    }

    @PostMapping("/barChart/countries")
    public String processCountriesBarRequest(@Valid CountriesBarChartRequest request, BindingResult result, Model model) {
        if (result.hasErrors() || request.getEndYear() < request.getStartYear()) { // Does not print errors
            collectAvailableData(model);
            model.addAttribute("error", "There was an error while validating the submitted form");
            model.addAttribute("barChartRequest", new BarChartRequest());
            return "forms/barChartForm";
        }
        List<Measurement> measurements = measurementRepo.findByCountryInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqual(
                request.getCountries(), request.getIndicators(), request.getStartYear(), request.getEndYear()
        );
        exchangeCodesWithNames(measurements);
        model.addAttribute("measurements", measurements);
        model.addAttribute("comparisonElements", request.getComparisonOption());
        model.addAttribute("header", formatHeader(measurements.get(0), request.getComparisonOption()));
        return "results/barChart";
    }

    @PostMapping("/barChart/indicators")
    public String processIndicatorsBarRequest(@Valid IndicatorsBarChartRequest request, BindingResult result, Model model) {
        log.info(request.toString());
        if (result.hasErrors() || request.getEndYear() < request.getStartYear()) { // Does not print errors
            collectAvailableData(model);
            model.addAttribute("error", "There was an error while validating the submitted form");
            model.addAttribute("barChartRequest", new BarChartRequest());
            return "forms/barChartForm";
        }
        List<Measurement> measurements = measurementRepo.findByCountryInAndIndicatorInAndYearGreaterThanEqualAndYearLessThanEqual(
                request.getCountries(), request.getIndicators(), request.getStartYear(), request.getEndYear()
        );
        exchangeCodesWithNames(measurements);
        model.addAttribute("measurements", measurements);
        model.addAttribute("comparisonElements", request.getComparisonOption());
        model.addAttribute("header", formatHeader(measurements.get(0), request.getComparisonOption()));
        return "results/barChart";
    }
}
