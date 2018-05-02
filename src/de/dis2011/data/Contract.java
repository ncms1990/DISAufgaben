package de.dis2011.data;

import javafx.beans.property.SimpleStringProperty;

public class Contract {

    private int contractNumber = -1;
    private SimpleStringProperty place = new SimpleStringProperty("");

    public int getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getPlace() {
        return place.get();
    }

    public SimpleStringProperty placeProperty() {
        return place;
    }

    public void setPlace(String place) {
        this.place.set(place);
    }

}
