package com.narola.krushit.entity;

public class Bike extends Vehicle {
    private final int capacity = 1;

    public Bike() {
    }

    public Bike(String vehicleType, String fuelType) {
        super(vehicleType, fuelType);
    }

    @Override
    public double calculateFare(double distance) {
        if (distance < 3) {
            return distance * 5 * 1.5;
        }
        return distance * 3;
    }

    @Override
    public String toString() {
        return super.toString() + ", Bike{" +
                ", capacity=" + capacity +
                '}';
    }
}
