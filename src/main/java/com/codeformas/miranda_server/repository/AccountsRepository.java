package com.codeformas.miranda_server.repository;

import com.codeformas.miranda_server.model.domain.Accounts;
import org.springframework.data.repository.CrudRepository;

public interface AccountsRepository extends CrudRepository<Accounts, Integer> {
}
