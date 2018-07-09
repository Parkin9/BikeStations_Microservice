package pl.parkin9.BikeStations_Microservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.parkin9.BikeStations_Microservice.Exception.BikeStationNotFoundException;
import pl.parkin9.BikeStations_Microservice.Exception.UserTempNotFoundException;
import pl.parkin9.BikeStations_Microservice.Model.BikeStation;
import pl.parkin9.BikeStations_Microservice.Model.UserTemp;
import pl.parkin9.BikeStations_Microservice.Repository.BikeStationRepository;
import pl.parkin9.BikeStations_Microservice.Repository.UserTempRepository;

import java.util.Optional;

@Service
public class GiveBackServiceImpl implements GiveBackService {

    private final BikeStationRepository bikeStationRepository;
    private final UserTempRepository userTempRepository;
    private final MoveBike moveBike;
    private final SwitchSlot switchSlot;

    @Autowired
    public GiveBackServiceImpl(BikeStationRepository bikeStationRepository, UserTempRepository userTempRepository, MoveBike moveBike, SwitchSlot switchSlot) {
        this.bikeStationRepository = bikeStationRepository;
        this.userTempRepository = userTempRepository;
        this.moveBike = moveBike;
        this.switchSlot = switchSlot;
    }

////////////////////////////////////////////////////////////////////////////////

    @Transactional
    public void giveBack(Long idStation, Long idUser) throws BikeStationNotFoundException, UserTempNotFoundException {

        Optional<UserTemp> userTemp = userTempRepository.findById(idUser);
        Optional<BikeStation> bikeStation = bikeStationRepository.findById(idStation);

        // if the UserTemp hasn't been found in DB
        if(!userTemp.isPresent()) {
            throw new UserTempNotFoundException("User (id: " + idUser + ") not found");
        }

        // if the BikeStation hasn't been found in DB
        if(!bikeStation.isPresent()) {
            throw new BikeStationNotFoundException("BikeStation (id: " + idStation + ") not found");
        }



        UserTemp fromUserTemp = userTemp.get();
        BikeStation toBikeStation = bikeStation.get();

        // getting Bike from UserTemp and saving to Station
        moveBike.moveToBikeStation(fromUserTemp, toBikeStation);

        // changing free Slot to occupied
        switchSlot.switchTrueToFalse(toBikeStation);



        userTempRepository.save(fromUserTemp);
        bikeStationRepository.save(toBikeStation);
    }
}
