package com.bankingapp.Banking.App.in.Spring.Boot.utils;

import com.bankingapp.Banking.App.in.Spring.Boot.dto.TransactionDtoRequest;
import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;
import com.bankingapp.Banking.App.in.Spring.Boot.model.CheckingAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.model.RegularAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionUtils {
    public static void performDeductions(Account account, TransactionDtoRequest transactionDtoRequest) {
        if (account instanceof RegularAccount)
            account.setBalance(account.getBalance() - (transactionDtoRequest.getAmount() +
                    account.getPenalty()));
        if (account instanceof CheckingAccount)
            account.setBalance(account.getBalance() - (transactionDtoRequest.getAmount() +
                    account.getPenalty() + account.getTransactionCharge()));
    }
}
