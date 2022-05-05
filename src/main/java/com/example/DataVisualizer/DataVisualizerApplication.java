package com.example.DataVisualizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DataVisualizerApplication {
	private static final Logger log = LoggerFactory.getLogger(DataVisualizerApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(DataVisualizerApplication.class, args);
	}

	@Bean
	public CommandLineRunner demon(MeasurementRepository repository){
		return (args) -> {
			List<String> countries = Arrays.asList("ALB");
			List<String> indicators = Arrays.asList("TX.VAL.MRCH.WL.CD");
			log.info("Measurments found with findAll():");
			log.info("----------------------------------");
			for (Measurement m: repository.findByCountryInAndIndicatorIn(countries, indicators))
				log.info(m.toString());
			log.info("");
		};
	}
}
