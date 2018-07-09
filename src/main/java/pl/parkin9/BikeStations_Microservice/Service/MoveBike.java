package pl.parkin9.BikeStations_Microservice.Service;

import pl.parkin9.BikeStations_Microservice.Model.BikeStation;
import pl.parkin9.BikeStations_Microservice.Model.UserTemp;

public interface MoveBike {

    void moveToUser(BikeStation fromBikeStation, UserTemp toUserTemp, String serialNumBike);

    void moveToBikeStation(UserTemp fromUserTemp, BikeStation toBikeStation);
}
