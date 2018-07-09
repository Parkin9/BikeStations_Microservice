package pl.parkin9.BikeStations_Microservice.Service;

import pl.parkin9.BikeStations_Microservice.Model.BikeStation;

public interface SwitchSlot {

    void switchFalseToTrue(BikeStation fromBikeStation);

    void switchTrueToFalse(BikeStation toBikeStation);
}
