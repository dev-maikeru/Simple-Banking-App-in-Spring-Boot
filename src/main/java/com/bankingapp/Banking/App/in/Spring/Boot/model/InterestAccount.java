package com.bankingapp.Banking.App.in.Spring.Boot.model;

import com.bankingapp.Banking.App.in.Spring.Boot.utils.AccountUtils;

import javax.persistence.Entity;

@Entity
public class InterestAccount extends Account {
    InterestAccount() {
        setAcctNumber(AccountUtils.generateAccountNumber());
        setInterestCharge(.03d);
    }
}
