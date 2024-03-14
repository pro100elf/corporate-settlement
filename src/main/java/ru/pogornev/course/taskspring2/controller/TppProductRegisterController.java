package ru.pogornev.course.taskspring2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.pogornev.course.taskspring2.model.TprRequest;
import ru.pogornev.course.taskspring2.model.TprResponse;
import ru.pogornev.course.taskspring2.service.TppProductRegisterService;

@RestController
public class TppProductRegisterController {
    @Autowired
    private TppProductRegisterService tppProductRegisterService;

    @RequestMapping("/corporate-settlement-account/create")
    @ResponseBody
    public TprResponse createCorporateSettlementAccount(@RequestBody TprRequest tppProductRegister) {
        var tprResponse = new TprResponse();
        tppProductRegisterService.validate(tppProductRegister);
        tprResponse.setAccountId(tppProductRegisterService.tppProductRegisterInsert(tppProductRegister));
        return tprResponse;
    }
}
