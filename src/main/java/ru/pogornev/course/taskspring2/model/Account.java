package ru.pogornev.course.taskspring2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Account {
    @Id
    private Long id;
    private Long accountPoolId;
    private String accountNumber;
    // from pool
    private String branchCode;
    private String currencyCode;
    private String mdmCode;
    private String priorityCode;
    private String registryTypeCode;
}
