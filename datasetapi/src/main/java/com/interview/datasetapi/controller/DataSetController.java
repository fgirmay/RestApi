package com.interview.datasetapi.controller;

import com.interview.datasetapi.model.Dataset;
import com.interview.datasetapi.model.Dealer;
import com.interview.datasetapi.model.Vehicle;
import com.interview.datasetapi.model.answer.Answer;
import com.interview.datasetapi.model.answer.ResponseAnswer;
import com.interview.datasetapi.service.DataSetService;
import com.interview.datasetapi.service.DealerService;
import com.interview.datasetapi.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataSetController {

    @Autowired
    private DataSetService dataSetService;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private DealerService dealerService;

    private Dataset dataset;


    @GetMapping("api/{datasetId}/vehicles/{vehicleId}")
    public Vehicle getVehicle(@PathVariable String datasetId, @PathVariable int vehicleId) {

        Vehicle vehicle = vehicleService.getVehicle(datasetId, vehicleId);

        return vehicle;
    }

    @GetMapping("api/{datasetId}/vehicles")
    public List<Vehicle> getVehicle(@PathVariable String datasetId) {

        List<Vehicle> vehicles = vehicleService.getVehicles(datasetId);

        return vehicles;
    }

    @GetMapping("api/{datasetId}/dealers")
    public List<Dealer> getDealer(@PathVariable String datasetId) {

        List<Dealer> dealers = dealerService.getDealers(datasetId);

        return dealers;
    }

    @PostMapping("api/{datasetId}/answer")
    public ResponseAnswer postAnswer(@PathVariable String datasetId) {

        ResponseAnswer responseAnswer = dealerService.postAnswer(datasetId);

        return responseAnswer;
    }

    @GetMapping("api/{datasetId}/answer")
    public Answer populateDealerAnswer(@PathVariable String datasetId) {

        Answer answer = dealerService.getAnswers(datasetId);

        return answer;
    }

    @GetMapping("/api/datasetId")
    public ResponseAnswer getAnswer() {

        dataset = dataSetService.getDataSet();

        ResponseAnswer responseAnswer = dealerService.postAnswer(dataset.getDatasetId());

        return responseAnswer;

    }

}
