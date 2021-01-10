package com.atm.dao;

import com.atm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
@CrossOrigin()
public interface RoleRepository extends JpaRepository<Role,Long>
{
    Role findRoleByRole(String role);
}