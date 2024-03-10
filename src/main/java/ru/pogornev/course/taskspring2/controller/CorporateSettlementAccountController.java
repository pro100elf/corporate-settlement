package ru.pogornev.course.taskspring2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pogornev.course.taskspring2.model.CorporateSettlementAccount;

@RestController
public class CorporateSettlementAccountController {
    @PostMapping("/corporate-settlement-account/create")
    public void createCorporateSettlementAccount(@RequestBody CorporateSettlementAccount account) {
        // Логика создания договора
    }
}
