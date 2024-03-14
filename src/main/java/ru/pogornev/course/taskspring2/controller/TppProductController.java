package ru.pogornev.course.taskspring2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pogornev.course.taskspring2.model.TpRequest;
import ru.pogornev.course.taskspring2.model.TpResponse;
import ru.pogornev.course.taskspring2.service.ProductService;

@RestController
public class TppProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/corporate-settlement-instance/create")
    public TpResponse createCorporateSettlementInstance(@RequestBody TpRequest tpRequest) {
        productService.validate(tpRequest);
//        if ( tpRequest.getInstanceId() != null && tpRequest.getInstanceId() > 0 )  {
//            // уходим на добавление доп.соглашения
//            // supplementaryAgreementId в Response
//        }
        return productService.productInsert(tpRequest);
    }
}
