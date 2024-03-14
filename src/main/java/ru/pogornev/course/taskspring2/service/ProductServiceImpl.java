package ru.pogornev.course.taskspring2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.pogornev.course.taskspring2.model.*;
import ru.pogornev.course.taskspring2.repository.ProductRepo;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private TppProductRegisterService tppProductRegisterService;

    @Override
    public TpResponse productInsert(TpRequest request) {
        try {
//            List<Object[]> accAttrList = accountRepo.getAccountByRequest(request.getBranchCode(), request.getCurrencyCode(), request.getMdmCode()
//                    , request.getPriorityCode(), request.getRegistryTypeCode());

            var newTppProduct = new TppProduct();
            newTppProduct.setProduct_code_id(request.getProductCode());
            newTppProduct.setType(request.getProductType());
            newTppProduct.setType(request.getProductType());
            newTppProduct.setNumber(request.getContractNumber());
            newTppProduct.setPriority(request.getPriority());
            newTppProduct.setDate_of_conclusion(request.getContractDate());
            newTppProduct.setPenalty_rate(request.getInterestRatePenalty());
            newTppProduct.setThreshold_amount(request.getThresholdAmount());
            newTppProduct.setInterest_rate_type(request.getRateType());
            newTppProduct.setTax_rate(request.getTaxPercentageRate());
            productRepo.save(newTppProduct);

            List<Object[]> regTypeList = productRepo.getRegistersByProductCode(request.getProductCodeName());
            List<Long> regIds = new ArrayList<>();
            regTypeList.forEach(objects -> {
                var tprRequest = new TprRequest();
                tprRequest.setInstanceId(newTppProduct.getId());
                tprRequest.setRegistryTypeCode((String) objects[0]);
                tprRequest.setBranchCode(request.getBranchCode());
                tprRequest.setCurrencyCode(request.getCurrencyCode());
                tprRequest.setMdmCode(request.getMdmCode());
                tprRequest.setPriorityCode(request.getPriorityCode());
                var regId = tppProductRegisterService.tppProductRegisterInsert(tprRequest);
                regIds.add(regId);
            });

            var tpResponse = new TpResponse();
            tpResponse.setInstanceId(newTppProduct.getId());
            tpResponse.setRegisterId(regIds);
            return tpResponse;
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.toString());
        }
    }

    @Override
    public void validate(TpRequest request) {
        if ( request.getProductCode() == null )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "productCode не заполнен");
        if ( request.getProductType() == null )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "productType не заполнен");
        if ( request.getContractNumber() == null )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "contractNumber не заполнен");
        if ( request.getPriority() == null )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "priority не заполнено");
        if ( request.getContractDate() == null )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "contractDate не заполнено");

        var existsId = productRepo.existsTp(request.getContractNumber());
        if ( ( existsId != null ) && ( existsId > 0 ) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Договор № "
                    + request.getContractNumber() +" (ContractNumber) уже существует в Продуктах (id=" + existsId + ")");
    }
}
