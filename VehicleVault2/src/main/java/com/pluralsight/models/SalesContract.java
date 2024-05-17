package com.pluralsight.models;

public class SalesContract extends Contract {
    private static final double SALES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100.00;
    private double vehiclePrice;
    private double processingFee;
    private boolean finance;
    private double interestRate;
    private int financeMonths;

    public SalesContract(String date, String customerName, String customerEmail, String vehicleSold, double vehiclePrice, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.vehiclePrice = vehiclePrice;
        this.finance = finance;

        // Set the processing fee based on the vehicle price
        if (vehiclePrice < 10000) {
            this.processingFee = 295.00;
        } else {
            this.processingFee = 495.00;
        }

        // Set the finance terms
        if (finance) {
            if (vehiclePrice < 10000) {
                this.interestRate = 0.0525;
                this.financeMonths = 24;
            } else {
                this.interestRate = 0.0425;
                this.financeMonths = 48;
            }
        } else {
            this.interestRate = 0;
            this.financeMonths = 0;
        }
    }

    @Override
    public double getTotalPrice() {
        double salesTax = vehiclePrice * SALES_TAX_RATE;
        return vehiclePrice + salesTax + RECORDING_FEE + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0;
        double totalPrice = getTotalPrice();
        return (totalPrice * (1 + interestRate)) / financeMonths;
    }

    @Override
    public double calculateTotalPrice() {
        return getTotalPrice();
    }

    @Override
    public double calculateMonthlyPayment() {
        return getMonthlyPayment();
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getFinanceMonths() {
        return financeMonths;
    }
}