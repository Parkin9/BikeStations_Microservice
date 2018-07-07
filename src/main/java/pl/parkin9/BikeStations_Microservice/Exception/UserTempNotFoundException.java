package pl.parkin9.BikeStations_Microservice.Exception;

public class UserTempNotFoundException extends Exception {

    public UserTempNotFoundException() {
        super();
    }

    public UserTempNotFoundException(String message) {
        super(message);
    }

    public UserTempNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserTempNotFoundException(Throwable cause) {
        super(cause);
    }
}
