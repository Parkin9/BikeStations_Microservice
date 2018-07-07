package pl.parkin9.BikeStations_Microservice.Service;

import pl.parkin9.BikeStations_Microservice.Exception.BikeStationNotFoundException;
import pl.parkin9.BikeStations_Microservice.Exception.UserTempNotFoundException;

public interface GiveBackService {

    void giveBack(Long idStation, Long idUser) throws BikeStationNotFoundException, UserTempNotFoundException;
}
