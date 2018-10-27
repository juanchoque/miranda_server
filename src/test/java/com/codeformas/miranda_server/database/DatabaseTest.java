package com.codeformas.miranda_server.database;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.codeformas.miranda_server.repository.AccountsRepository;
import com.sun.glass.ui.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTest {

    @Autowired
    AccountsRepository accountsRepository;

    @Test
    public void create(){
        Accounts accounts = new Accounts();
        accounts.setId(12);
        accounts.setName("Esta es una prueba");
        accountsRepository.save(accounts);

        assertNotNull(accounts);
        //assertEquals("", "");
    }

}
