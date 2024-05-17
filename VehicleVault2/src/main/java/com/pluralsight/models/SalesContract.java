package com.pluralsight.models;

public class SalesContract extends Contract {
    private static final double SALES_TAX_RATE = 0.05;
    private static final double RECORDING_FEE = 100.00;
    private double processingFee;
    private boolean finance;
    private double interestRate;
    private int financeMonths;

    public SalesContract(String date, String customerName, String customerEmail, String vehicleSold, double processingFee, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.processingFee = processingFee;
        this.finance = finance;
    }

    public void setProcessingFee(double processingFee)
    {
        this.processingFee = processingFee;
    }

    public void setFinance(boolean finance)
    {
        this.finance = finance;
    }

    public double getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(double interestRate)
    {
        this.interestRate = interestRate;
    }

    public int getFinanceMonths()
    {
        return financeMonths;
    }

    public void setFinanceMonths(int financeMonths)
    {
        this.financeMonths = financeMonths;
    }

    @Override
    public double getTotalPrice() {
        // Total price calculation logic
        double totalPrice = getTotalPriceWithoutTaxes(); // Get total price without taxes
        double salesTax = totalPrice * SALES_TAX_RATE; // Calculate sales tax
        return totalPrice + salesTax + RECORDING_FEE + processingFee; // Return total price including taxes and fees
    }

    private double getTotalPriceWithoutTaxes() {
        // Total price calculation logic without taxes
        // Replace this with your actual calculation based on the specifications
        return 0; // Placeholder, replace with actual calculation
    }

    @Override
    public double getMonthlyPayment() {
        // Monthly payment calculation logic
        if (!finance) return 0; // If not financed, return 0
        double totalPrice = getTotalPrice(); // Get total price
        calculateFinanceDetails(totalPrice); // Calculate finance details based on total price
        return (totalPrice * (1 + interestRate)) / financeMonths; // Return monthly payment
    }

    @Override
    public double calculateTotalPrice()
    {
        return 0;
    }

    @Override
    public double calculateMonthlyPayment()
    {
        return 0;
    }

    private void calculateFinanceDetails(double totalPrice) {
        // Calculate finance details based on total price
        if (totalPrice < 10000) {
            interestRate = 0.0525; // Interest rate for price less than $10,000
            financeMonths = 24; // Finance months for price less than $10,000
        } else {
            interestRate = 0.0425; // Interest rate for price $10,000 or more
            financeMonths = 48; // Finance months for price $10,000 or more
        }
    }

    public Object getProcessingFee()
    {
        return null;
    }

    public Object isFinance()
    {
        return null;
    }
}