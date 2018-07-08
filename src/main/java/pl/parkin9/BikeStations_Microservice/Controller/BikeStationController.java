package pl.parkin9.BikeStations_Microservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.parkin9.BikeStations_Microservice.Exception.BikeStationNotFoundException;
import pl.parkin9.BikeStations_Microservice.Exception.UserTempNotFoundException;
import pl.parkin9.BikeStations_Microservice.Model.BikeStation;
import pl.parkin9.BikeStations_Microservice.Model.StatusStation;
import pl.parkin9.BikeStations_Microservice.Repository.BikeStationRepository;
import pl.parkin9.BikeStations_Microservice.Service.BorrowService;
import pl.parkin9.BikeStations_Microservice.Service.GiveBackService;
import pl.parkin9.BikeStations_Microservice.Service.StatusAllStationsService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BikeStationController {

    private final BikeStationRepository bikeStationRepository;
    private final BorrowService borrowService;
    private final GiveBackService giveBackService;
    private final StatusAllStationsService statusAllStationsService;

    @Autowired
    public BikeStationController(BikeStationRepository bikeStationRepository,
                                 BorrowService borrowService,
                                 GiveBackService giveBackService,
                                 StatusAllStationsService statusAllStationsService) {

        this.bikeStationRepository = bikeStationRepository;
        this.borrowService = borrowService;
        this.giveBackService = giveBackService;
        this.statusAllStationsService = statusAllStationsService;
    }

//////////////////////////////////////////////////////////////////

    @GetMapping("/stations")
    public List<StatusStation> showAllBikeStations() {

        return statusAllStationsService.build();
    }


    @GetMapping("/stations/{id}")
    public BikeStation showBikeStationById(@PathVariable Long id) throws BikeStationNotFoundException {

        Optional<BikeStation> bikeStation = bikeStationRepository.findById(id);

        // if the BikeStation hasn't been found in DB
        if (!bikeStation.isPresent()) {
            throw new BikeStationNotFoundException("BikeStation (id: " + id + ") - not found");
        }

        return bikeStation.get();
    }


    @PostMapping("/stations")
    public ResponseEntity<Object> createBikeStation(@RequestBody BikeStation bikeStation) {

        BikeStation savedBikeStation = bikeStationRepository.save(bikeStation);

        URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(savedBikeStation.getId())
                            .toUri();

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/stations/{id}")
    public void deleteBikeStation(@PathVariable Long id) {

        bikeStationRepository.deleteById(id);
    }


    // Update the whole Station
    @PutMapping("/stations/{idStation}")
    public ResponseEntity<Object> updateBikeStation(@RequestBody BikeStation bikeStation,
                                                    @PathVariable Long idStation) throws BikeStationNotFoundException {

        Optional<BikeStation> bikeStationOptional = bikeStationRepository.findById(idStation);

        if(!bikeStationOptional.isPresent()) {

            // if the BikeStation hasn't been found in DB
            throw new BikeStationNotFoundException("BikeStation (id: " + idStation + ") not found");
        }

        bikeStation.setId(idStation);

        bikeStationRepository.save(bikeStation);

        return ResponseEntity.ok().body("Update - successful");
    }


    // Update - "renting a bike"
    @PatchMapping("/stations/borrow/{idStation}/{idUser}/{serialNumBike}")
    public ResponseEntity<Object> borrowBike(@PathVariable Long idStation,
                                             @PathVariable Long idUser,
                                             @PathVariable String serialNumBike) throws BikeStationNotFoundException,
                                                                                        UserTempNotFoundException {
        borrowService.borrow(idStation, idUser, serialNumBike);

        return ResponseEntity.ok().body("User takes a bike for a ride.");
    }


    // Update - "returning a bike"
    @PatchMapping("/stations/gback/{idStation}/{idUser}")
    public ResponseEntity<Object> giveBackBike(@PathVariable Long idStation,
                                               @PathVariable Long idUser) throws BikeStationNotFoundException,
                                                                                    UserTempNotFoundException {
        giveBackService.giveBack(idStation, idUser);

        return ResponseEntity.ok().body("User returns a bike from a ride.");
    }
}
