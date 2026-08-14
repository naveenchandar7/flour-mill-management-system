package org.example.model;

public class BillItem {
    private int billItemId;
    private int billId;
    private int serviceId;
    private double quantity;
    private double rate;
    private double amount;

    public BillItem(int billItemId, int billId, int serviceId,
                    double quantity, double rate, double amount) {

        this.billItemId = billItemId;
        this.billId = billId;
        this.serviceId = serviceId;
        this.quantity = quantity;
        this.rate = rate;
        this.amount = amount;
    }
    public int getBillItemId(){
        return billItemId;
    }
    public void setBillItemId(int billItemId){
        this.billItemId=billItemId;
    }
    public int getBillId(){
        return billId;
    }
    public void setBillId(int billId){
        this.billId=billId;
    }
    public int getServiceId(){
        return serviceId;
    }
    public void setServiceId(int serviceId){
        this.serviceId=serviceId;
    }
    public double getQuantity(){
        return quantity;
    }
    public void setQuantity(double quantity){
        this.quantity=quantity;
    }
    public double getRate(){
        return rate;
    }
    public void setRate(double rate){
        this.rate=rate;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double calculateAmount() {
        return quantity * rate;
    }
}
