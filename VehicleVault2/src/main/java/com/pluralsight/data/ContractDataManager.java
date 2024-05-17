package com.pluralsight.data;

import com.pluralsight.models.Contract;
import com.pluralsight.models.SalesContract;
import com.pluralsight.models.LeaseContract;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContractDataManager {
    private static final String CONTRACT_FILE = "Files/contract.csv";

    public static void saveContract(LeaseContract leaseContract)
    {
    }

    public static void saveContract(SalesContract salesContract)
    {
    }

    public List<Contract> loadContracts() {
        List<Contract> contracts = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(CONTRACT_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                String contractType = parts[0];
                String date = parts[1];
                String customerName = parts[2];
                String customerEmail = parts[3];
                String vehicleSold = parts[4];

                Contract contract;
                if (contractType.equals("SALE")) {
                    double processingFee = Double.parseDouble(parts[5]);
                    boolean finance = Boolean.parseBoolean(parts[6]);
                    contract = new SalesContract(date, customerName, customerEmail, vehicleSold, processingFee, finance);
                } else if (contractType.equals("LEASE")) {
                    double endingValue = Double.parseDouble(parts[5]);
                    double leaseFee = Double.parseDouble(parts[6]);
                    contract = new LeaseContract(date, customerName, customerEmail, vehicleSold, endingValue);
                } else {
                    throw new IllegalArgumentException("Unknown contract type: " + contractType);
                }

                contracts.add(contract);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Contract file not found: " + e.getMessage());
        }

        return contracts;
    }

    public void saveContracts(List<Contract> contracts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTRACT_FILE))) {
            for (Contract contract : contracts) {
                if (contract instanceof SalesContract) {
                    SalesContract salesContract = (SalesContract) contract;
                    writer.write(String.format("SALE|%s|%s|%s|%s|%|2f|%b\n",
                            salesContract.getDate(),
                            salesContract.getCustomerName(),
                            salesContract.getCustomerEmail(),
                            salesContract.getVehicleSold(),
                            salesContract.getProcessingFee(),
                            salesContract.isFinance()));
                } else if (contract instanceof LeaseContract) {
                    LeaseContract leaseContract = (LeaseContract) contract;
                    writer.write(String.format("LEASE|%s|%s|%s|%s|%.2f|%.2f\n",
                            leaseContract.getDate(),
                            leaseContract.getCustomerName(),
                            leaseContract.getCustomerEmail(),
                            leaseContract.getVehicleSold(),
                            leaseContract.getEndingValue(),
                            leaseContract.getLeaseFee()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to contract file: " + e.getMessage());
        }
    }
}