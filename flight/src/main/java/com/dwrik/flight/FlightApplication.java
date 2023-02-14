package com.dwrik.flight;

import com.dwrik.flight.model.Flight;
import com.dwrik.flight.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class FlightApplication implements CommandLineRunner {

	@Autowired
	private FlightRepository flightRepository;

	public static void main(String[] args) {
		SpringApplication.run(FlightApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.MONTH, 3);
		calendar.set(Calendar.DAY_OF_MONTH, 15);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Date currentDay = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 2);
		Date twoDaysAhead = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 3);
		Date threeDaysAhead = calendar.getTime();

		flightRepository.saveAll(List.of(
				new Flight(1L, "FN101", "BOM", "KOL", currentDay, 6500, 5),
				new Flight(2L, "FN103", "HYD", "DUR", threeDaysAhead, 5500, 0),
				new Flight(3L, "FN104", "HYD", "KOL", twoDaysAhead, 8000, 5),
				new Flight(4L, "FN105", "PUN", "DEL", twoDaysAhead, 10000, 5),
				new Flight(5L, "FN102", "DEL", "BOM", currentDay, 9500, 5)
		));
	}
}
