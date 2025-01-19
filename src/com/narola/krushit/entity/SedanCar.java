
package com.narola.krushit.entity;

public class SedanCar extends Car {
    public final int capacity = 4;
    private String wheelBase;
    private double carLength;

    public SedanCar() {
    }

    public SedanCar(String vehicleType, String fuelType, String groundClearance, String wheelBase, double carLength) {
        super(vehicleType, fuelType, groundClearance);
        this.wheelBase = wheelBase;
        this.carLength = carLength;
    }

    @Override
    public double calculateFare(double distance) {
        double baseRate = 1200;
        double distanceRate = 20;

        double rate = baseRate + (distance * distanceRate);

        if (distance > 50) {
            rate += (carLength > 4.5 ? 250 : 75);
        }
        return rate;
    }

    @Override
    public String toString() {
        return "SedanCar{" +
                "capacity=" + capacity +
                ", wheelBase='" + wheelBase + '\'' +
                ", carLength=" + carLength +
                '}';
    }
}
