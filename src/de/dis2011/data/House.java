package de.dis2011.data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class House extends Estate{

    private int estateID = -1;
    private SimpleIntegerProperty floors = new SimpleIntegerProperty(0);
    private SimpleDoubleProperty price = new SimpleDoubleProperty(0);
    private SimpleStringProperty garden = new SimpleStringProperty("");
    private int ownerID = -1;

    public int getEstateID() {
        return estateID;
    }

    public void setEstateID(int estateID) {
        this.estateID = estateID;
    }

    public int getFloors() {
        return floors.get();
    }

    public SimpleIntegerProperty floorsProperty() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors.set(floors);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getGarden() {
        return garden.get();
    }

    public SimpleStringProperty gardenProperty() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden.set(garden);
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
}
