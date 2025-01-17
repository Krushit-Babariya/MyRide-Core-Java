package com.narola.krushit;

public class Bike extends Vehicle {
    private final int capacity = 1;

    public Bike() {
    }

    public Bike(int vehicleID, String vehicleType, String fuelType) {
        super(vehicleID, vehicleType, fuelType);
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
