package com.codeformas.miranda_server.controllers;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountsController accountsController;

    @Test
    public void setAccountsService() {
    }

    @Test
    public void listAccounts()  throws Exception {
        String URI = "/accounts/list";

        Accounts mockTicket = new Accounts();
        mockTicket.setEmail("martin.s2017@gmail.com");

        String inputInJson = this.mapToJson(mockTicket);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = null;

        result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(">>>>>>>>>>>>>>>>." + result);
        ///MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //System.out.println("resulta>>>>" + result);
        /*String expectedJson = this.mapToJson(ticketList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);*/
    }

    @Test
    public void addAccounts() throws Exception {
        Accounts mockTicket = new Accounts();
        mockTicket.setEmail("martin.s2017@gmail.com");

        String inputInJson = this.mapToJson(mockTicket);

        String URI = "/accounts/add";

        //Mockito.when(accountsService.saveUpdate(Mockito.any(Accounts.class))).thenReturn(mockTicket);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = null;

        result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        String outputInJson = response.getContentAsString();

        System.out.println(">>>>>>>>>>>>>>>>." + outputInJson);

        //assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    public void deleteAccounts() {
    }

    @Test
    public void setAccountsService1() {
    }

    /**
     * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
     */
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}