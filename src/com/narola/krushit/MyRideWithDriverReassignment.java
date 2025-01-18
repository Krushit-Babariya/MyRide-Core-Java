package com.narola.krushit;

import com.narola.krushit.commons.StatusConstant;
import com.narola.krushit.entity.*;
import com.narola.krushit.exception.RouteNotSupportedException;
import com.narola.krushit.exception.VehicleNotAvailableException;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MyRideWithDriverReassignment {
    private List<Customer> customerList = new ArrayList<>();
    private List<Driver> driverList = new ArrayList<>();
    private List<RideRequest> rideRequestList = new ArrayList<>();
    private List<Ride> rideList = new ArrayList<>();
    private List<Route> routeList = new ArrayList<>();
    private List<String> vehicleList = new ArrayList<>();

    public static void main(String[] args) {
        MyRideWithDriverReassignment myRide = new MyRideWithDriverReassignment();

        // Adding Routes
        myRide.addRoute(new Route("Surat", "Ahmedabad", 150));
        myRide.addRoute(new Route("Mumbai", "Kolkata", 1700));

        // Registering Drivers
        Driver driver1 = new Driver("Raj", "Raja", new BigInteger("9874563214"), "rajraja@gmail.com", "DL14-0123654787");
        myRide.registerDriver(driver1);
        driver1.setAvailable(false);

        Vehicle vehicle1 = new SedanCar("3W", "Petrol", "High", "Moderate", 4);
        driver1.addVehical(vehicle1);
        myRide.addVehicle(vehicle1);

        Driver driver2 = new Driver("Shri", "Vastava", new BigInteger("5641239874"), "shri@gmail.com", "GJ05-0123654787");
        myRide.registerDriver(driver2);

        Vehicle vehicle2 = new SuvCar("3W", "Petrol", 'S', 100, "Low");
        driver2.addVehical(vehicle2);
        myRide.addVehicle(vehicle2);

        System.out.println("Vehicles :: " + myRide.vehicleList);

        // Registering Customer
        Customer customer1 = new Customer("Rushit", "Bahtiyar", new BigInteger("9876543214"), "rkb@narola.email");
        myRide.registerCustomer(customer1);

        // Creating Ride Request
        RideRequest rideRequest = new RideRequest.Builder()
                .setPickUpLocation("Mumbai")
                .setDropOffLocation("Kolkata")
                .setCustomer(customer1)
                .setRideRequestDate(LocalDate.now())
                .setPickUpTime(LocalTime.of(10, 30))
                .setDropOffTime(LocalTime.of(11, 30))
                .setVehicleType("suv")
                .setCapacity(4)
                .build();

        System.out.println(rideRequest);
        myRide.rideRequestList.add(rideRequest);

        try {
            Ride ride = myRide.processRideRequest(rideRequest);
            if (ride != null) {
                myRide.rideList.add(ride);
            }
        } catch (Exception e) {
            System.out.println("\u26A0 \u26A0 " + e.getMessage() + " \u26A0 \u26A0");
        }

        System.out.println("=========================================================");
        System.out.println("Ride Requests :: " + myRide.rideRequestList);

        System.out.println("=========================================================");
        System.out.println("Rides :: " + myRide.rideList);
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle instanceof SedanCar) {
            vehicleList.add("sedan");
        } else if (vehicle instanceof SuvCar) {
            vehicleList.add("suv");
        } else if (vehicle instanceof AutoRickshow) {
            vehicleList.add("auto");
        } else if (vehicle instanceof Bike) {
            vehicleList.add("bike");
        }
    }

    public void registerDriver(Driver driver) {
        driver.setUserID(driverList.size() + 1);
        driverList.add(driver);
        System.out.println("Driver Registered Successfully...!!!");
    }

    public void registerCustomer(Customer customer) {
        customer.setUserID(customerList.size() + 1);
        customerList.add(customer);
        System.out.println("Customer Registered Successfully...!!!");
    }

    public void addRoute(Route route) {
        routeList.add(route);
        System.out.println("Route Added Successfully...!!!");
    }

    public Ride processRideRequest(RideRequest rideRequest) throws Exception {
        if (!isRouteAvailable(rideRequest)) {
            throw new RouteNotSupportedException("Route is not supported for the requested ride.");
        }

        if (!isVehicleAvailable(rideRequest)) {
            throw new VehicleNotAvailableException("Sorry your requested vehicle is not available");
        }

        FareDetails fareDetails = calculateFare(rideRequest);
        Ride ride = assignDriverToRide(rideRequest, fareDetails);
        if (ride != null) {
            processRide(ride);
            rideRequest.setRideRequestStatus(StatusConstant.RIDE_REQUEST_STATUS_ACCEPTED);
        }
        return ride;
    }

    private boolean isRouteAvailable(RideRequest request) {
        for (Route route : routeList) {
            if (route.getPickUpLocation().equalsIgnoreCase(request.getPickUpLocation()) &&
                    route.getDropOffLocation().equalsIgnoreCase(request.getDropOffLocation())) {
                return true;
            }
        }
        return false;
    }

    private boolean isVehicleAvailable(RideRequest rideRequest) {
        for (String vehicle : vehicleList) {
            if (rideRequest.getVehicleType().equalsIgnoreCase(vehicle)) {
                return true;
            }
        }
        return false;
    }

    private FareDetails calculateFare(RideRequest request) throws Exception {
        Route matchedRoute = null;
        for (Route route : routeList) {
            if (route.getPickUpLocation().equalsIgnoreCase(request.getPickUpLocation()) &&
                    route.getDropOffLocation().equalsIgnoreCase(request.getDropOffLocation())) {
                matchedRoute = route;
                break;
            }
        }

        double distance = matchedRoute.getDistance();
        double rate;

        if (request.getVehicleType().equalsIgnoreCase("bike")) {
            rate = new Bike().calculateFare(distance);
        } else if (request.getVehicleType().equalsIgnoreCase("sedan")) {
            rate = new SedanCar().calculateFare(distance);
        } else if (request.getVehicleType().equalsIgnoreCase("suv")) {
            rate = new SuvCar().calculateFare(distance);
        } else if (request.getVehicleType().equalsIgnoreCase("auto")) {
            rate = new AutoRickshow().calculateFare(distance);
        } else {
            throw new Exception("Invalid vehicle type: " + request.getVehicleType());
        }

        return new FareDetails(rate, distance);
    }

    public void processRide(Ride ride) {
        printTicket(ride);
        if (ride != null) {
            System.out.print("Ride is Ongoing");
            rideProgress();
            ride.setRideStatus(StatusConstant.RIDE_COMPLETED);
            System.out.println("\nRide Completed...!!!");
        }
    }

    private Ride assignDriverToRide(RideRequest request, FareDetails fareDetails) {
        List<Driver> availableDrivers = getAvailableDrivers();

        for (Driver driver : availableDrivers) {
            System.out.println("Attempting with Driver: " + driver.getFirstName() + " " + driver.getLastName());
            if (!driver.isLastTimeRejected() && driver.isAvailable() && request.getVehicleType().equalsIgnoreCase(driver.getVehicleType(driver.getVehicle()))) {
                driver.setAvailable(false);
                System.out.println("Your rider is " + driver.getFirstName() + " " + driver.getLastName());
                return new Ride.Builder()
                        .setRideID(rideList.size() + 1)
                        .setRideStatus(StatusConstant.RIDE_SCHEDULED)
                        .setPickUpLocation(request.getPickUpLocation())
                        .setDropOffLocation(request.getDropOffLocation())
                        .setCustomer(request.getCustomer())
                        .setDriver(driver)
                        .setRideDate(request.getRideRequestDate())
                        .setPickUpTime(request.getPickUpTime())
                        .setDropOffTime(request.getDropOffTime())
                        .setDistance(fareDetails.getDistance())
                        .setTotalCost(fareDetails.getRate())
                        .build();
            }
        }

        request.setRideRequestStatus(StatusConstant.RIDE_REQUEST_STATUS_REJECTED);
        System.out.println("No available drivers at the moment.");
        return null;
    }

    private List<Driver> getAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : driverList) {
            if (driver.isAvailable()) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

    private void rideProgress() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.print(".");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
