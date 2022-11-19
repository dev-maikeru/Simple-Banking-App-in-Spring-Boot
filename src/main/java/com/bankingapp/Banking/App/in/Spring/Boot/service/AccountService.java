package com.bankingapp.Banking.App.in.Spring.Boot.service;

import com.bankingapp.Banking.App.in.Spring.Boot.dto.AccountDtoRequest;
import com.bankingapp.Banking.App.in.Spring.Boot.exception.RecordNotFoundException;
import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;

import java.util.List;

public interface AccountService {
    Account saveAccount(Account account);
    List<Account> findAllAccount();
    Account findAccountById(Long accId) throws RecordNotFoundException;
    void deleteAccount(Long accId) throws RecordNotFoundException;
}
