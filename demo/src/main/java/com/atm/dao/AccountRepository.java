package com.atm.dao;

import com.atm.entity.Account;
import com.atm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin
public interface AccountRepository extends JpaRepository<Account, Long>
{
    public Account findAccountById(Long id);
}
