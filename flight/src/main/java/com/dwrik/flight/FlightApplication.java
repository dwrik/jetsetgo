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

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Date currentDay = calendar.getTime();

		calendar.add(Calendar.DAY_OF_MONTH, 2);
		Date twoDaysAhead = calendar.getTime();

		calendar.add(Calendar.DAY_OF_MONTH, 3);
		Date threeDaysAhead = calendar.getTime();

//		Date twoDaysAhead = new Date(currentDay.getTime() + 2 * 24 * 3600 * 1000L);
//		Date threeDaysAhead = new Date(currentDay.getTime() + 3 * 24 * 3600 * 1000L);

		flightRepository.deleteAll();

		flightRepository.saveAll(List.of(
				new Flight("FN101", "BOM", "KOL", currentDay, 6500, 5),
				new Flight("FN103", "HYD", "DUR", threeDaysAhead, 5500, 5),
				new Flight("FN104", "HYD", "KOL", twoDaysAhead, 8000, 5),
				new Flight("FN105", "PUN", "DEL", twoDaysAhead, 10000, 5),
				new Flight("FN102", "DEL", "BOM", currentDay, 9500, 5)
		));
	}
}
