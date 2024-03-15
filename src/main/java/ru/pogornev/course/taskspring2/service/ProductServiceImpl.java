package ru.pogornev.course.taskspring2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.pogornev.course.taskspring2.model.*;
import ru.pogornev.course.taskspring2.repository.AgreementRepo;
import ru.pogornev.course.taskspring2.repository.ProductRepo;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private AgreementRepo agreementRepo;
    @Autowired
    private TppProductRegisterService tppProductRegisterService;

    @Override
    public TpResponse productInsert(TpRequest request) {
        try {
            var newTppProduct = new TppProduct();
            newTppProduct.setProductCodeId(request.getProductCode());
            newTppProduct.setType(request.getProductType());
            newTppProduct.setNumber(request.getContractNumber());
            newTppProduct.setPriority(request.getPriority());
            newTppProduct.setDateOfConclusion(request.getContractDate());
            newTppProduct.setPenaltyRate(request.getInterestRatePenalty());
            newTppProduct.setThresholdAmount(request.getThresholdAmount());
            newTppProduct.setInterestRateType(request.getRateType());
            newTppProduct.setTaxRate(request.getTaxPercentageRate());
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
    public TpResponse agreementInsert(TpRequest request) {
        var newAgreement = new Agreement();
        newAgreement.setProductId(request.getInstanceId());
        newAgreement.setArrangementType(request.getProductType());
        newAgreement.setNumber(request.getContractNumber());
        newAgreement.setOpeningDate(request.getContractDate());
        agreementRepo.save(newAgreement);
        var tpResponse = new TpResponse();
        tpResponse.setInstanceId(request.getInstanceId());
        tpResponse.setSupplementaryAgreementId(newAgreement.getId());
        return tpResponse;
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
