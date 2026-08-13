package org.example.model;

public class Bill {
    private int billId;
    private int customerId;
    private double totalAmount;

    public Bill(int billId, int customerId, double totalAmount) {
        this.billId = billId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
    }

    public int getBillId() {
        return billId;
    }
    public void setBillId(int billId) {
        this.billId = billId;
    }
    public int getCustomerId(){
        return customerId;
    }
    public void setCustomerId(int customerId){
        this.customerId=customerId;
    }
    public double getTotalAmount(){
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount){
        this.totalAmount=totalAmount;
    }
}
