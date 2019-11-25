package com.example.sendcard.DTO;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Sent {
    @Id private long id;
    private String phone;
    private String request;
    private String response;

    public Sent() {
    }

    public Sent(long id, String phone, String request, String response) {
        this.id = id;
        this.phone = phone;
        this.request = request;
        this.response = response;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}