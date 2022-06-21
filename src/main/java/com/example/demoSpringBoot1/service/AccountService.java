package com.example.demoSpringBoot1.service;

import com.example.demoSpringBoot1.entity.Account;
import com.example.demoSpringBoot1.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> fillAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long id){
        return accountRepository.findById(id);
    }

    public Account save(Account account){
        return accountRepository.save(account);
    }

    public void deleteById(Long id){
        accountRepository.deleteById(id);
    }
}
