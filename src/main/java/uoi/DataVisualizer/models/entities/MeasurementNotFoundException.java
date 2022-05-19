package uoi.DataVisualizer.models.entities;

public class MeasurementNotFoundException extends RuntimeException {
    MeasurementNotFoundException(MeasurementId id) { super("Could not find Measurement" + id); }
}
