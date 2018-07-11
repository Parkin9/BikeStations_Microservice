package pl.parkin9.BikeStations_Microservice;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.parkin9.BikeStations_Microservice.Controller.BikeStationController;
import pl.parkin9.BikeStations_Microservice.Model.Bike;
import pl.parkin9.BikeStations_Microservice.Model.BikeStation;
import pl.parkin9.BikeStations_Microservice.Model.Slot;
import pl.parkin9.BikeStations_Microservice.Model.UserTemp;
import pl.parkin9.BikeStations_Microservice.Repository.BikeStationRepository;
import pl.parkin9.BikeStations_Microservice.Repository.UserTempRepository;
import pl.parkin9.BikeStations_Microservice.Service.MoveBike;
import pl.parkin9.BikeStations_Microservice.Service.SwitchSlot;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BikeStationControllerMicroserviceApplicationTests {

	@Autowired
	private BikeStationController bikeStationController;
	@Autowired
    private MoveBike moveBike;
	@Autowired
    private SwitchSlot switchSlot;

	private static BikeStation fromBikeStation;
	private static UserTemp toUserTemp;
	private static Slot slotRentFrom;
    private static Slot slotToBack;
    private static BikeStation toBikeStation;
    private static UserTemp fromUserTemp;

/////////////////////////////////////////////////////////////////

	@BeforeClass
    public static void mockUpBikeStationAndUserTemp() {

        /*
         * Renting a bike
         */
        Set<Bike> bikeSetFrom = new HashSet<>();
        bikeSetFrom.add(new Bike("869ISyt6709"));

        Set<Slot> slotSetFrom = new HashSet<>();
        slotRentFrom = new Slot(false);
        slotSetFrom.add(slotRentFrom);

        fromBikeStation = new BikeStation("nameTestFrom", bikeSetFrom, slotSetFrom);
        toUserTemp = new UserTemp("Ala", null);

        /*
         * Giving a bike back
         */
        Set<Bike> bikeSetTo = new HashSet<>();

        Set<Slot> slotSetTo = new HashSet<>();
        slotToBack = new Slot(true);
        slotSetTo.add(slotToBack);

        fromUserTemp = new UserTemp("Ela", new Bike("GtYU5473KI98"));
        toBikeStation = new BikeStation("nameTestTo", bikeSetTo, slotSetTo);
    }

    @Test
	public void contextLoads() {

		assertNotNull(bikeStationController);
	}

    /*
     * Renting a bike
     */
	@Test
	public void shouldMoveBike_FromStationToUser() {

	    moveBike.moveToUser(fromBikeStation, toUserTemp, "869ISyt6709");

	    assertTrue(fromBikeStation.getBikeSet().isEmpty());

        assertNotNull(toUserTemp.getRentBike());

        assertEquals("869ISyt6709", toUserTemp.getRentBike().getSerialNumber());

    }

    /*
     * Renting a bike
     */
    @Test
    public void shouldSwitchSlot_FromFalseToTrue() {

	    switchSlot.switchFalseToTrue(fromBikeStation);

	    assertEquals(true, slotRentFrom.getFree());
    }

///////////////////////////////////////////////////////////////////////////////

    /*
     * Giving a bike back
     */
    @Test
    public void shouldMoveBike_FromUserToStation() {

	    moveBike.moveToBikeStation(fromUserTemp, toBikeStation);

        for(Bike bike : toBikeStation.getBikeSet()) {
            if(!bike.getSerialNumber().equals("GtYU5473KI98")) {
                fail();
            }
        }

        assertNull(fromUserTemp.getRentBike());
    }

    /*
     * Giving a bike back
     */
    @Test
    public void shouldSwitchSlot_FromTrueToFalse() {

        switchSlot.switchTrueToFalse(toBikeStation);

        assertEquals(false, slotToBack.getFree());
    }
}
