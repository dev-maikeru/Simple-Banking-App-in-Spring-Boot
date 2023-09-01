package com.bankingapp.Banking.App.in.Spring.Boot.dto;

import lombok.Data;

@Data
public class TransactionDtoRequest {
    private String type;
    private double amount;
}
