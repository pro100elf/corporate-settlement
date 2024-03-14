package ru.pogornev.course.taskspring2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TprRequest {
    private long instanceId; // product_id
    private String registryTypeCode; // type - tpp_ref_product_register_type.value
    private String branchCode; // account_pool -> account_number + account_id
    private String currencyCode;
    private String mdmCode;
    private String priorityCode;
}
