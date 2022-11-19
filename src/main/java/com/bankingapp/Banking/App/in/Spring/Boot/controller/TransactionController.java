package com.bankingapp.Banking.App.in.Spring.Boot.controller;

import com.bankingapp.Banking.App.in.Spring.Boot.dto.TransactionDtoRequest;
import com.bankingapp.Banking.App.in.Spring.Boot.exception.RecordNotFoundException;
import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;
import com.bankingapp.Banking.App.in.Spring.Boot.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{accId}/transactions")
    public Account performTransaction(@PathVariable Long accId, @RequestBody TransactionDtoRequest transactionDtoRequest) throws RecordNotFoundException {
        return transactionService.performTransaction(accId, transactionDtoRequest);
    }
}
