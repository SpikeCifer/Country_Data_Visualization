package uoi.DataVisualizer;

import uoi.DataVisualizer.models.Indicator;

import uoi.DataVisualizer.resositories.IndicatorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataVisualizerApplication {
	private static final Logger log = LoggerFactory.getLogger(DataVisualizerApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(DataVisualizerApplication.class, args);
	}
}
