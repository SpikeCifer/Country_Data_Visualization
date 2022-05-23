package uoi.DataVisualizer.models.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@IdClass(MeasurementId.class)
public class Measurement implements Serializable {
    @Id
    private String country;
    @Id
    private String indicator;
    @Id
    private int year;
    private double measurement;

    protected Measurement() {}

    public Measurement(String countryCode, String indicatorCode, int year, double measurement) {
        this.country = countryCode;
        this.indicator = indicatorCode;
        this.year = year;
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "country='" + country + '\'' +
                ", indicator='" + indicator + '\'' +
                ", year=" + year +
                ", measurement=" + measurement +
                '}';
    }

    public String getCountry() {
        return country;
    }

    public String getIndicator() {
        return indicator;
    }

    public int getYear() {
        return year;
    }

    public double getMeasurement() {
        return measurement;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }
}
