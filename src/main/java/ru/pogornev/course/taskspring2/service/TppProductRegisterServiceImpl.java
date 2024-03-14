package ru.pogornev.course.taskspring2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.pogornev.course.taskspring2.model.TppProductRegister;
import ru.pogornev.course.taskspring2.model.TprRequest;
import ru.pogornev.course.taskspring2.repository.AccountRepo;
import ru.pogornev.course.taskspring2.repository.TppProductRegisterRepo;
import java.util.List;

@Service
public class TppProductRegisterServiceImpl implements TppProductRegisterService {
    @Autowired
    private TppProductRegisterRepo tppProductRegisterRepo;
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public long tppProductRegisterInsert(TprRequest request) {
        try {
            List<Object[]> accAttrList = accountRepo.getAccountByRequest(request.getBranchCode(), request.getCurrencyCode(), request.getMdmCode()
                    , request.getPriorityCode(), request.getRegistryTypeCode());

            var newTppProductRegister = new TppProductRegister();
            newTppProductRegister.setProduct_id(request.getInstanceId());
            newTppProductRegister.setType(request.getRegistryTypeCode());
            newTppProductRegister.setAccount((long)accAttrList.get(0)[0]);
            newTppProductRegister.setCurrency_code(request.getCurrencyCode());
            newTppProductRegister.setState("OPEN");
            newTppProductRegister.setAccount_number((String) accAttrList.get(0)[1]);
            tppProductRegisterRepo.save(newTppProductRegister);
            return newTppProductRegister.getId();
        }
        catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.toString());
        }
    }

    @Override
    public void validate(TprRequest request) {
        if ( request.getInstanceId() <= 0 )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Неверный или не задан instanceId ("
                    + request.getInstanceId() + ")");

        if ( tppProductRegisterRepo.existTpr(request.getInstanceId(), request.getRegistryTypeCode()) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST
                    , "Параметр registryTypeCode тип регистра <"
                      + request.getRegistryTypeCode() +"> уже существует для ЭП с ИД <"
                      + request.getInstanceId() + ">");

        if ( !tppProductRegisterRepo.existTprType(request.getRegistryTypeCode()) )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND
                    , "Код Продукта  <"
                      + request.getRegistryTypeCode()
                      + "> не найден в Каталоге продуктов <tpp_ref_product_register_type>для данного типа Регистра");
    }
}
