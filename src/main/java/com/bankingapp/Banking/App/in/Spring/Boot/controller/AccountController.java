package com.bankingapp.Banking.App.in.Spring.Boot.controller;


import com.bankingapp.Banking.App.in.Spring.Boot.exception.RecordNotFoundException;
import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;
import com.bankingapp.Banking.App.in.Spring.Boot.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

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
