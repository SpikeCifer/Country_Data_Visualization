package com.example.DataVisualizer;

public class MeasurementNotFoundException extends RuntimeException {
    MeasurementNotFoundException(MeasurementId id) { super("Could not find Measurement" + id); }
}
