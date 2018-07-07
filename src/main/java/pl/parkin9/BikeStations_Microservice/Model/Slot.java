package pl.parkin9.BikeStations_Microservice.Model;


import javax.persistence.*;

@Embeddable
public class Slot {

    private Boolean free;

/////////////////////////////////////////////////////////

    public Slot() {
    }

    public Slot(Boolean free) {
        this.free = free;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "free=" + free +
                '}';
    }
}
