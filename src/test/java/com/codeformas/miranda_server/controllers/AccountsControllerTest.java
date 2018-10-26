package com.codeformas.miranda_server.controllers;

import com.codeformas.miranda_server.repository.AccountsRepository;
import com.codeformas.miranda_server.services.AccountsService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccountsService accountsService;

    @InjectMocks
    private AccountsController accountsController;

    @Before
    public void setUp() throws Exception {
        Mockito.reset(accountsService);
        mockMvc = MockMvcBuilders
                //.standaloneSetup(accountsController)
                .standaloneSetup(new AccountsController())
                .build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setAccountsService() {
    }

    @Test
    public void listAccounts() {
        String json = "{\n" +
                        "}";
        try {
            mockMvc.perform(post("/accounts/list")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.*", Matchers.hasSize(2)));

            /*mockMvc.perform(post("/accounts/list")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title", Matchers.is("Greetings")))
                    .andExpect(jsonPath("$.value", Matchers.is("Hello World")))
                    .andExpect(jsonPath("$.*", Matchers.hasSize(2)));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addAccounts() {
    }

    @Test
    public void deleteAccounts() {
    }
}