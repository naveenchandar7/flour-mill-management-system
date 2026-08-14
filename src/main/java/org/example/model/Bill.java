package org.example.model;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private int billId;
    private int customerId;
    private double totalAmount;

    private ArrayList<BillItem> billItems = new ArrayList<>();

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
    public void addBillItem(BillItem billItem){
        billItems.add(billItem);
    }
    public double calculateTotalAmount(){
        double total=0;
        for (BillItem item:billItems){
            total+= item.calculateAmount();
        }
        return total;
    }
}
