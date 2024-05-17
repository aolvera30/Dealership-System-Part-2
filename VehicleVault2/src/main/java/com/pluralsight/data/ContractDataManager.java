package com.pluralsight.data;

import com.pluralsight.models.Contract;
import com.pluralsight.models.SalesContract;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractDataManager {
    private static final String CONTRACT_FILE = "contracts.txt";

    public void saveContract(Contract contract) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTRACT_FILE, true))) {
            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;
                writer.write(String.format("SALE|%s|%