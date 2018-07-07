package pl.parkin9.BikeStations_Microservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.parkin9.BikeStations_Microservice.Model.UserTemp;

@Repository
public interface UserTempRepository extends JpaRepository<UserTemp, Long> {
}
