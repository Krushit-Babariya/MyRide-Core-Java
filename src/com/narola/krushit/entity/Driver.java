package com.narola.krushit.entity;

import java.math.BigInteger;

public class Driver extends User {
    private String licenseNumber;
    private boolean available;
    private Vehicle vehicle;
    private boolean isLastTimeRejected;

    public boolean isLastTimeRejected() {
        return isLastTimeRejected;
    }

    public void setLastTimeRejected(boolean lastTimeRejected) {
        isLastTimeRejected = lastTimeRejected;
    }

    public Driver(String firstName, String lastLame, BigInteger phoneNo, String emailID, String licenseNumber) {
        super(firstName, lastLame, phoneNo, emailID);
        this.available = true;
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void acceptRide() {
        System.out.println(getFirstName() + " accepted the ride.");
        this.available = false;
    }

    public void completeRide() {
        System.out.println(getFirstName() + " completed the ride.");
        this.available = true;
    }

    public void addVehical(Vehicle vehicle){
        this.setVehicle(vehicle);
        System.out.println("Vehicle added successfully...!!");
    }

    @Override
    public String toString() {
        return "Driver [" +
                " Driver ID='" + getUserID() + '\'' +
                ", Driver First Name='" + getFirstName() + '\'' +
                ", Driver Last Name='" + getLastName() + '\'' +
                ", License Number='" + licenseNumber + '\'' +
                ", \n\tAvailability =" + available +
                ", Vehicle=" + vehicle +
                ", Phone No=" + getPhoneNo() +
                ", Email id='" + getEmailID() + '\'' +
                " ]";
    }

    @Override
    public void displayUserRole() {
        System.out.println("Role: Driver");
    }

    @Override
    public String getUserDetails() {
        return "Driver Details: " +
                "Name: " + getFirstName() + " " + getLastName() +
                ", Phone: " + getPhoneNo() +
                ", License: " + licenseNumber +
                ", Vehicle: " + vehicle.getVehicleType();
    }

    public boolean requestConfirmation(RideRequest request){
        boolean[] acceptance = {true, false, true, false, true};
        int index = (int) (Math.random() * acceptance.length);

        boolean ans = acceptance[index];
        if(!ans){
            this.setLastTimeRejected(true);
            return false;
        }
        return true;
    }

    public String getVehicleType(Vehicle vehicle) {
        if (vehicle instanceof SedanCar) {
            return "sedan";
        } else if (vehicle instanceof SuvCar) {
            return "suv";
        } else if (vehicle instanceof AutoRickshow) {
            return "auto";
        } else if (vehicle instanceof Bike) {
            return "bike";
        }
        return null;
    }
}