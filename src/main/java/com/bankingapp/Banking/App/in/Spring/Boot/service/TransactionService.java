package com.bankingapp.Banking.App.in.Spring.Boot.service;

import com.bankingapp.Banking.App.in.Spring.Boot.dto.TransactionDtoRequest;
import com.bankingapp.Banking.App.in.Spring.Boot.exception.RecordNotFoundException;
import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;

import javax.transaction.Transaction;

public interface TransactionService {
    Account performTransaction(Long accId, TransactionDtoRequest transactionDtoRequest) throws RecordNotFoundException;
}
