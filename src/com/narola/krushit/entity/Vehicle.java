package com.narola.krushit.entity;

public abstract class Vehicle {
    private String vehicleType;
    private String fuelType;

    public Vehicle() {
    }

    public Vehicle(String vehicleType, String fuelType) {
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                ", vehicleType='" + vehicleType + '\'' +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }

    public abstract double calculateFare(double distance);
}
