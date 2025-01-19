package com.narola.krushit.entity;

import com.narola.krushit.commons.StatusConstant;

import java.time.LocalDate;
import java.time.LocalTime;

public class RideRequest {
    private String rideRequestStatus = StatusConstant.RIDE_REQUEST_STATUS_PENDING;
    private String pickUpLocation;
    private String dropOffLocation;
    private Customer customer;

    public void setRideRequestStatus(String rideRequestStatus) {
        this.rideRequestStatus = rideRequestStatus;
    }

    private LocalDate rideRequestDate;
    private LocalTime pickUpTime;
    private LocalTime dropOffTime;
    private String vehicleType;
    private int capacity;

    private RideRequest(Builder builder) {
        this.rideRequestStatus = builder.rideRequestStatus;
        this.pickUpLocation = builder.pickUpLocation;
        this.dropOffLocation = builder.dropOffLocation;
        this.customer = builder.customer;
        this.rideRequestDate = builder.rideRequestDate;
        this.pickUpTime = builder.pickUpTime;
        this.dropOffTime = builder.dropOffTime;
        this.vehicleType = builder.vehicleType;
        this.capacity = builder.capacity;
    }

    public String getRideRequestStatus() {
        return rideRequestStatus;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getRideRequestDate() {
        return rideRequestDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public LocalTime getDropOffTime() {
        return dropOffTime;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getCapacity() {
        return capacity;
    }

    public static class Builder {
        private String rideRequestStatus = "Pending";
        private String pickUpLocation;
        private String dropOffLocation;
        private Customer customer;
        private LocalDate rideRequestDate;
        private LocalTime pickUpTime;
        private LocalTime dropOffTime;
        private String vehicleType;
        private int capacity;

        public Builder setRideRequestStatus(String rideRequestStatus) {
            this.rideRequestStatus = rideRequestStatus;
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

        public Builder setRideRequestDate(LocalDate rideRequestDate) {
            this.rideRequestDate = rideRequestDate;
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

        public Builder setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Builder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public RideRequest build() {
            return new RideRequest(this);
        }
    }

    @Override
    public String toString() {
        return "RideRequest [rideRequestStatus=" + rideRequestStatus
                + ", pickUpLocation=" + pickUpLocation + ", dropOffLocation=" + dropOffLocation + ", customer="
                + customer + ", rideRequestDate=" + rideRequestDate + ", pickUpTime="
                + pickUpTime + ", dropOffTime=" + dropOffTime + ", vehicleType=" + vehicleType + ", capacity=" + capacity + "]";
    }
}
