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
public class BorrowServiceImpl implements BorrowService {

    private final BikeStationRepository bikeStationRepository;
    private final UserTempRepository userTempRepository;

    @Autowired
    public BorrowServiceImpl(BikeStationRepository bikeStationRepository, UserTempRepository userTempRepository) {
        this.bikeStationRepository = bikeStationRepository;
        this.userTempRepository = userTempRepository;
    }

////////////////////////////////////////////////////////////////////////////////

    @Transactional
    public void borrow(Long idStation, Long idUser, String serialNumBike) throws BikeStationNotFoundException,
                                                                                    UserTempNotFoundException {
        Optional<BikeStation> fromBikeStation = bikeStationRepository.findById(idStation);
        Optional<UserTemp> toUserTemp = userTempRepository.findById(idUser);

        // if the BikeStation hasn't been found in DB
        if(!fromBikeStation.isPresent()) {
            throw new BikeStationNotFoundException("BikeStation (id: " + idStation + ") not found");
        }

        // if the UsetTemp hasn't been found in DB
        if(!toUserTemp.isPresent()) {
            throw new UserTempNotFoundException("User (id: " + idUser + ") not found");
        }

        Set<Bike> bikeSet = new HashSet<>(fromBikeStation.get().getBikeSet());
        Set<Slot> slotSet = new HashSet<>(fromBikeStation.get().getSlotSet());


        // get Bike from Station and save to UserTemp
        for(Bike bike : bikeSet) {

            if(bike.getSerialNumber().equals(serialNumBike)) {

                bikeSet.remove(bike);
                fromBikeStation.get().setBikeSet(bikeSet);
                toUserTemp.get().setRentBike(bike);
                break;
            }
        }

        // change occupied Slot to free
        for(Slot slot : slotSet) {

            if(slot.getFree().equals(false)) {

                slot.setFree(true);
                break;
            }
        }

        bikeStationRepository.save(fromBikeStation.get());
        userTempRepository.save(toUserTemp.get());
    }
}
