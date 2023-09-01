package com.bankingapp.Banking.App.in.Spring.Boot.utils;

import com.bankingapp.Banking.App.in.Spring.Boot.dto.TransactionDtoRequest;
import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;
import com.bankingapp.Banking.App.in.Spring.Boot.model.CheckingAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.model.RegularAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;

public class TransactionUtils {

    public static void performDeposit(Account account, TransactionDtoRequest transactionDtoRequest) {
        Function<Account, Double> depositAmountToAccount = acc -> acc.getBalance() + transactionDtoRequest.getAmount();
        account.setBalance(depositAmountToAccount.apply(account));
    }
    public static void performWithdrawal(Account account, TransactionDtoRequest transactionDtoRequest) {
        if (transactionDtoRequest.getAmount() > account.getBalance()) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        Function<Account, Double> deductFromAccountBalance = acc -> acc.getBalance() - (transactionDtoRequest.getAmount() + acc.getPenalty());
        if (account instanceof RegularAccount) {
            account.setBalance(deductFromAccountBalance.apply(account));
        }
        if (account instanceof CheckingAccount) {
            account.setBalance(deductFromAccountBalance.apply(account));
        }
    }
}
