package com.bankingapp.Banking.App.in.Spring.Boot.model;

import com.bankingapp.Banking.App.in.Spring.Boot.utils.AccountUtils;

import javax.persistence.Entity;

@Entity
public class CheckingAccount extends Account {
    CheckingAccount() {
        setAcctNumber(AccountUtils.generateAccountNumber());
        setBalance(100d);
        setMinimumBalance(100d);
        setPenalty(10d);
        setTransactionCharge(1.0d);
    }
}
