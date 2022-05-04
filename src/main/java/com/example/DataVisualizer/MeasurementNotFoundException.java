package com.example.DataVisualizer;

public class MeasurementNotFoundException extends RuntimeException {
    MeasurementNotFoundException(Long id) { super("Could not find Measurement" + id); }
}
