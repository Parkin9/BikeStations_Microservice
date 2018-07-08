package pl.parkin9.BikeStations_Microservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.parkin9.BikeStations_Microservice.Exception.BikeStationNotFoundException;
import pl.parkin9.BikeStations_Microservice.Exception.UserTempNotFoundException;
import pl.parkin9.BikeStations_Microservice.Model.Bike;
import pl.parkin9.BikeStations_Microservice.Model.BikeStation;
import pl.parkin9.BikeStations_Microservice.Model.Slot;
import pl.parkin9.BikeStations_Microservice.Model.UserTemp;
import pl.parkin9.BikeStations_Microservice.Repository.BikeStationRepository;
import pl.parkin9.BikeStations_Microservice.Repository.UserTempRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class GiveBackServiceImpl implements GiveBackService {

    private final BikeStationRepository bikeStationRepository;
    private final UserTempRepository userTempRepository;

    @Autowired
    public GiveBackServiceImpl(BikeStationRepository bikeStationRepository, UserTempRepository userTempRepository) {
        this.bikeStationRepository = bikeStationRepository;
        this.userTempRepository = userTempRepository;
    }

////////////////////////////////////////////////////////////////////////////////

    @Transactional
    public void giveBack(Long idStation, Long idUser) throws BikeStationNotFoundException, UserTempNotFoundException {

        Optional<UserTemp> fromUserTemp = userTempRepository.findById(idUser);
        Optional<BikeStation> toBikeStation = bikeStationRepository.findById(idStation);

        // if the UserTemp hasn't been found in DB
        if(!fromUserTemp.isPresent()) {
            throw new UserTempNotFoundException("User (id: " + idUser + ") not found");
        }

        // if the BikeStation hasn't been found in DB
        if(!toBikeStation.isPresent()) {
            throw new BikeStationNotFoundException("BikeStation (id: " + idStation + ") not found");
        }

        Set<Bike> bikeSet = new HashSet<>(toBikeStation.get().getBikeSet());
        Set<Slot> slotSet = new HashSet<>(toBikeStation.get().getSlotSet());


        // get Bike from UserTemp and save to Station
        Bike bike = fromUserTemp.get().getRentBike();
        fromUserTemp.get().setRentBike(null);
        bikeSet.add(bike);
        toBikeStation.get().setBikeSet(bikeSet);

        // change free Slot to occupied
        for(Slot slot : slotSet) {

            if(slot.getFree().equals(true)) {

                slot.setFree(false);
                break;
            }
        }

        userTempRepository.save(fromUserTemp.get());
        bikeStationRepository.save(toBikeStation.get());
    }
}
