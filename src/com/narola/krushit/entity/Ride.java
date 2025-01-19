package com.narola.krushit.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Ride {
    private int rideID;
    private String rideStatus;
    private String pickUpLocation;
    private String dropOffLocation;
    private Customer customer;
    private Driver driver;
    private LocalDate rideDate;
    private LocalTime pickUpTime;
    private LocalTime dropOffTime;
    private double distance;
    private double totalCost;

    private Ride(Builder builder) {
        this.rideID = builder.rideID;
        this.rideStatus = builder.rideStatus;
        this.pickUpLocation = builder.pickUpLocation;
        this.dropOffLocation = builder.dropOffLocation;
        this.customer = builder.customer;
        this.driver = builder.driver;
        this.rideDate = builder.rideDate;
        this.pickUpTime = builder.pickUpTime;
        this.dropOffTime = builder.dropOffTime;
        this.distance = builder.distance;
        this.totalCost = builder.totalCost;
    }

    public int getRideID() {
        return rideID;
    }

    public void setRideID(int rideID) {
        this.rideID = rideID;
    }

    public String getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(String rideStatus) {
        this.rideStatus = rideStatus;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public LocalDate getRideDate() {
        return rideDate;
    }

    public void setRideDate(LocalDate rideDate) {
        this.rideDate = rideDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public LocalTime getDropOffTime() {
        return dropOffTime;
    }

    public void setDropOffTime(LocalTime dropOffTime) {
        this.dropOffTime = dropOffTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public static class Builder {
        private int rideID;
        private String rideStatus;
        private String pickUpLocation;
        private String dropOffLocation;
        private Customer customer;
        private Driver driver;
        private LocalDate rideDate;
        private LocalTime pickUpTime;
        private LocalTime dropOffTime;
        private double distance;
        private double totalCost;

        public Builder setRideID(int rideID) {
            this.rideID = rideID;
            return this;
        }

        public Builder setRideStatus(String rideStatus) {
            this.rideStatus = rideStatus;
            return this;
        }

        public Builder setPickUpLocation(String pickUpLocation) {
            this.pickUpLocation = pickUpLocation;
            return this;
        }

        public Builder setDropOffLocation(String dropOffLocation) {
            this.dropOffLocation = dropOffLocation;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder setDriver(Driver driver) {
            this.driver = driver;
            return this;
        }

        public Builder setRideDate(LocalDate rideDate) {
            this.rideDate = rideDate;
            return this;
        }

        public Builder setPickUpTime(LocalTime pickUpTime) {
            this.pickUpTime = pickUpTime;
            return this;
        }

        public Builder setDropOffTime(LocalTime dropOffTime) {
            this.dropOffTime = dropOffTime;
            return this;
        }

        public Builder setDistance(double distance) {
            this.distance = distance;
            return this;
        }

        public Builder setTotalCost(double totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public Ride build() {
            return new Ride(this);
        }
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideID=" + rideID +
                ", rideStatus='" + rideStatus + '\'' +
                ", pickUpLocation='" + pickUpLocation + '\'' +
                ", dropOffLocation='" + dropOffLocation + '\'' +
                ", customer=" + customer +
                ", driver=" + driver +
                ", rideDate=" + rideDate +
                ", pickUpTime=" + pickUpTime +
                ", dropOffTime=" + dropOffTime +
                ", distance=" + distance +
                ", totalCost=" + totalCost +
                '}';
    }
}
