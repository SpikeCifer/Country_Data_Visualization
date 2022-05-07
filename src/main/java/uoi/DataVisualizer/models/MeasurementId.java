package uoi.DataVisualizer.models;

import java.io.Serializable;
import java.util.Objects;

public class MeasurementId implements Serializable {
    private String country;
    private String indicator;
    private int year;

    protected MeasurementId() {}
    public MeasurementId(String country, String indicator, int year) {
        this.country = country;
        this.indicator = indicator;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementId that = (MeasurementId) o;
        return year == that.year && Objects.equals(country, that.country) &&
                Objects.equals(indicator, that.indicator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, indicator, year);
    }
}
