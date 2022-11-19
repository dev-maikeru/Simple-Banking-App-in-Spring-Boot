package com.bankingapp.Banking.App.in.Spring.Boot.controller;


import com.bankingapp.Banking.App.in.Spring.Boot.dto.AccountDtoRequest;
import com.bankingapp.Banking.App.in.Spring.Boot.exception.RecordNotFoundException;
import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;
import com.bankingapp.Banking.App.in.Spring.Boot.model.CheckingAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.model.InterestAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.model.RegularAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("")
    public Account createAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @GetMapping("")
    public List<Account> getAllAccount() {
        return accountService.findAllAccount();
    }

    @GetMapping("/{accId}")
    public Account getAnAccount(@PathVariable Long accId) throws RecordNotFoundException {
        return accountService.findAccountById(accId);
    }

    @DeleteMapping("/{accId}")
    public void deleteAccount(@PathVariable Long accId) throws RecordNotFoundException {
        accountService.deleteAccount(accId);
    }
}
