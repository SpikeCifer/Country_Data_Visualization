package com.example.DataVisualizer;

import java.io.Serializable;
import java.util.Objects;

public class MeasurementId implements Serializable {
    private String countryCode;
    private String indicatorCode;
    private int year;

    public MeasurementId() {}

    public MeasurementId(String countryCode, String indicatorCode, int year) {
        this.countryCode = countryCode;
        this.indicatorCode = indicatorCode;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementId that = (MeasurementId) o;
        return year == that.year && Objects.equals(countryCode, that.countryCode) && Objects.equals(indicatorCode, that.indicatorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, indicatorCode, year);
    }
}
