package com.pluralsight.ui;
import com.pluralsight.models.Dealership;
import com.pluralsight.data.DealershipFileManager;
import com.pluralsight.models.Vehicle;

import java.util.List;
import java.util.Scanner;

public class UserInterface
{
    public Dealership dealership;
    public DealershipFileManager fileManager;


    public UserInterface()
    {
        init();
        fileManager = new DealershipFileManager();

    }

    private void init()
    {
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.loadDearlership();

    }


    public void display()
    {
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
                    System.out.println(" ");

                case 10:
                    System.out.println("");

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
    private void displayVehicles(List<Vehicle> vehicles)
    {
        for (Vehicle vehicle : vehicles) {
            // Display each vehicle information
            System.out.println(vehicle.toString());
        }
    }


    public void processGetByMakeModelRequest()
    {
    }

    public void processGetByYearRequest()
    {
    }

    public void processGetByColorRequest()
    {
    }

    public void processGetByMileageRequest()
    {
    }

    public void processGetByVehicleTypeRequest()
    {
    }


    public void processGetAllVehiclesRequest()
    {
        // 1. Call the dealership's getAllVehicles() method to get the list of all vehicles
        List<Vehicle> allVehicles = dealership.getAllVehicles();

        // 2. Call the displayVehicles() helper method passing it the list of all vehicles
        displayVehicles(allVehicles);
    }

    // Add Vehicle
    public void processAddVehicleRequest(Vehicle vehicle)
    {
        dealership.addVehicle(vehicle); // Add vehicle to the dealership inventory
        fileManager.saveDealership(dealership); // Save the updated inventory
        System.out.println("Vehicle added successfully.");
        System.out.println();
    }

    // Remove Vehicle
    public void processRemoveVehicleRequest(int vin)
    {
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

    private Vehicle findVehicleByVIN(int vin)
    {
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                return vehicle; // Found the vehicle
            }
        }
        return null; // Vehicle with the given VIN not found
    }

    public static void sellVehicle()
}

