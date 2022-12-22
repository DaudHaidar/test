package com.test.demotest.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseData<T> {
    private String status;
    private String message;
    private List<T> data = new ArrayList<T>();


    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return "{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    
}
