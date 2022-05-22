package uoi.DataVisualizer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import uoi.DataVisualizer.models.entities.Measurement;
import uoi.DataVisualizer.resositories.CountryRepository;
import uoi.DataVisualizer.resositories.IndicatorRepository;
import uoi.DataVisualizer.resositories.MeasurementRepository;
import uoi.DataVisualizer.resositories.TimePeriodRepository;

import java.util.List;

@Controller
@Slf4j
public class RequestController {
    @Autowired
    protected CountryRepository countryRepo;
    @Autowired
    protected IndicatorRepository indicatorRepo;
    @Autowired
    protected TimePeriodRepository timeRepo;
    @Autowired
    protected MeasurementRepository measurementRepo;

    protected void exchangeCodesWithNames(List<Measurement> measurements) {
        for (Measurement m: measurements) {
            m.setCountry(countryRepo.findByCode(m.getCountry()).getName());
            m.setIndicator(indicatorRepo.findByCode(m.getIndicator()).getName());
        }
    }

    protected void collectAvailableData(Model model) {
       model.addAttribute("countries", countryRepo.findAll());
        model.addAttribute("indicators",indicatorRepo.findAll());
        model.addAttribute("years", timeRepo.findAll());
    }

    protected String formatHeader(Measurement m, String base) {
        if (base.equals("countries"))
            return m.getIndicator();

        return m.getCountry();
    }
}
