package pl.parkin9.BikeStations_Microservice.Service;

import pl.parkin9.BikeStations_Microservice.Exception.BikeStationNotFoundException;
import pl.parkin9.BikeStations_Microservice.Exception.UserTempNotFoundException;

public interface BorrowService {

    void borrow(Long idStation, Long idUser, String serialNumBike) throws BikeStationNotFoundException,
                                                                            UserTempNotFoundException;
}
