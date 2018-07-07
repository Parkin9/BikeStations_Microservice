package pl.parkin9.BikeStations_Microservice.Model;

import javax.persistence.*;

@Embeddable
public class Bike {

    @Column(unique = true)
    private String serialNumber;

//////////////////////////////////////////////////////////////

    public Bike() {
    }

    public Bike(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
