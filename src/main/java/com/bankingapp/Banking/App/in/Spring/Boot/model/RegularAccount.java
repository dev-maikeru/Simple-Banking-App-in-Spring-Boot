package com.bankingapp.Banking.App.in.Spring.Boot.model;

import com.bankingapp.Banking.App.in.Spring.Boot.utils.AccountUtils;

import javax.persistence.Entity;

@Entity
public class RegularAccount extends Account {
    public RegularAccount() {
        setAcctNumber(AccountUtils.generateAccountNumber());
        setBalance(500d);
        setMinimumBalance(500d);
        setPenalty(10d);
    }
}
