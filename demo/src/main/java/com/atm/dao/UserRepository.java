package com.atm.dao;

import com.atm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findUserByUserName(String userName);
    Optional<User> findById(Long id);
}