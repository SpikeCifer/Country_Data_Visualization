package com.example.DataVisualizer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "measurements")
@IdClass(MeasurementId.class)
public class Measurement implements Serializable {
    @Id
    private String countryCode;
    @Id
    private String indicatorCode;
    @Id
    private int year;
    private double measurement;

    protected Measurement() {}

    public Measurement(String countryCode, String indicatorCode, int year, double measurement) {
        this.countryCode = countryCode;
        this.indicatorCode = indicatorCode;
        this.year = year;
        this.measurement = measurement;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getIndicatorCode() {
        return indicatorCode;
    }

    public void setIndicatorCode(String indicatorCode) {
        this.indicatorCode = indicatorCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMeasurement() {
        return measurement;
    }

    public void setMeasurement(double measurement) {
        this.measurement = measurement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measurement that = (Measurement) o;
        return year == that.year && Double.compare(that.measurement, measurement) == 0 && Objects.equals(countryCode, that.countryCode) && Objects.equals(indicatorCode, that.indicatorCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, indicatorCode, year, measurement);
    }
}
