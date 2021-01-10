package com.atm.service;

import com.atm.dao.UserRepository;
import com.atm.entity.User;
import com.atm.misc.UserDetailsAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsAuthenticationService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> user = userRepository.findUserByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException("not found: " + username));
        return user.map(UserDetailsAuth::new).get();
    }
}