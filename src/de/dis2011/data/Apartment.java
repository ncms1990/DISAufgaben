package de.dis2011.data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Apartment extends Estate {
    private SimpleIntegerProperty floor = new SimpleIntegerProperty(0);
    private SimpleDoubleProperty rent = new SimpleDoubleProperty(0);
    private SimpleIntegerProperty rooms = new SimpleIntegerProperty(0);
    private SimpleStringProperty balcony = new SimpleStringProperty("");
    private SimpleStringProperty builtInKitchen = new SimpleStringProperty("");
    private SimpleIntegerProperty renter = new SimpleIntegerProperty(0);

    public int getFloor() {
        return floor.get();
    }

    public SimpleIntegerProperty floorProperty() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor.set(floor);
    }

    public double getRent() {
        return rent.get();
    }

    public SimpleDoubleProperty rentProperty() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent.set(rent);
    }

    public int getRooms() {
        return rooms.get();
    }

    public SimpleIntegerProperty roomsProperty() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms.set(rooms);
    }

    public String getBalcony() {
        return balcony.get();
    }

    public SimpleStringProperty balconyProperty() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony.set(balcony);
    }

    public String getBuiltInKitchen() {
        return builtInKitchen.get();
    }

    public SimpleStringProperty builtInKitchenProperty() {
        return builtInKitchen;
    }

    public void setBuiltInKitchen(String builtInKitchen) {
        this.builtInKitchen.set(builtInKitchen);
    }

    public int getRenter() {
        return renter.get();
    }

    public SimpleIntegerProperty renterProperty() {
        return renter;
    }

    public void setRenter(int renter) {
        this.renter.set(renter);
    }
}
