package com.bankingapp.Banking.App.in.Spring.Boot.service;

import com.bankingapp.Banking.App.in.Spring.Boot.exception.RecordNotFoundException;
import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;
import com.bankingapp.Banking.App.in.Spring.Boot.model.CheckingAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.model.InterestAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.model.RegularAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.repository.AccountRepository;
import com.bankingapp.Banking.App.in.Spring.Boot.utils.AccountUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    public Account arrangeRegularAccount() {
        Account regularAccount = new RegularAccount();
        regularAccount.setAcctNumber(AccountUtils.generateAccountNumber());
        regularAccount.setBalance(500d);
        regularAccount.setMinimumBalance(500d);
        regularAccount.setPenalty(10d);
        return regularAccount;
    }

    public Account arrangeInterestAccount() {
        Account interestAccount = new InterestAccount();
        interestAccount.setAcctNumber(AccountUtils.generateAccountNumber());
        interestAccount.setInterestCharge(.03d);
        return interestAccount;
    }

    public Account arrangeCheckingAccount() {
        Account checkingAccount = new CheckingAccount();
        checkingAccount.setAcctNumber(AccountUtils.generateAccountNumber());
        checkingAccount.setBalance(100d);
        checkingAccount.setMinimumBalance(100d);
        checkingAccount.setPenalty(10d);
        checkingAccount.setTransactionCharge(1.0d);
        return checkingAccount;
    }

    @Test
    void testSaveRegularAccount() {
        //Arrange
        Account regularAccount = arrangeRegularAccount();
        when(accountRepository.save(any(Account.class))).thenReturn(regularAccount);

        //Act
        Account newRegularAccount = accountService.saveAccount(regularAccount);

        //Assert
        assertNotNull(newRegularAccount);
        assertEquals(9, newRegularAccount.getAcctNumber().length());
        assertEquals(500d, newRegularAccount.getBalance());
        assertEquals(500d, newRegularAccount.getMinimumBalance());
        assertEquals(10d, newRegularAccount.getPenalty());
    }

    @Test
    void testSaveInterestAccount() {
        //Arrange
        Account interestAccount = arrangeInterestAccount();
        when(accountRepository.save(any(Account.class))).thenReturn(interestAccount);

        //Act
        Account newInterestAccount = accountService.saveAccount(interestAccount);

        //Assert
        assertNotNull(newInterestAccount);
        assertEquals(9, newInterestAccount.getAcctNumber().length());
        assertEquals(.03d, newInterestAccount.getInterestCharge());
    }

    @Test
    void testSaveCheckingAccount() {
        //Arrange
        Account checkingAccount = arrangeCheckingAccount();
        when(accountRepository.save(any(Account.class))).thenReturn(checkingAccount);

        //Act
        Account newCheckingAccount = accountService.saveAccount(checkingAccount);

        //Assert
        assertNotNull(newCheckingAccount);
        assertEquals(9, newCheckingAccount.getAcctNumber().length());
        assertEquals(100d, newCheckingAccount.getBalance());
        assertEquals(100d, newCheckingAccount.getMinimumBalance());
        assertEquals(10d, newCheckingAccount.getPenalty());
        assertEquals(1.0d, newCheckingAccount.getTransactionCharge());
    }

    @Test
    void testFindAllAccount() {
        //Arrange
        List<Account> mockListOfAccounts = new ArrayList<>();
        mockListOfAccounts.add(arrangeRegularAccount());
        mockListOfAccounts.add(arrangeInterestAccount());
        mockListOfAccounts.add(arrangeCheckingAccount());
        when(accountRepository.findAll()).thenReturn(mockListOfAccounts);

        //Act
        List<Account> listOfAccountsFromDb = accountService.findAllAccount();

        //Assert
        assertNotNull(listOfAccountsFromDb);
        assertEquals(3, listOfAccountsFromDb.size());
    }

    @Test
    void testFindAccountById() throws RecordNotFoundException {
        //Arrange
        long accId = 1L;
        Account account = arrangeRegularAccount();
        account.setId(accId);
        when(accountRepository.findById(accId)).thenReturn(Optional.of(account));

        //Act
        Account resultAccount = accountService.findAccountById(accId);

        //Assert
        verify(accountRepository).findById(accId);
        assertEquals(account, resultAccount);
    }

    @Test
    void testDeleteAccountById() throws RecordNotFoundException {
        //Arrange
        Long accId = 2L;
        when(accountRepository.existsById(accId)).thenReturn(true);

        //Act
        accountService.deleteAccount(2L);

        //Assert
        verify(accountRepository).deleteById(2L);
    }
}
