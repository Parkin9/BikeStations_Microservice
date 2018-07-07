package pl.parkin9.BikeStations_Microservice.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BikeStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "BIKE_STATION_ID"))
    private Set<Bike> bikeSet = new HashSet<>();

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "BIKE_STATION_ID"))
    private Set<Slot> slotSet = new HashSet<>();

//////////////////////////////////////////////////////////////

    public BikeStation() {
    }

    public BikeStation(String name, Set<Bike> bikeSet, Set<Slot> slotSet) {
        this.name = name;
        this.bikeSet = bikeSet;
        this.slotSet = slotSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Bike> getBikeSet() {
        return bikeSet;
    }

    public void setBikeSet(Set<Bike> bikeSet) {
        this.bikeSet = bikeSet;
    }

    public Set<Slot> getSlotSet() {
        return slotSet;
    }

    public void setSlotSet(Set<Slot> slotSet) {
        this.slotSet = slotSet;
    }

    @Override
    public String toString() {
        return "BikeStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bikeSet=" + bikeSet +
                ", slotSet=" + slotSet +
                '}';
    }
}
