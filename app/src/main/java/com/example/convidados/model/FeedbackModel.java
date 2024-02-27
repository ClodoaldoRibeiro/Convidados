package com.example.convidados.model;

public class FeedbackModel {
    private boolean mSuccess = true;
    private String mMessage = "";

    public FeedbackModel(String mMessage) {
        this.mMessage = mMessage;
    }

    public FeedbackModel(boolean mSuccess, String mMessage) {
        this.mSuccess = mSuccess;
        this.mMessage = mMessage;
    }

    public boolean isSuccess() {
        return mSuccess;
    }

    public String getMessage() {
        return mMessage;
    }
}
