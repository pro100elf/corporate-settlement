package ru.pogornev.course.taskspring2.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tpp_product")
public class TppProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long productCodeId;
    private String type;
    private String number;
    private Integer priority;
    private Timestamp dateOfConclusion;
    private Float penaltyRate;
    private BigDecimal thresholdAmount;
    private String interestRateType;
    private Float taxRate;
}
