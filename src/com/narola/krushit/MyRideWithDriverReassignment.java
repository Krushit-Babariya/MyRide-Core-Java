package com.narola.krushit;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MyRideWithDriverReassignment {

    private List<Customer> customerList = new ArrayList<>();
    private List<Driver> driverList = new ArrayList<>();
    private List<RideRequest> rideRequestsList = new ArrayList<>();
    private List<Ride> rideList = new ArrayList<>();
    private List<Route> routeList = new ArrayList<>();

    public MyRideWithDriverReassignment() {
    }

    public static void main(String[] args) {
        MyRideWithDriverReassignment myRide = new MyRideWithDriverReassignment();

        Route route1 = new Route("Surat", "Ahmedabad", 150);
        myRide.addRoute(route1);

        Route route2 = new Route("Mumbai", "Kolkata", 1700);
        myRide.addRoute(route2);

        Driver driver1 = new Driver(1, "Raj", "Raja", new BigInteger("9874563214"), "rajraja@gmail.com", "DL14-0123654787");
        myRide.registerDriver(driver1);
        driver1.setAvailable(false);

        Driver driver2 = new Driver(2, "Shri", "Vastava", new BigInteger("5641239874"), "shri@gmail.com", "GJ05-0123654787");
        myRide.registerDriver(driver2);

        Customer customer1 = new Customer(3, "Rushit", "Bahtiyar", new BigInteger("9876543214"), "rkb@narola.email");
        myRide.registerCustomer(customer1);

        LocalDate rideDate = LocalDate.parse("2025-01-16");
        RideRequest rideRequest = new RideRequest.Builder()
                .setPickUpLocation("Mumbai")
                .setDropOffLocation("Kolkata")
                .setCustomer(customer1)
                .setRideRequestDate(LocalDate.now())
                .setPickUpTime(LocalTime.of(10, 30))
                .setDropOffTime(LocalTime.of(11, 30))
                .setVehicleType("Sedan")
                .setCapacity(4)
                .build();

        System.out.println(rideRequest);

        myRide.rideRequestsList.add(rideRequest);

        Ride ride = myRide.requestForRide(rideRequest);
        if (ride == null) {
            System.out.println("");
            return;
        }
        System.out.println("Ride confirmed successfully!");
        printTicket(ride);
        myRide.rideList.add(ride);
    }

    public void registerDriver(Driver driver) {
        driverList.add(driver);
        System.out.println("Driver Registered Successfully...!!!");
    }

    public void registerCustomer(Customer customer) {
        customerList.add(customer);
        System.out.println("Customer Registered Successfully...!!!");
    }

    public void addRoute(Route route) {
        routeList.add(route);
        System.out.println("Route Added Successfully...!!!");
    }

    private boolean isRouteSupported(RideRequest request) {
        boolean isSupported = false;
        for (Route route : routeList) {
            if (route.getPickUpLocation().equalsIgnoreCase(request.getPickUpLocation()) &&
                    route.getDropOffLocation().equalsIgnoreCase(request.getDropOffLocation())) {
                isSupported = true;
                break;
            }
        }
        return isSupported;
    }

    private double[] tisRouteSupported(RideRequest request) {
        double distance = 0.0;
        boolean isSupported = false;
        for (Route route : routeList) {
            if (route.getPickUpLocation().equalsIgnoreCase(request.getPickUpLocation()) &&
                    route.getDropOffLocation().equalsIgnoreCase(request.getDropOffLocation())) {
                distance = route.getDistance();
                isSupported = true;
                break;
            }
        }

        if (!isSupported) {
            System.out.println("Service not available for this route.");
            return null;
        }

        double rate;
        String type = request.getVehicleType();
        switch (type.toLowerCase()) {
            case "bike":
                rate = new Bike().calculateFare(distance);
                break;
            case "sedan":
                rate = new SedanCar().calculateFare(distance);
                break;
            case "suv":
                rate = new SuvCar().calculateFare(distance);
                break;
            case "autorickshaw":
                rate = new AutoRickshow().calculateFare(distance);
                break;
            default:
                System.out.println("Invalid vehicle type: " + type);
                return null;
        }

        double[] fareDetails = new double[2];
        fareDetails[0] = rate;
        fareDetails[1] = distance;
        return fareDetails;
    }

    private Ride assignDriver(RideRequest request, double[] arr) {
        List<Driver> availableDrivers = getAvailableDrivers();
        for (int i = 0; i < availableDrivers.size(); i++) {
            Driver assignedDriver = availableDrivers.get(i);
            System.out.println("Attempting with Driver: " + assignedDriver.getFirstName() + " " + assignedDriver.getLastName());
            if (assignedDriver.isAvailable()) {
                System.out.println("Driver accepted the request.");
                assignedDriver.setAvailable(false);
                Ride ride = new Ride.Builder()
                        .setRideID(1)
                        .setRideStatus("Completed")
                        .setPickUpLocation(request.getPickUpLocation())
                        .setDropOffLocation(request.getDropOffLocation())
                        .setCustomer(request.getCustomer())
                        .setDriver(assignedDriver)
                        .setRideDate(request.getRideRequestDate())
                        .setPickUpTime(request.getPickUpTime())
                        .setDropOffTime(request.getDropOffTime())
                        .setDistance(arr[1])
                        .setTotalCost(arr[0])
                        .build();
                return ride;
            }
        }
        return null;
    }

    private List<Driver> getAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : driverList) {
            if (!driver.isLastTimeRejected()) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

    public Ride requestForRide(RideRequest rideRequest) throws Exception {
        if (!isRouteSupported(rideRequest)) {
            throw new Exception("Ride not supported");
        }

        double[] fareDetails = null;
        if (fareDetails == null) {
            System.out.println("Sorry, we are unable to proceed with your request.");
            return null;
        }

        return assignDriver(rideRequest, fareDetails);
    }

    public static void printTicket(Ride ride) {
        System.out.println("\n=========== Ride Details ===============");
        System.out.println("Ride ID            : " + ride.getRideID());
        System.out.println("Pick-Up Location   : " + ride.getPickUpLocation());
        System.out.println("Drop-Off Location  : " + ride.getDropOffLocation());
        System.out.println("Customer           : " + ride.getCustomer().getFirstName() + " " + ride.getCustomer().getLastName());
        System.out.println("Driver             : " + ride.getDriver().getFirstName() + " " + ride.getDriver().getLastName());
        System.out.println("Ride Date          : " + ride.getRideDate());
        System.out.println("Pick-Up Time       : " + ride.getPickUpTime());
        System.out.println("Drop-Off Time      : " + ride.getDropOffTime());
        System.out.println("Distance           : " + ride.getDistance() + " km");
        System.out.println("Total Cost         : $" + String.format("%.2f", ride.getTotalCost()));
        System.out.println("========================================");
    }
}