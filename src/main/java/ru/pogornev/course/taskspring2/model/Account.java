package ru.pogornev.course.taskspring2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Account {
    @Id
    private Long id;
    private Long account_pool_id;
    private String account_number;
    // from pool
    private String branch_code;
    private String currency_code;
    private String mdm_code;
    private String priority_code;
    private String registry_type_code;
}
