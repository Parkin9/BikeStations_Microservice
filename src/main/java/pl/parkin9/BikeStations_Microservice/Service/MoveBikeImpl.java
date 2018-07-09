package pl.parkin9.BikeStations_Microservice.Service;

import org.springframework.stereotype.Component;
import pl.parkin9.BikeStations_Microservice.Model.Bike;
import pl.parkin9.BikeStations_Microservice.Model.BikeStation;
import pl.parkin9.BikeStations_Microservice.Model.UserTemp;

import java.util.HashSet;
import java.util.Set;

@Component
public class MoveBikeImpl implements MoveBike {

    public void moveToUser(BikeStation fromBikeStation, UserTemp toUserTemp, String serialNumBike) {

        Set<Bike> bikeSet = new HashSet<>(fromBikeStation.getBikeSet());

        for(Bike bike : bikeSet) {

            if(bike.getSerialNumber().equals(serialNumBike)) {

                bikeSet.remove(bike);
                fromBikeStation.setBikeSet(bikeSet);
                toUserTemp.setRentBike(bike);
                break;
            }
        }
    }


    public void moveToBikeStation(UserTemp fromUserTemp, BikeStation toBikeStation) {

        Set<Bike> bikeSet = new HashSet<>(toBikeStation.getBikeSet());

        Bike bike = fromUserTemp.getRentBike();
        fromUserTemp.setRentBike(null);
        bikeSet.add(bike);
        toBikeStation.setBikeSet(bikeSet);
    }
}
