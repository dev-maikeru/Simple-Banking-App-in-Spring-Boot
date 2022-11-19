package com.bankingapp.Banking.App.in.Spring.Boot.service;

import com.bankingapp.Banking.App.in.Spring.Boot.dto.TransactionDtoRequest;
import com.bankingapp.Banking.App.in.Spring.Boot.exception.RecordNotFoundException;
import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;
import com.bankingapp.Banking.App.in.Spring.Boot.model.CheckingAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.model.RegularAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.repository.AccountRepository;
import com.bankingapp.Banking.App.in.Spring.Boot.utils.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account performTransaction(Long accId, TransactionDtoRequest transactionDtoRequest) throws RecordNotFoundException {
        Account account = accountRepository.findById(accId)
                .orElseThrow(() -> new RecordNotFoundException("Account not found."));

        switch (transactionDtoRequest.getType()) {
            case "deposit" -> account.setBalance(account.getBalance() + transactionDtoRequest.getAmount());
            case "withdraw" -> {
                if(transactionDtoRequest.getAmount() < account.getBalance()) {
                   TransactionUtils.performDeductions(account, transactionDtoRequest);
                } else {
                    account.setBalance(account.getBalance() - transactionDtoRequest.getAmount());
                }
            }
            default -> throw new IllegalArgumentException();
        }

        return accountRepository.save(account);
    }
}
