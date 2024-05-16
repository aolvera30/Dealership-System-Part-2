package com.pluralsight.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DealershipTest {
    private Dealership dealership;

    @BeforeEach
    public void setUp() {
        dealership = new Dealership("Test Dealership", "123 Test St", "123-456-7890");
    }

    @Test
    public void testAddVehicle_shouldAddVehicle_toDealership() {
        // Arrange
        Vehicle vehicle = new Vehicle(12345, 2020, "Toyota", "Camry", "Car", "Red", 10000, 25000.00);

        // Act
        dealership.addVehicle(vehicle);

        // Assert
        assertTrue(dealership.getAllVehicles().contains(vehicle));
    }

    @Test
    public void testRemoveVehicle_shouldRemoveVehicle_fromDealership() {
        // Arrange
        Vehicle vehicle = new Vehicle(12345, 2020, "Toyota", "Camry", "Car", "Red", 10000, 25000.00);
        dealership.addVehicle(vehicle);

        // Act
        dealership.removeVehicle(vehicle);

        // Assert
        assertFalse(dealership.getAllVehicles().contains(vehicle));
    }
}
