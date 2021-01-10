package com.atm.controller;

import com.atm.dao.AccountRepository;
import com.atm.dao.UserRepository;
import com.atm.entity.Account;
import com.atm.entity.User;
import com.atm.misc.UserDetailsAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/operations")
@CrossOrigin
public class ATMUsageController
{
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/checkBalance")
    public Long checkBalance(@AuthenticationPrincipal UserDetailsAuth userDetailsAuth)
    {
        Long balance = Long.valueOf(0);
        Optional<User> user = userRepository.findById(userDetailsAuth.getId());

        if(user.get() == null)
            return balance;

        Account account = user.get().getAccount();
        balance = account.getAcctBalance();
        return balance;
    }

    @PostMapping(value = "/addMoney")
    public String addMoney(@AuthenticationPrincipal UserDetailsAuth userDetailsAuth,
                         @RequestParam("50") Long count50Notes,
                         @RequestParam("100") Long count100Notes,
                         @RequestParam("200") Long count200Notes,
                         @RequestParam("500") Long count500Notes,
                         @RequestParam("2000") Long count2000Notes)
    {
        Long totalBalance, newBalance, accountId;

        totalBalance = count50Notes * 50 + count100Notes * 100 + count200Notes * 200 + count500Notes * 500 + count2000Notes * 2000;

        if(totalBalance > 200000)
            return ("Deposit of Rs. 2,00,000 at a time exceeded. Please try again with an amount <= 2,00,000.");

        Optional<User> user = userRepository.findById(userDetailsAuth.getId());

        Account account = user.get().getAccount();
        accountId = account.getId();
        newBalance = account.getAcctBalance();
        newBalance += totalBalance;
        account.setAcctBalance(newBalance);
        accountRepository.save(account);

        account = accountRepository.findAccountById(accountId);
        return ("New balance = " + account.getAcctBalance());
    }

    @PostMapping(value = "/withdrawMoney")
    public String withdrawMoney(@AuthenticationPrincipal UserDetailsAuth userDetailsAuth,
                           @RequestParam("50") Long count50Notes,
                           @RequestParam("100") Long count100Notes,
                           @RequestParam("200") Long count200Notes,
                           @RequestParam("500") Long count500Notes,
                           @RequestParam("2000") Long count2000Notes)
    {
        Long totalBalance, newBalance, accountId;

        if(count50Notes + count100Notes + count200Notes + count500Notes + count2000Notes > 40)
            return ("Cannot withdraw more than 40 notes at a time. Please try again.");

        totalBalance = count50Notes * 50 + count100Notes * 100 + count200Notes * 200 + count500Notes * 500 + count2000Notes * 2000;

        if(totalBalance > 200000)
            return ("Withdrawal of Rs. 2,00,000 at a time exceeded. Please try again with an amount <= 2,00,000.");

        Optional<User> user = userRepository.findById(userDetailsAuth.getId());

        Account account = user.get().getAccount();
        accountId = account.getId();
        newBalance = account.getAcctBalance();
        newBalance -= totalBalance;
        account.setAcctBalance(newBalance);
        accountRepository.save(account);

        account = accountRepository.findAccountById(accountId);
        return ("New balance = " + account.getAcctBalance());
    }
}