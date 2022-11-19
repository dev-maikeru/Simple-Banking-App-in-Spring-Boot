package com.bankingapp.Banking.App.in.Spring.Boot.service;

import com.bankingapp.Banking.App.in.Spring.Boot.dto.AccountDtoRequest;
import com.bankingapp.Banking.App.in.Spring.Boot.exception.RecordNotFoundException;
import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;
import com.bankingapp.Banking.App.in.Spring.Boot.model.InterestAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAllAccount() {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountById(Long accId) throws RecordNotFoundException {
        return accountRepository.findById(accId)
                .orElseThrow(() -> new RecordNotFoundException("Account not found."));
    }

    @Override
    public void deleteAccount(Long accId) throws RecordNotFoundException {
        Account account = accountRepository.findById(accId)
                .orElseThrow(() -> new RecordNotFoundException("Account not found"));
        accountRepository.delete(account);
    }
}
