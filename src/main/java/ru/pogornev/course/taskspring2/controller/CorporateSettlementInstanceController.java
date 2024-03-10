package ru.pogornev.course.taskspring2.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pogornev.course.taskspring2.model.CorporateSettlementInstance;

@RestController
public class CorporateSettlementInstanceController {
    @PostMapping("/corporate-settlement-instance/create")
    public void createCorporateSettlementInstance(@RequestBody CorporateSettlementInstance instance) {
        // Логика создания продукта договора
    }
}
