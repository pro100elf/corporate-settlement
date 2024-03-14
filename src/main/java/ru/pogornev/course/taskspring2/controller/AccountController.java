package ru.pogornev.course.taskspring2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pogornev.course.taskspring2.service.AccountService;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/corporate-settlement-account/accounts")
    public String getAllAccounts(Model model) {
        model.addAttribute("accounts", accountService.findAll());
        return "accounts";
    }
}
