package pl.parkin9.BikeStations_Microservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.parkin9.BikeStations_Microservice.Controller.BikeStationController;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BikeStationControllerMicroserviceApplicationTests {

	@Autowired
	private BikeStationController bikeStationController;

/////////////////////////////////////////////////////////////////

	@Test
	public void contextLoads() {

		assertNotNull(bikeStationController);
	}

}
