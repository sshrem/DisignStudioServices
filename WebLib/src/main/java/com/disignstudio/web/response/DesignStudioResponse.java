package com.disignstudio.web.response;

/**
 * Created by ohadbenporat on 1/9/16.
 */
public class DesignStudioResponse<T> {

    public static final String SUCCESS_MSG = "SUCCESS!";
    private boolean isSuccess;
    private String message;
    private T data;

    public DesignStudioResponse() {
    }

    public DesignStudioResponse(T data){
        this(true,SUCCESS_MSG,data);
    }

    public DesignStudioResponse(boolean isSuccess, String message, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
