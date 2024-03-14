package ru.pogornev.course.taskspring2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pogornev.course.taskspring2.model.Account;
import ru.pogornev.course.taskspring2.repository.AccountRepo;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public List<Account> findAll() {
        return (List<Account>) accountRepo.getAccounts();//findAll();
    }
}
