package com.bankingapp.Banking.App.in.Spring.Boot.controller;

import com.bankingapp.Banking.App.in.Spring.Boot.model.Account;
import com.bankingapp.Banking.App.in.Spring.Boot.model.RegularAccount;
import com.bankingapp.Banking.App.in.Spring.Boot.service.AccountService;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void testCreateAccountSuccess() throws Exception {
        // Arrange
        Account account = new RegularAccount();
        account.setId(1L);

        // Mock the accountService behaviour
        when(accountService.saveAccount(any(Account.class))).thenReturn(account);

        // Act and Assert
        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(account)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(account)));
    }

    @Test
    public void testGetAllAccountSuccess() throws Exception {
        // Arrange
        List<Account> accountList = new ArrayList<>();
        accountList.add(new RegularAccount());
        //Mock the accountService behavior
        when(accountService.findAllAccount()).thenReturn(accountList);

        // Act and Assert
        mockMvc.perform(get("/account")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(accountList)));
    }

    @Test
    public void testGetAnAccountSuccess() throws Exception {
        // Arrange
        long accId = 1L;
        Account account = new RegularAccount();
        account.setId(accId);
        //Mock the accountService behavior
        when(accountService.findAccountById(accId)).thenReturn(account);

        // Act and Assert
        mockMvc.perform(get("/account/{accId}", accId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(account)));
    }

    @Test
    public void testDeleteAccountSuccess() throws Exception {
        // Arrange
        long accId = 1L;

        // Act and Assert
        mockMvc.perform(delete("/account/{accId}", accId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(accountService, times(1)).deleteAccount(accId);
    }

    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
