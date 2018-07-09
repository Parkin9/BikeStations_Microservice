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
public class BorrowServiceImpl implements BorrowService {

    private final BikeStationRepository bikeStationRepository;
    private final UserTempRepository userTempRepository;
    private final MoveBike moveBike;
    private final SwitchSlot switchSlot;

    @Autowired
    public BorrowServiceImpl(BikeStationRepository bikeStationRepository, UserTempRepository userTempRepository, MoveBike moveBike, SwitchSlot switchSlot) {
        this.bikeStationRepository = bikeStationRepository;
        this.userTempRepository = userTempRepository;
        this.moveBike = moveBike;
        this.switchSlot = switchSlot;
    }

////////////////////////////////////////////////////////////////////////////////

    @Transactional
    public void borrow(Long idStation, Long idUser, String serialNumBike) throws BikeStationNotFoundException,
                                                                                    UserTempNotFoundException {

        Optional<BikeStation> bikeStation = bikeStationRepository.findById(idStation);
        Optional<UserTemp> userTemp = userTempRepository.findById(idUser);

        // if the BikeStation hasn't been found in DB
        if(!bikeStation.isPresent()) {
            throw new BikeStationNotFoundException("BikeStation (id: " + idStation + ") not found");
        }

        // if the UsetTemp hasn't been found in DB
        if(!userTemp.isPresent()) {
            throw new UserTempNotFoundException("User (id: " + idUser + ") not found");
        }



        BikeStation fromBikeStation = bikeStation.get();
        UserTemp toUserTemp = userTemp.get();

        // getting Bike from Station and saving to UserTemp
        moveBike.moveToUser(fromBikeStation, toUserTemp, serialNumBike);

        // changing occupied Slot to free
        switchSlot.switchFalseToTrue(fromBikeStation);



        bikeStationRepository.save(fromBikeStation);
        userTempRepository.save(toUserTemp);
    }
}
