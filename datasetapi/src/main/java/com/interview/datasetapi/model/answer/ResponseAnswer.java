package com.interview.datasetapi.model.answer;

public class ResponseAnswer {

    private Boolean success;
    private String message;
    private Integer totalMilliseconds;

    public ResponseAnswer(Boolean success, String message, Integer totalMilliseconds) {
        this.success = success;
        this.message = message;
        this.totalMilliseconds = totalMilliseconds;
    }

    public ResponseAnswer() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotalMilliseconds() {
        return totalMilliseconds;
    }

    public void setTotalMilliseconds(Integer totalMilliseconds) {
        this.totalMilliseconds = totalMilliseconds;
    }
}
