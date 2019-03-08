package com.interview.datasetapi.service;

import com.interview.datasetapi.model.Dealer;
import com.interview.datasetapi.model.answer.Answer;
import com.interview.datasetapi.model.answer.DealerAnswer;
import com.interview.datasetapi.model.answer.ResponseAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerService {

    @Autowired
    private ApiService apiService;



    public DealerService() {
    }

    public List<Dealer> getDealers(String datasetId) {

        List<Dealer> dealers = apiService.getDealersFromVehicles(datasetId);

        return dealers;
    }

    public Dealer getDealer(String datasetId, int dealerId) {

        return apiService.getDealer(datasetId, dealerId);
    }

    public ResponseAnswer postAnswer(String datasetId) {

        return apiService.postAnswer(datasetId);
    }

    public Answer getAnswers(String datasetId) {

        Answer answer = apiService.populateDealerAnswer(datasetId);

        return answer;
    }
}
