package uoi.DataVisualizer.controllers;

import uoi.DataVisualizer.resositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/measurements")
public class MeasurementController {
    @Autowired
    private final MeasurementRepository repository;

    public MeasurementController(MeasurementRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    String listAll() {
        repository.findAll();
        return "timeline";
    }
}