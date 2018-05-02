package de.dis2011.data;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PurchaseContract extends Contract{

    private int contractNumber = -1;
    private SimpleIntegerProperty numberOfInstallments = new SimpleIntegerProperty(-1);
    private SimpleDoubleProperty interestRate = new SimpleDoubleProperty(-1);
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
        return numberOfInstallments.get();
    }

    public SimpleIntegerProperty numberOfInstallmentsProperty() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(int numberOfInstallments) {
        this.numberOfInstallments.set(numberOfInstallments);
    }

    public double getInterestRate() {
        return interestRate.get();
    }

    public SimpleDoubleProperty interestRateProperty() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate.set(interestRate);
    }

    public int getHouseID() {
        return houseID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }
}
