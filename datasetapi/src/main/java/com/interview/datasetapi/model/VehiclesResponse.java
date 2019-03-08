package com.interview.datasetapi.model;

import java.util.List;

public class VehiclesResponse {

    private List<Integer> vehicleIds;

    public VehiclesResponse() {
    }

    public VehiclesResponse(List<Integer> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public List<Integer> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<Integer> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }
}
