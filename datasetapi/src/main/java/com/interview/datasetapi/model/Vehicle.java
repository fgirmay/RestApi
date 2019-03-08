package com.interview.datasetapi.model;

public class Vehicle {

    public Vehicle() {
    }

    public Vehicle(int year, String make, String model, int vehicleId, int dealerId) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleId = vehicleId;
        this.dealerId = dealerId;
    }

    private int vehicleId;
    private int year;
    private String make;
    private String model;
    private int dealerId;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public int getDealerId() {
        return dealerId;
    }

    public void setDealerId(int dealerId) {
        this.dealerId = dealerId;
    }
}
