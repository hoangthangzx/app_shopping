package com.example.appshopping.model;

import java.util.List;

public class sanphammoiModel {
    boolean success;
    String message;
    List<sanphammoi> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<sanphammoi> getResult() {
        return result;
    }

    public void setResult(List<sanphammoi> result) {
        this.result = result;
    }
}
