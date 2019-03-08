package com.interview.datasetapi.model;

public class Dataset {

    private String datasetId;

    public Dataset() {
    }

    public Dataset(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }
}
