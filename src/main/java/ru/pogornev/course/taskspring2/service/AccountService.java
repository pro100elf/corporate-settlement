package ru.pogornev.course.taskspring2.service;

import ru.pogornev.course.taskspring2.model.Account;
import java.util.List;

public interface AccountService {
    List<Account> findAll();
}
