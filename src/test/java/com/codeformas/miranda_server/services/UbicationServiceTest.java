package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.repository.UbicationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class UbicationServiceTest {

    @InjectMocks
    IUbicationService iUbicationService;

    @Mock
    UbicationRepository ubicationRepository;

    @Test
    public void listAll() {
        iUbicationService.listAll();
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