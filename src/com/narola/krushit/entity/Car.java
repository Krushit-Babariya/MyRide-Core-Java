package com.narola.krushit.entity;

public abstract class Car extends Vehicle {
    private String groundClearance;

    public Car() {
    }

    public Car(String vehicleType, String fuelType, String groundClearance) {
        super(vehicleType, fuelType);
        this.groundClearance = groundClearance;
    }
}
