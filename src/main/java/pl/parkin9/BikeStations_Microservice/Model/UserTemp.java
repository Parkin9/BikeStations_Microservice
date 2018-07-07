package pl.parkin9.BikeStations_Microservice.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private Bike rentBike;

/////////////////////////////////////////////////////////////

    public UserTemp() {
    }

    public UserTemp(String userName, Bike rentBike) {
        this.userName = userName;
        this.rentBike = rentBike;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Bike getRentBike() {
        return rentBike;
    }

    public void setRentBike(Bike rentBike) {
        this.rentBike = rentBike;
    }

    @Override
    public String toString() {
        return "UserTemp{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", rentBike=" + rentBike +
                '}';
    }
}
