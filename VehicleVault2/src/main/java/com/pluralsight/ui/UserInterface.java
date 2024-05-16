package com.pluralsight.ui;

import com.pluralsight.models.Dealership;
import com.pluralsight.models.DealershipFileManager;
import com.pluralsight.models.Vehicle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        init();
    }

    private void init() {
        List<Vehicle> vehicles = DealershipFileManager.loadDealership();
        this.dealership = new Dealership("", "", "");
        for (Vehicle vehicle : vehicles) {
            this.dealership.addVehicle(vehicle);
        }
    }


    public void display() {
        System.out.println("Welcome to VehicleVault!");

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 99:
                    System.out.println("Exiting the program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Find vehicles within a price range");
        System.out.println("2. Find vehicles by make/model");
        System.out.println("3. Find vehicles by year range");
        System.out.println("4. Find vehicles by color");
        System.out.println("5. Find vehicles by mileage range");
        System.out.println("6. Find vehicles by type");
        System.out.println("7. List all vehicles");
        System.out.println("8. Add a vehicle");
        System.out.println("9. Remove a vehicle");
        System.out.println("99. Quit");
        System.out.print("Enter your choice: ");
    }

    private void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehicles);
    }

    private void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    private void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();

        List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vehicles);
    }

    private void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.next();

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }

    private void processGetByMileageRequest() {
        try {
            System.out.print("Enter minimum mileage: ");
            int minMileage = scanner.nextInt();
            System.out.print("Enter maximum mileage: ");
            int maxMileage = scanner.nextInt();

            List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
            displayVehicles(vehicles);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer for mileage.");
            scanner.next(); // consume invalid input
        }
    }

    private void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String vehicleType = scanner.next();

        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);
    }

    private void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            System.out.println("Vehicles:");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    private void processAddVehicleRequest() {
        System.out.println("Enter vehicle details:");
        System.out.print("VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        System.out.print("Make: ");
        String make = scanner.next();
        System.out.print("Model: ");
        String model = scanner.next();
        System.out.print("Vehicle type: ");
        String vehicleType = scanner.next();
        System.out.print("Color: ");
        String color = scanner.next();
        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

        dealership.addVehicle(vehicle);

        saveDealershipToFile(dealership);

        System.out.println("Vehicle added successfully!");
    }

    private void processRemoveVehicleRequest() {
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();

        Vehicle vehicleToRemove = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleToRemove = vehicle;
                break;
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);

            saveDealershipToFile(dealership);

            System.out.println("Vehicle removed successfully!");
        } else {
            System.out.println("Vehicle with VIN " + vin + " not found.");
        }
    }

    private static final String DELIMITER = "|";

    private void saveDealershipToFile(Dealership dealership) {
        String fileName = "inventory_" + System.currentTimeMillis() + ".csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            writer.write(dealership.getName() + DELIMITER + dealership.getAddress() + DELIMITER + dealership.getPhone());
            writer.newLine();

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.write(vehicle.getVin() + DELIMITER + vehicle.getYear() + DELIMITER + vehicle.getMake() + DELIMITER +
                        vehicle.getModel() + DELIMITER + vehicle.getVehicleType() + DELIMITER + vehicle.getColor() + DELIMITER +
                        vehicle.getOdometer() + DELIMITER + vehicle.getPrice());
                writer.newLine();
            }
            System.out.println("Dealership data saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing dealership file: " + e.getMessage());
        }
    }
}

