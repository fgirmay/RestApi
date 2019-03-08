package com.interview.datasetapi.service;

import com.interview.datasetapi.model.*;
import com.interview.datasetapi.model.answer.Answer;
import com.interview.datasetapi.model.answer.DealerAnswer;
import com.interview.datasetapi.model.answer.ResponseAnswer;
import com.interview.datasetapi.model.answer.VehicleAnswer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class ApiService {

    ExecutorService executor = Executors.newFixedThreadPool(3);

    private final static String baseUrl = "http://vautointerview.azurewebsites.net/api/";

    private final RestTemplate restTemplate;

    private List<Integer> vehicleIds;

    private List<Integer> dealerIds;

    private List<Dealer> dealers;
    List<Future<Vehicle>> vehicleFutures;
    List<Future<Dealer>> dealerFutures;

    private List<Vehicle> vehicles;

    private List<DealerAnswer> dealerAnswers;

    private Map<String, Answer> answerMap = new HashMap<>();

    public ApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Dataset getDataSet() {

        String url = baseUrl + "datasetId";
        Dataset dataset = this.restTemplate.getForObject(url, Dataset.class);

        return dataset;
    }

    private List<Integer> getVehiclesIds(String datasetId) {

        String url = baseUrl + datasetId + "/vehicles";

        VehiclesResponse vehiclesResponse = restTemplate.getForObject(url, VehiclesResponse.class);
        vehicleIds = vehiclesResponse.getVehicleIds();

        return vehicleIds;
    }

    public List<Vehicle> getVehicles(String datasetId) {

        List<Integer> vehiclesIds = getVehiclesIds(datasetId);
        vehicles = new ArrayList<>();

        Vehicle vehicle;

        for (int vehicleId : vehiclesIds) {

            vehicle = getVehicle(datasetId, vehicleId);

            vehicles.add(vehicle);
        }

        return vehicles;
    }


    public Vehicle getVehicle(String datasetId, int vehicleId) {

        String url = baseUrl + datasetId + "/vehicles/" + vehicleId;
        Vehicle vehicle = restTemplate.getForObject(url, Vehicle.class);

        return vehicle;
    }

    private List<Integer> getDealerIdsFromVehicles(List<Vehicle> vehicles) {

        dealerIds = new ArrayList<>();
        int dealerId;

        for (Vehicle vehicle : vehicles) {

            dealerId = vehicle.getDealerId();

            if (!dealerIds.contains(dealerId)) {
                dealerIds.add(dealerId);
            }

        }

        return dealerIds;
    }

    public List<Dealer> getDealersFromVehicles(String datasetId) {

        dealers = new ArrayList<>();

        if (vehicles == null) {
            vehicles = getVehicles(datasetId);
        }

        List<Integer> dealerIds = getDealerIdsFromVehicles(vehicles);
        Dealer dealer;

        for (int dealerId : dealerIds) {
            dealer = getDealer(datasetId, dealerId);
            dealers.add(dealer);
        }

        return dealers;

    }

    public Dealer getDealer(String datasetId, int dealerId) {

        String url = baseUrl + datasetId + "/dealers/" + dealerId;

        Dealer dealer = restTemplate.getForObject(url, Dealer.class);

        return dealer;

    }

    public Answer populateDealerAnswer(String datasetId) {

        Answer answer = answerMap.get(datasetId);

        if (answer != null) {
            return answer;

        } else {

            dealerAnswers = new ArrayList<>();
            getVehiclesAsync(datasetId);

            vehicles = new ArrayList<>();

            for(Future<Vehicle> future: vehicleFutures) {

                try {
                    vehicles.add(future.get());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            //dealers = getDealersFromVehicles(datasetId);
            getDealersFromVehiclesAsync(datasetId);
            dealers = new ArrayList<>();

            for(Future<Dealer> future: dealerFutures) {

                try {
                    dealers.add(future.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            for (Dealer dealer : dealers) {

                DealerAnswer dealerAnswer = new DealerAnswer();

                dealerAnswer.setDealerId(dealer.getDealerId());
                dealerAnswer.setName(dealer.getName());

                for (Vehicle vehicle : vehicles) {

                    VehicleAnswer vehicleAnswer = new VehicleAnswer();

                    vehicleAnswer.setVehicleId(vehicle.getVehicleId());
                    vehicleAnswer.setYear(vehicle.getYear());
                    vehicleAnswer.setMake(vehicle.getMake());
                    vehicleAnswer.setModel(vehicle.getModel());

                    if (vehicle.getDealerId() == dealer.getDealerId()) {
                        dealerAnswer.addVehicleAnswer(vehicleAnswer);
                    }
                }
                dealerAnswers.add(dealerAnswer);
            }

            answer = new Answer(dealerAnswers);
            answerMap.put(datasetId, answer);
        }

        return answer;

    }

    public void getVehiclesAsync(String datasetId) {

        List<Integer> vehiclesIds = getVehiclesIds(datasetId);
        vehicles = new ArrayList<>();
        vehicleFutures = new ArrayList<>();

        for (int vehicleId : vehiclesIds) {

            vehicleFutures.add(executor.submit(new Callable<Vehicle>() {
                public Vehicle call() throws IOException {
                    return getVehicle(datasetId, vehicleId);
                }
            }));

        }
    }

    public void getDealersFromVehiclesAsync(String datasetId) {

        dealers = new ArrayList<>();
        dealerFutures = new ArrayList<>();

        if (vehicles == null) {
            return;
        }

        List<Integer> dealerIds = getDealerIdsFromVehicles(vehicles);

        for (int dealerId : dealerIds) {
            dealerFutures.add(executor.submit(new Callable<Dealer>() {
                public Dealer call() throws IOException {
                    return getDealer(datasetId, dealerId);
                }
            }));
        }

    }

    public ResponseAnswer postAnswer(String datasetId) {

        Answer answer = populateDealerAnswer(datasetId);

        String url = baseUrl + datasetId + "/answer";

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Answer> request = new HttpEntity<>(answer, requestHeaders);
        ResponseEntity<ResponseAnswer> responseAnswer = restTemplate.postForEntity(url, request, ResponseAnswer.class);

        return responseAnswer.getBody();
    }
}
