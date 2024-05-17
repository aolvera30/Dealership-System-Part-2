package com.pluralsight.models;

public abstract class Contract {
    private String date;
    private String customerName;
    private String customerEmail;
    private String vehicleSold;
    private double totalPrice;
    private double monthlyPayment;

    public Contract(String date, String customerName, String customerEmail, String vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    // Getters and Setters
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getVehicleSold() { return vehicleSold; }
    public void setVehicleSold(String vehicleSold) { this.vehicleSold = vehicleSold; }


    // Abstract methods
    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();
}
