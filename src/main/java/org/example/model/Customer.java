package org.example.model;

public class Customer {
    private int customerId;
    private String customerName;
    private String village;
    private String mobile;

   public Customer(int customerId,String customerName,String village,String mobile) {
       this.customerId = customerId;
       this.customerName = customerName;
       this.village = village;
       this.mobile = mobile;
   }

   public int getCustomerId(){         // Get is used to get and return the values
       return customerId;
   }

   public void setCustomerId(int customerId){  //set is used to insert or update the values
       this.customerId=customerId;
   }

   public String getCustomerName(){
       return customerName;
   }

   public void setCustomerName(String customerName){
       this.customerName=customerName;
   }

   public String getMobile(){
       return mobile;
   }

   public void setMobile(String mobile){
       this.mobile=mobile;
   }

   public String getVillage(){
       return village;
   }

   public void setVillage(String village){
       this.village=village;
   }
    @Override
    public String toString() {
        return customerName + " - " + mobile;
    }




}

