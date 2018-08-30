package com.codeformas.miranda_server.repository;

import com.codeformas.miranda_server.model.domain.GroupAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupAccountRepository extends CrudRepository<GroupAccount, Integer> {
    @Query("FROM GroupAccount ga where ga.accounts.id = :idAccount AND ga.groups.id = :idGroup")
    GroupAccount getByAccountGroup(@Param("idAccount")Integer idAccount, @Param("idGroup")Integer idGroup);

    @Query("FROM GroupAccount ga where ga.accounts.imei = :imei")
    List<GroupAccount> findByImei(@Param("imei")String imei);
}

