package com.interview.datasetapi.service;

import com.interview.datasetapi.model.Dataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataSetService {

    @Autowired
    private ApiService apiService;



    public DataSetService() {
    }

    public Dataset getDataSet() {

        Dataset dataset = apiService.getDataSet();

        return dataset;
    }

}
