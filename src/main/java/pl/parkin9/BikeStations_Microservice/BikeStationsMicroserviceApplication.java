package pl.parkin9.BikeStations_Microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.parkin9.BikeStations_Microservice.Exception.BikeStationNotFoundException;

@SpringBootApplication
public class BikeStationsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeStationsMicroserviceApplication.class, args);
	}
}
