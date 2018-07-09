package pl.parkin9.BikeStations_Microservice.Service;

import org.springframework.stereotype.Component;
import pl.parkin9.BikeStations_Microservice.Model.BikeStation;
import pl.parkin9.BikeStations_Microservice.Model.Slot;

import java.util.HashSet;
import java.util.Set;

@Component
public class SwitchSlotImpl implements SwitchSlot {

    public void switchFalseToTrue(BikeStation fromBikeStation) {

        Set<Slot> slotSet = new HashSet<>(fromBikeStation.getSlotSet());

        for(Slot slot : slotSet) {

            if(slot.getFree().equals(false)) {

                slot.setFree(true);
                break;
            }
        }
    }


    public void switchTrueToFalse(BikeStation toBikeStation) {

        Set<Slot> slotSet = new HashSet<>(toBikeStation.getSlotSet());

        for(Slot slot : slotSet) {

            if(slot.getFree().equals(true)) {

                slot.setFree(false);
                break;
            }
        }
    }
}
