package de.dis2011.data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PurchaseContract extends Contract{

    private int contractNumber = -1;
    private int numberOfInstallments;
    private Double interestRate;
    private int houseID = -1;

    @Override
    public int getContractNumber() {
        return contractNumber;
    }

    @Override
    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public int getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public int getHouseID() {
        return houseID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }

    @Override
    public String toString(){
        return "Purchase: " + super.toString();
    }
}
