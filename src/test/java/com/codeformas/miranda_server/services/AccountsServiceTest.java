package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.codeformas.miranda_server.repository.AccountsRepository;
import com.codeformas.miranda_server.util.ConstantMiranda;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountsServiceTest {

    @Autowired
    private AccountsRepository accountsRepository;


    @Test
    public void listAll() {
        List<Accounts> accountsListA = new ArrayList<>();
        this.accountsRepository.findAll().forEach(accountsListA::add);

        //HashMap hashMap = accountsService.listAll();
        //List<Accounts> accountsListB = (ArrayList)hashMap.get(ConstantMiranda.OBJECT);

        //System.out.println(accountsListA.size());
        //System.out.println(accountsListB.size());

        //assertArrayEquals(accountsListA.toArray(), accountsListB.toArray());
    }

    @Test
    public void getById() {
    }

    @Test
    public void saveOrUpdate() {
    }

    @Test
    public void delete() {
    }
}