package com.codeformas.miranda_server.model.domain;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MirandaPojoTest {

    @Test
    public void testContructor() {
        Accounts accounts = new Accounts();
        Assert.assertEquals("23","23");

        Assertions.assertThat(accounts)
                .as("")
                .isNotNull();

        Assertions.assertThat("")
                .as("")
                .isNotBlank();
    }
}