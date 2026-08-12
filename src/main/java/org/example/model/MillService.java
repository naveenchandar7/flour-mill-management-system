package org.example.model;

public class MillService {
    private int serviceId;
    private String serviceName;
    private double ratePerKg;

    public MillService(int serviceId,String serviceName,double ratePerKg){
        this.serviceId=serviceId;
        this.serviceName=serviceName;
        this.ratePerKg=ratePerKg;
    }
    public int getServiceId(){
        return serviceId;
    }
    public void setServiceId(int serviceId){
        this.serviceId=serviceId;
    }
    public String getServiceName(){
        return serviceName;
    }
    public void setServiceName(String serviceName){
        this.serviceName=serviceName;
    }
    public double getRatePerKg(){
        return ratePerKg;
    }
    public void setRatePerKg(double ratePerKg){
        this.ratePerKg=ratePerKg;
    }
}
