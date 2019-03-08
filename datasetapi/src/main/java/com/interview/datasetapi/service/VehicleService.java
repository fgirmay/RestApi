package com.interview.datasetapi.service;

import com.interview.datasetapi.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private ApiService apiService;



    public VehicleService() {
    }

    public List<Vehicle> getVehicles(String datasetId) {

        List<Vehicle> vehicles = apiService.getVehicles(datasetId);

        return vehicles;
    }

    public Vehicle getVehicle(String datasetId, int vehicleId) {

        return apiService.getVehicle(datasetId, vehicleId);
    }
}
