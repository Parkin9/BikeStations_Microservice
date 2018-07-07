package pl.parkin9.BikeStations_Microservice.Exception;

public class BikeStationNotFoundException extends Exception {

    public BikeStationNotFoundException() {
        super();
    }

    public BikeStationNotFoundException(String message) {
        super(message);
    }

    public BikeStationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BikeStationNotFoundException(Throwable cause) {
        super(cause);
    }
}
