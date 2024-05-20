package com.pluralsight.ui;


import com.pluralsight.data.ContractDataManager;
import com.pluralsight.models.Dealership;
import com.pluralsight.data.DealershipFileManager;
import com.pluralsight.models.LeaseContract;
import com.pluralsight.models.SalesContract;
import com.pluralsight.models.Vehicle;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public Dealership dealership;
    public DealershipFileManager fileManager;
    private Scanner scanner;


    public UserInterface() {
        init();
        fileManager = new DealershipFileManager();
    }

    {
        this.scanner = new Scanner(System.in);
    }

    private void init() {
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.loadDearlership();
    }

    public void display() {
        init(); // Load the dealership data

        Scanner userInput = new Scanner(System.in);
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
            System.out.println("10.Lease Vehicle");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            // Read user input
            int choice = userInput.nextInt();
            userInput.nextLine();

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
                    // Prompt user for details
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

                    // Create a new Vehicle object with the entered details
                    Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                    processAddVehicleRequest(newVehicle);
                    break;

                case 8:
                    // Prompt the user to enter the VIN of the vehicle to remove
                    System.out.print("Enter the VIN of the vehicle to remove: ");
                    int vinToRemove = userInput.nextInt();
                    userInput.nextLine();

                    // Search for the vehicle in the dealership's inventory
                    Vehicle vehicleToRemove = findVehicleByVIN(vinToRemove);

                    if (vehicleToRemove != null) {
                        processRemoveVehicleRequest(vinToRemove);
                    } else {
                        System.out.println("Vehicle with VIN " + vinToRemove + " not found.");
                    }
                    break;

                case 9:
                    addSalesContract();
                    break;

                case 10:
                    addLeaseContract();
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
        // Implement logic
    }

    public void processGetByYearRequest() {
        // Implement logic
    }

    public void processGetByColorRequest() {
        // Implement logic
    }

    public void processGetByMileageRequest() {
        // Implement logic
    }

    public void processGetByVehicleTypeRequest() {
        // Implement logic
    }

    public void processGetAllVehiclesRequest() {
        // 1. Call the dealership's getAllVehicles() method to get the list of all vehicles
        List<Vehicle> allVehicles = dealership.getAllVehicles();

        // 2. Call the displayVehicles() helper method passing it the list of all vehicles
        displayVehicles(allVehicles);
    }

    // Add Vehicle
    public void processAddVehicleRequest(Vehicle vehicle) {
        dealership.addVehicle(vehicle); // Add vehicle to the dealership inventory
        fileManager.saveDealership(dealership); // Save the updated inventory
        System.out.println("Vehicle added successfully.");
        System.out.println();
    }

    // Remove Vehicle
    public void processRemoveVehicleRequest(int vin) {
        Vehicle vehicleToRemove = findVehicleByVIN(vin);
        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle with VIN " + vin + " removed successfully.");
            System.out.println();
        } else {
            System.out.println("Vehicle with VIN " + vin + " not found.");
            System.out.println();
        }
    }

    private Vehicle findVehicleByVIN(int vin) {
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                return vehicle; // Found the vehicle
            }
        }
        return null; // Vehicle with the given VIN not found
    }

    private void addLeaseContract() {
        System.out.println("Enter contract details:");
        System.out.print("Date: ");
        String date = scanner.nextLine();
        System.out.print("Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Customer Email: ");
        String customerEmail = scanner.nextLine();
        System.out.print("Vehicle Sold: ");
        String vehicleSold = scanner.nextLine();
        System.out.print("Vehicle Price: ");
        double vehiclePrice = scanner.nextDouble();
        scanner.nextLine();


        LeaseContract leaseContract = new LeaseContract(date,customerName, customerEmail, vehicleSold,vehiclePrice);
        ContractDataManager.saveContract(leaseContract);
    }

    private SalesContract addSalesContract() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);

        System.out.println("Enter contract details:");
        System.out.println("Date: " + formattedDate);
        String date = formattedDate; // Automatically set the date to current date

        System.out.print("Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Customer Email: ");
        String customerEmail = scanner.nextLine();

        System.out.print("Vehicle Sold: ");
        String vehicleSold = scanner.nextLine();

        System.out.print("Vehicle Price: ");
        double vehiclePrice = scanner.nextDouble();
        scanner.nextLine(); // Consume the leftover newline character

        System.out.print("Finance (true/false): ");
        boolean finance = scanner.nextBoolean();
        scanner.nextLine(); // Consume the leftover newline character

        // Create a new SalesContract instance
        SalesContract contract = new SalesContract(date, customerName, customerEmail, vehicleSold, vehiclePrice, finance);

        // Additional logic to save the contract or display confirmation
        System.out.println("Sales contract created:");
        System.out.println("Date: " + contract.getDate());
        System.out.println("Customer Name: " + contract.getCustomerName());
        System.out.println("Customer Email: " + contract.getCustomerEmail());
        System.out.println("Vehicle Sold: " + contract.getVehicleSold());
        System.out.println("Vehicle Price: " + contract.getVehiclePrice());
        System.out.println("Processing Fee: " + contract.getProcessingFee());
        System.out.println("Finance: " + contract.isFinance());
        System.out.println("Total Price: " + contract.getTotalPrice());
        System.out.println("Monthly Payment: " + contract.getMonthlyPayment());

        ContractDataManager.saveContract(addSalesContract());
        return contract;
    }
}

