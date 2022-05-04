package com.example.DataVisualizer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, MeasurementId> {
}
