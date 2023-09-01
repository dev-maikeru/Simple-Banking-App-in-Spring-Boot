package com.bankingapp.Banking.App.in.Spring.Boot.service;

import com.bankingapp.Banking.App.in.Spring.Boot.dto.TransactionDtoRequest;
import com.bankingapp.Banking.App.in.Spring.Boot.enums.TransactionTypes;
import com.bankingapp.Banking.App.in.Spring.Boot.exception.RecordNotFoundException;
import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;
import com.bankingapp.Banking.App.in.Spring.Boot.repository.AccountRepository;
import com.bankingapp.Banking.App.in.Spring.Boot.utils.TransactionUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;

    public TransactionServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account performTransaction(Long accId, TransactionDtoRequest transactionDtoRequest) throws RecordNotFoundException {
        Account account = accountRepository.findById(accId)
                .orElseThrow(() -> new RecordNotFoundException("Account not found."));
        TransactionTypes type = TransactionTypes.valueOf(transactionDtoRequest.getType());
        switch (type) {
            case DEPOSIT -> TransactionUtils.performDeposit(account, transactionDtoRequest);
            case WITHDRAW -> TransactionUtils.performWithdrawal(account, transactionDtoRequest);
            default -> throw new IllegalArgumentException("Invalid transaction type.");
        }
        return accountRepository.save(account);
    }
}
