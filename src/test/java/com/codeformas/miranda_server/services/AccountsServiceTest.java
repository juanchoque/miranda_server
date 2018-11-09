package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.codeformas.miranda_server.repository.AccountsRepository;
import com.codeformas.miranda_server.util.ConstantMiranda;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountsServiceTest {
    @Autowired
    private AccountsService accountsService;

    @Test
    public void listAll() {
        //Mockito.when(accountsRepository.findAll()).thenReturn(accounts);

        HashMap hashMap = accountsService.list(null);
        List<Accounts> acountsList = (ArrayList)hashMap.get(ConstantMiranda.OBJECT);

        acountsList.forEach(x -> System.out.println(x.getId()));

        //System.out.println(accountsListA.size());
        //System.out.println(accountsListB.size());

        //assertArrayEquals(accountsListA.toArray(), accountsListB.toArray());
    }

    @Test
    public void getById() {
    }

    @Test
    public void saveOrUpdate() {
        Accounts accounts = new Accounts();
        accounts.setEmail("martin.s2ewe017@gmail.com");

        HashMap hashMap = new HashMap();
        hashMap.put("MESSAGE", "");
        hashMap.put("STATUS",true);

        //ockito.when(accountsRepository.save(accounts)).thenReturn(accounts);

        //assertThat(accountsService.saveUpdate(accounts)).isEqualTo(hashMap);

        /*HashMap hashMap = accountsService.saveUpdate(accounts);
        Accounts accounts1 = (Accounts) hashMap.get(ConstantMiranda.OBJECT);

        System.out.println(",,,,,,,,,,,,,,,,,,,,," + accounts1);*/

        //assertThat(accounts).isEqualTo(accounts);
    }

    @Test
    public void delete() {
    }
}