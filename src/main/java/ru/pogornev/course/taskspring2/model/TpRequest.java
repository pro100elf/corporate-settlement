package ru.pogornev.course.taskspring2.model;

import lombok.Getter;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
public class TpRequest {
    private Integer instanceId; // id
    private Long productCode; // -> product_code_id
    private String productType; // type
    private String contractNumber; // number
    private Integer priority;
    private Timestamp contractDate; // date_of_conclusion
    private Float interestRatePenalty; // penalty_rate
    private BigDecimal thresholdAmount; // threshold_amount
    private String rateType; // interest_rate_type
    private Float taxPercentageRate; // tax_rate
    private String productCodeName; // product_code
    private String branchCode;
    private String currencyCode;
    private String mdmCode;
    private String priorityCode;
}
