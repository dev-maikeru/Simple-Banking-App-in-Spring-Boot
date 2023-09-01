package com.bankingapp.Banking.App.in.Spring.Boot.service;

import com.bankingapp.Banking.App.in.Spring.Boot.exception.RecordNotFoundException;
import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;
import com.bankingapp.Banking.App.in.Spring.Boot.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteAccount(Long accId) throws RecordNotFoundException {
        if (!accountRepository.existsById(accId)) {
            throw new RecordNotFoundException("Account not found");
        }
        accountRepository.deleteById(accId);
    }
}
