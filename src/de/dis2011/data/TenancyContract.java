package de.dis2011.data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class TenancyContract extends Contract {

    private int contractNumber = -1;
    private String startDate;
    private String duration;
    private Double additionalCost;
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
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(Double additionalCost) {
        this.additionalCost = additionalCost;
    }

    public int getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(int apartmentID) {
        this.apartmentID = apartmentID;
    }

    @Override
    public String toString(){
        return "Tenacy: " + super.toString();
    }
}
