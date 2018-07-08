package pl.parkin9.BikeStations_Microservice.Model;

public class StatusStation {

    private String name;
    private Long freeSlot;
    private Long occupiedSlot;
    private Long availableBike;

    public StatusStation(String name, Long freeSlot, Long occupiedSlot, Long availableBike) {
        this.name = name;
        this.freeSlot = freeSlot;
        this.occupiedSlot = occupiedSlot;
        this.availableBike = availableBike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFreeSlot() {
        return freeSlot;
    }

    public void setFreeSlot(Long freeSlot) {
        this.freeSlot = freeSlot;
    }

    public Long getOccupiedSlot() {
        return occupiedSlot;
    }

    public void setOccupiedSlot(Long occupiedSlot) {
        this.occupiedSlot = occupiedSlot;
    }

    public Long getAvailableBike() {
        return availableBike;
    }

    public void setAvailableBike(Long availableBike) {
        this.availableBike = availableBike;
    }

    @Override
    public String toString() {
        return "StatusStation{" +
                "name='" + name + '\'' +
                ", freeSlot=" + freeSlot +
                ", occupiedSlot=" + occupiedSlot +
                ", availableBike=" + availableBike +
                '}';
    }
}
