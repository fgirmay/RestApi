package com.interview.datasetapi.model.answer;

import java.util.ArrayList;
import java.util.List;

public class DealerAnswer {

    private Integer dealerId = null;
    private String name = null;
    private List<VehicleAnswer> vehicles = new ArrayList<>();

    public DealerAnswer() {
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VehicleAnswer> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleAnswer> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicleAnswer(VehicleAnswer vehicleAnswer) {

        vehicles.add(vehicleAnswer);
    }
}
