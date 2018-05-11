package de.dis2011.data;

import javafx.beans.property.SimpleStringProperty;

public class Person {

    private int estateID = -1;
    private String firstName;
    private String name;
    private String address;

    public int getEstateID() {
        return estateID;
    }

    public void setEstateID(int estateID) {
        this.estateID = estateID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Person createPerson(String firstN, String name, String address){
        Person p = new Person();
        p.setFirstName(firstN);
        p.setName(name);
        p.setAddress(address);
        return p;
    }

    @Override
    public String toString(){
        return getName() + ", " + getFirstName() + ", " + getAddress();
    }
}
