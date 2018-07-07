package pl.parkin9.BikeStations_Microservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.parkin9.BikeStations_Microservice.Model.BikeStation;

import java.util.Optional;

@Repository
public interface BikeStationRepository extends JpaRepository<BikeStation, Long> {

//    @Query("select bs from BikeStation bs join bst.bikeSet b where b.serialNumber = ?1")
//    Optional<BikeStation> findBySerialNumber(String serialNumber);
}
