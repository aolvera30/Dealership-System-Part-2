package com.pluralsight.data;
import com.pluralsight.models.Dealership;
import com.pluralsight.models.Vehicle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DealershipFileManager
{

    public Dealership loadDearlership()
    {
        Dealership dealership = null;

        // Read data from the inventory CSV file
        try(Scanner fileScanner = new Scanner(new File("Files/inventory.csv"))) {
            String dealerLine = fileScanner.nextLine(); // this is the dealership  name, address and phone
            String[] parts = dealerLine.split("\\|");
            dealership = new Dealership(parts[0], parts[1], parts[2]);

            // Read each line of the file and parse vehicle data
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] tokens = line.split("\\|");

                // Extract vehicle data from tokens
                int vin = Integer.parseInt(tokens[0].trim());
                int year = Integer.parseInt(tokens[1].trim());
                String make = tokens[2].trim();
                String model = tokens[3].trim();
                String vehicleType = tokens[4].trim();
                String color = tokens[5].trim();
                int odometer = Integer.parseInt(tokens[6].trim());
                double price = Double.parseDouble(tokens[7].trim());

                // Create a Vehicle object with extracted data and add it to the inventory
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);
            }

        } catch (FileNotFoundException e) {
            // Handle the case where the file is not found
            throw new RuntimeException("Failed to load inventory: File not found.", e);
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try (FileWriter writer = new FileWriter("Files/inventory.csv")) {
            // Write dealership information (name, address, phone)
            writer.write(dealership.getName() + " | " + dealership.getAddress() + " | " + dealership.getPhone() + "\n");

            // Write each vehicle in the inventory
            List<Vehicle> vehicles = dealership.getAllVehicles();
            for (Vehicle vehicle : vehicles) {
                writer.write(vehicle.getVin() + " | " + vehicle.getYear() + " | " + vehicle.getMake() + " | " +
                        vehicle.getModel() + " | " + vehicle.getVehicleType() + " | " + vehicle.getColor() + " | " +
                        vehicle.getOdometer() + " | " + vehicle.getPrice() + "\n");
            }

        } catch (IOException e) {
            // Handle file writing errors
            e.printStackTrace();
        }

    }
}


