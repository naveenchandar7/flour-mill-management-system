package org.example.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ElectricityUsage {

    private int usageId;
    private Date usageDate;
    private double startUnit;
    private double endUnit;
    private double unitsUsed;
    private double ratePerUnit;
    private double electricityCost;
    private Timestamp createdAt;


    public ElectricityUsage(int usageId,
                            Date usageDate,
                            double startUnit,
                            double endUnit,
                            double unitsUsed,
                            double ratePerUnit,
                            double electricityCost,
                            Timestamp createdAt) {

        this.usageId = usageId;
        this.usageDate = usageDate;
        this.startUnit = startUnit;
        this.endUnit = endUnit;
        this.unitsUsed = unitsUsed;
        this.ratePerUnit = ratePerUnit;
        this.electricityCost = electricityCost;
        this.createdAt = createdAt;
    }


    public int getUsageId() {
        return usageId;
    }

    public void setUsageId(int usageId) {
        this.usageId = usageId;
    }


    public Date getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(Date usageDate) {
        this.usageDate = usageDate;
    }
    public double getStartUnit() {
        return startUnit;
    }

    public void setStartUnit(double startUnit) {
        this.startUnit = startUnit;
    }

    public double getEndUnit() {
        return endUnit;
    }

    public void setEndUnit(double endUnit) {
        this.endUnit = endUnit;
    }


    public double getUnitsUsed() {
        return unitsUsed;
    }

    public void setUnitsUsed(double unitsUsed) {
        this.unitsUsed = unitsUsed;
    }


    public double getRatePerUnit() {
        return ratePerUnit;
    }

    public void setRatePerUnit(double ratePerUnit) {
        this.ratePerUnit = ratePerUnit;
    }


    public double getElectricityCost() {
        return electricityCost;
    }

    public void setElectricityCost(double electricityCost) {
        this.electricityCost = electricityCost;
    }


    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}