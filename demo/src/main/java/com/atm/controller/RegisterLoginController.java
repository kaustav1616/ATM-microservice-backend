package com.atm.controller;

import com.atm.dao.RoleRepository;
import com.atm.dao.UserRepository;
import com.atm.entity.Role;
import com.atm.entity.User;
import com.atm.misc.UserDetailsAuth;
import com.atm.service.UserDetailsAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class RegisterLoginController
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserDetailsAuthenticationService userDetailsAuthenticationService;

    @GetMapping(value = "/login")
    public User login(@AuthenticationPrincipal UserDetailsAuth userDetailsAuth)
    {
        Optional<User> user = userRepository.findById(userDetailsAuth.getId());

        if(user.get() == null)
            return null;

        return user.get();
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth instanceof AnonymousAuthenticationToken) // check for not logged in user (anonymous user)
            return ("User not logged in.");

        new SecurityContextLogoutHandler().logout(request, response, auth);
        System.out.println("Logged out.");
        return ("Logout successful.");
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam("userName") String userName, @RequestParam("password") String password)
    {
        Optional<User> user = userRepository.findUserByUserName(userName);
        System.out.println("User: " + user);

        if(user.isPresent())
            return ("User name already exists. Choose another user name.");

        List<Role> roles = new ArrayList<>();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Slow Hashing based password encoding

        Role userRole = roleRepository.findRoleByRole("USER");
        roles.add(userRole);

        User newUser = new User();
        newUser.setUserName(userName);
        String encodedPassword = encoder.encode(password);
        newUser.setPassword(encodedPassword);
        newUser.setActive(true);
        newUser.setRoles(roles);
        newUser.setAccount(null);

        userRepository.save(newUser);
        return ("User registered successfully");
    }
}