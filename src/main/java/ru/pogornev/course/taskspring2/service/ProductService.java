package ru.pogornev.course.taskspring2.service;

import ru.pogornev.course.taskspring2.model.TpRequest;
import ru.pogornev.course.taskspring2.model.TpResponse;

public interface ProductService {
    TpResponse productInsert(TpRequest tpRequest);
    TpResponse agreementInsert(TpRequest tpRequest);
    void validate(TpRequest request);
}
