package com.pluralsight.models;

public class LeaseContract extends Contract {
    private static final double LEASE_FEE_RATE = 0.07;
    private static final double FINANCE_RATE = 0.04;
    private static final int LEASE_MONTHS = 36;
    private double endingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, String vehicleSold, double vehiclePrice) {
        super(date, customerName, customerEmail, vehicleSold);
        this.endingValue = vehiclePrice * 0.5;
        this.leaseFee = vehiclePrice * LEASE_FEE_RATE;
        setTotalPrice(vehiclePrice);
    }

    @Override
    public double getTotalPrice() {
        double vehiclePrice = getTotalPrice();
        return vehiclePrice + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double totalPrice = getTotalPrice();
        return (totalPrice - endingValue) * (1 + FINANCE_RATE) / LEASE_MONTHS;
    }
}
