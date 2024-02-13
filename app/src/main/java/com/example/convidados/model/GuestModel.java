package com.example.convidados.model;

public class GuestModel {
    public GuestModel(String name, int confirmation) {
        this.name = name;
        this.confirmation = confirmation;
    }

    private String name;
    private int confirmation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }
}
