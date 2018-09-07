package com.codeformas.miranda_server.repository;

import com.codeformas.miranda_server.model.domain.Accounts;
import com.codeformas.miranda_server.model.domain.GroupAccount;
import com.codeformas.miranda_server.model.domain.Groups;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupsRepository extends CrudRepository<Groups, Integer> {

    @Query("FROM GroupAccount ga where ga.accounts.id = :id")
    List<GroupAccount> findAllByAccount(@Param("id")Integer idAccount);
}
