package com.narola.krushit.entity;

public class FareDetails {
    private double rate;
    private double distance;

    public FareDetails(double rate, double distance) {
        this.rate = rate;
        this.distance = distance;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
