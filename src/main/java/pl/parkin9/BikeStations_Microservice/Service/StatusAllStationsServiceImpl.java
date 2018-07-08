package pl.parkin9.BikeStations_Microservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.parkin9.BikeStations_Microservice.Model.StatusStation;
import pl.parkin9.BikeStations_Microservice.Repository.BikeStationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusAllStationsServiceImpl implements StatusAllStationsService {

    private final BikeStationRepository bikeStationRepository;

    @Autowired
    public StatusAllStationsServiceImpl(BikeStationRepository bikeStationRepository) {
        this.bikeStationRepository = bikeStationRepository;
    }

///////////////////////////////////////////////////////////////////////////////

    public List<StatusStation> build() {

        List<StatusStation> statusList = new ArrayList<>();

        List<String> nameStations = new ArrayList<>(bikeStationRepository.findAllName());
        Long freeSlot;
        Long occupiedSlot;
        Long availableBike;

        for(String name : nameStations) {

            freeSlot = bikeStationRepository.countFreeSlot(name);
            occupiedSlot = bikeStationRepository.countOccupiedSlot(name);
            availableBike = bikeStationRepository.countAvailableBike(name);

            StatusStation statusStation = new StatusStation(name, freeSlot, occupiedSlot, availableBike);

            statusList.add(statusStation);
        }

        return statusList;
    }
}
