package com.pluralsight.models;

public class LeaseContract extends Contract {
    private static final double LEASE_FEE_RATE = 0.07;
    private static final double FINANCE_RATE = 0.04;
    private static final int LEASE_MONTHS = 36;
    private double endingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, String vehicleSold, double vehiclePrice, double leaseFee) {
        super(date, customerName, customerEmail, vehicleSold);
        this.endingValue = vehiclePrice * 0.5; // Set ending value as 50% of vehicle price
        this.leaseFee = leaseFee; // Set lease fee
    }

    @Override
    public double getTotalPrice() {
        // Total price calculation logic
        return getEndingValue() + getLeaseFee(); // Total price is sum of ending value and lease fee
    }

    @Override
    public double getMonthlyPayment() {
        // Monthly payment calculation logic
        double totalPrice = getTotalPrice(); // Get total price
        return (totalPrice * (1 + FINANCE_RATE)) / LEASE_MONTHS; // Return monthly payment
    }

    @Override
    public double calculateTotalPrice() {
        // Calculate total price (override for abstract method)
        return getTotalPrice();
    }

    @Override
    public double calculateMonthlyPayment() {
        // Calculate monthly payment (override for abstract method)
        return getMonthlyPayment();
    }

    public double getEndingValue() {
        // Getter for ending value
        return endingValue;
    }

    public double getLeaseFee() {
        // Getter for lease fee
        return leaseFee;
    }
}