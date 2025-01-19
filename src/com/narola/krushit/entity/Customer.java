package com.narola.krushit.entity;

import java.math.BigInteger;

public class Customer extends User {

    public Customer(String firstName, String lastLame, BigInteger phoneNo, String emailID) {
        super(firstName, lastLame, phoneNo, emailID);
    }

    @Override
    public void displayUserRole() {
        System.out.println("Role: Customer");
    }

    @Override
    public String getUserDetails() {
        return "Driver Details: " +
                "Name: " + getFirstName() + " " + getLastName() +
                ", Phone: " + getPhoneNo();
    }

    @Override
    public String toString() {
        return "Customer [" +
                "Customer ID=" + getUserID() +
                ", First Name='" + getFirstName() + '\'' +
                ", Last Lame='" + getLastName() + '\'' +
                ", Phone No='" + getPhoneNo() + '\'' +
                ", Email ID='" + getEmailID() + '\'' +
                "]";
    }

}
