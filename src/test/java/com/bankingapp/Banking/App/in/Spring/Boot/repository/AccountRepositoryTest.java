package com.bankingapp.Banking.App.in.Spring.Boot.repository;

import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;
import com.bankingapp.Banking.App.in.Spring.Boot.model.RegularAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testSaveAccount() {
        // Arrange
        Account account = new RegularAccount();
        account.setName("John Smith");

        // Act
        Account savedAccount = accountRepository.save(account);

        // Assert
        assertThat(savedAccount.getId()).isNotNull();
        assertThat(savedAccount.getName()).isEqualTo(account.getName());

        Account foundAccount = entityManager.find(Account.class, savedAccount.getId());
        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount.getName()).isEqualTo(account.getName());
    }

    @Test
    public void testFindAllAccount() {
        // Arrange
        Account account1 = new RegularAccount();
        account1.setName("John Smith");

        Account account2 = new RegularAccount();
        account2.setName("Jane Doe");

        entityManager.persist(account1);
        entityManager.persist(account2);
        entityManager.flush();

        // Act
        List<Account> accountList = accountRepository.findAll();

        // Assert
        assertEquals(2, accountList.size());
        assertThat(accountList).contains(account1, account2);
    }

    @Test
    public void testFindAccountById() {
        // Arrange
        Account account = new RegularAccount();
        account.setName("John Smith");

        entityManager.persist(account);
        entityManager.flush();

        // Act
        Account foundAccount = accountRepository.findById(account.getId()).orElse(null);

        // Assert
        assertThat(foundAccount).isNotNull();
        assertThat(foundAccount.getName()).isEqualTo(account.getName());
    }

    @Test
    public void testDeleteAccount() {
        // Arrange
        Account account = new RegularAccount();
        account.setName("John Smith");

        entityManager.persist(account);
        entityManager.flush();

        // Act
        accountRepository.deleteById(account.getId());

        // Assert
        Account foundAccount = entityManager.find(Account.class, account.getId());
        assertThat(foundAccount).isNull();
    }

}
