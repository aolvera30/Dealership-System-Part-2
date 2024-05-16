package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByMileage(int minMileage, int maxMileage) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= minMileage && vehicle.getOdometer() <= maxMileage) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> vehiclesInRange = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                vehiclesInRange.add(vehicle);
            }
        }
        return vehiclesInRange;
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}


