package com.narola.krushit.entity;

public class SuvCar extends Car {
    public final int capacity = 6;
    private char trunkSize;
    private int towingCapacity;
    private double grossVehicleWeight; //in kgs

    public SuvCar() {
    }

    public SuvCar(String vehicleType, String fuelType, char trunkSize, int towingCapacity, String groundClearance) {
        super(vehicleType, fuelType, groundClearance);
        this.trunkSize = trunkSize;
        this.towingCapacity = towingCapacity;
    }

    @Override
    public double calculateFare(double distance) {
        double fare = distance * 10;
        if (grossVehicleWeight > 2000) {
            if(distance > 200){
                fare += 10 * distance;
            }
            fare += 200;
        }
        return fare;
    }

    @Override
    public String toString() {
        return super.toString() + "SuvCar{" +
                "Capacity =" + capacity +
                '}';
    }
}