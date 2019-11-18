package com.example.sendcard.DTO;

public class Phone {
    private long id;
    private String phone;

    public Phone() {
    }

    public Phone(long id, String phone) {
        this.id = id;
        this.phone = phone;
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
}