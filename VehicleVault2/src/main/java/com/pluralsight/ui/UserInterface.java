package com.pluralsight.ui;

import com.pluralsight.models.Dealership;
import com.pluralsight.data.DealershipFileManager;
import com.pluralsight.models.Vehicle;
import com.pluralsight.models.SalesContract;
import com.pluralsight.models.LeaseContract;
import com.pluralsight.data.ContractDataManager;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public Dealership dealership;
    public DealershipFileManager fileManager;
    private static final Scanner userInput = new Scanner(System.in);
    private static final ContractDataManager contractDataManager = new ContractDataManager();

    public UserInterface() {
        init();
        fileManager = new DealershipFileManager();
    }

    private void init() {
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.loadDealership();
    }

    public void display() {
        init(); // Load the dealership data

        boolean running = true;
        while (running) {
            // Display menu options
            System.out.println("1. Get by Make and Model");
            System.out.println("2. Get by Year");
            System.out.println("3. Get by Color");
            System.out.println("4. Get by Mileage");
            System.out.println("5. Get by Vehicle Type");
            System.out.println("6. Get All Vehicles");
            System.out.println("7. Add Vehicle");
            System.out.println("8. Remove Vehicle");
            System.out.println("9. Sell Vehicle");
            System.out.println("10. Lease Vehicle");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            // Read user input
            int choice = userInput.nextInt();
            userInput.nextLine(); // Consume newline

            // Process user command
            switch (choice) {
                case 1:
                    processGetByMakeModelRequest();
                    break;
                case 2:
                    processGetByYearRequest();
                    break;
                case 3:
                    processGetByColorRequest();
                    break;
                case 4:
                    processGetByMileageRequest();
                    break;
                case 5:
                    processGetByVehicleTypeRequest();
                    break;
                case 6:
                    processGetAllVehiclesRequest();
                    break;
                case 7:
                    handleAddVehicle();
                    break;
                case 8:
                    handleRemoveVehicle();
                    break;
                case 9:
                    handleSellVehicle();
                    break;
                case 10:
                    handleLeaseVehicle();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        userInput.close();
    }

    // Helper Method
    private void displayVehicles(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            // Display each vehicle information
            System.out.println(vehicle.toString());
        }
    }

    public void processGetByMakeModelRequest() {
    }

    public void processGetByYearRequest() {
    }

    public void processGetByColorRequest() {
    }

    public void processGetByMileageRequest() {
    }

    public void processGetByVehicleTypeRequest() {
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    public void handleAddVehicle() {
        System.out.println("Enter vehicle details:");
        System.out.print("VIN: ");
        int vin = userInput.nextInt();
        userInput.nextLine();
        System.out.print("Year: ");
        int year = userInput.nextInt();
        userInput.nextLine();
        System.out.print("Make: ");
        String make = userInput.nextLine();
        System.out.print("Model: ");
        String model = userInput.nextLine();
        System.out.print("Vehicle Type: ");
        String vehicleType = userInput.nextLine();
        System.out.print("Color: ");
        String color = userInput.nextLine();
        System.out.print("Odometer: ");
        int odometer = userInput.nextInt();
        userInput.nextLine();
        System.out.print("Price: ");
        double price = userInput.nextDouble();
        userInput.nextLine(); // Consume newline

        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        processAddVehicleRequest(newVehicle);
    }

    public void handleRemoveVehicle() {
        System.out.print("Enter the VIN of the vehicle to remove: ");
        int vinToRemove = userInput.nextInt();
        userInput.nextLine();

        Vehicle vehicleToRemove = findVehicleByVIN(vinToRemove);

        if (vehicleToRemove != null) {
            processRemoveVehicleRequest(vinToRemove);
        } else {
            System.out.println("Vehicle with VIN " + vinToRemove + " not found.");
        }
    }

    public void handleSellVehicle() {
        System.out.println("Enter date (YYYYMMDD): ");
        String date = userInput.nextLine();

        System.out.println("Enter customer name: ");
        String customerName = userInput.nextLine();

        System.out.println("Enter customer email: ");
        String customerEmail = userInput.nextLine();

        System.out.println("Enter vehicle sold (VIN): ");
        int vin = userInput.nextInt();
        userInput.nextLine();

        Vehicle vehicleSold = findVehicleByVIN(vin);
        if (vehicleSold == null) {
            System.out.println("Vehicle with VIN " + vin + " not found.");
            return;
        }

        System.out.println("Enter vehicle price: ");
        double vehiclePrice = userInput.nextDouble();
        userInput.nextLine();

        System.out.println("Will this be financed? (yes/no): ");
        boolean finance = userInput.nextLine().equalsIgnoreCase("yes");

        SalesContract salesContract = new SalesContract(date, customerName, customerEmail, vehicleSold.toString(), vehiclePrice, finance);
        contractDataManager.saveContract(salesContract);

        System.out.println("Sales contrac
