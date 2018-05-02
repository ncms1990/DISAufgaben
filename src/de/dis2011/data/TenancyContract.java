package de.dis2011.data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class TenancyContract extends Contract {

    private int contractNumber = -1;
    private SimpleStringProperty startDate = new SimpleStringProperty("");
    private SimpleStringProperty duration = new SimpleStringProperty("");
    private SimpleDoubleProperty additionalCost = new SimpleDoubleProperty(0);
    private int apartmentID = -1;

    @Override
    public int getContractNumber() {
        return contractNumber;
    }

    @Override
    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getStartDate() {
        return startDate.get();
    }

    public SimpleStringProperty startDateProperty() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public String getDuration() {
        return duration.get();
    }

    public SimpleStringProperty durationProperty() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public double getAdditionalCost() {
        return additionalCost.get();
    }

    public SimpleDoubleProperty additionalCostProperty() {
        return additionalCost;
    }

    public void setAdditionalCost(double additionalCost) {
        this.additionalCost.set(additionalCost);
    }

    public int getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(int apartmentID) {
        this.apartmentID = apartmentID;
    }
}
