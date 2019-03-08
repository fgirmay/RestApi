package com.interview.datasetapi.model.answer;

import java.util.List;

public class Answer {

    private List<DealerAnswer> dealers = null;

    public Answer(List<DealerAnswer> dealers) {
        this.dealers = dealers;
    }

    public Answer() {
    }

    public List<DealerAnswer> getDealers() {
        return dealers;
    }

    public void setDealers(List<DealerAnswer> dealers) {
        this.dealers = dealers;
    }
}
