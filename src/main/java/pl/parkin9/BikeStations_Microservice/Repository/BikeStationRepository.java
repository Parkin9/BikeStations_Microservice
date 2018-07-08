package pl.parkin9.BikeStations_Microservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.parkin9.BikeStations_Microservice.Model.BikeStation;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikeStationRepository extends JpaRepository<BikeStation, Long> {

    // Query to find BikeStation by Bike's serialNumber; unnecessary for now
    @Query("select bs from BikeStation bs join bs.bikeSet b where b.serialNumber = ?1")
    Optional<BikeStation> findBySerialNumber(String serialNumber);

    @Query(value = "SELECT NAME FROM BIKE_STATION ORDER BY NAME", nativeQuery = true)
    List<String> findAllName();

    @Query("select count(s) from BikeStation bs join bs.slotSet s where s.free = true and bs.name = ?1")
    Long countFreeSlot(String name);

    @Query("select count(s) from BikeStation bs join bs.slotSet s where s.free = false and bs.name = ?1")
    Long countOccupiedSlot(String name);

    @Query("select count(b) from BikeStation bs join bs.bikeSet b where bs.name = ?1")
    Long countAvailableBike(String name);
}
