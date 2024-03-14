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
    private Long product_code_id;
    private String type;
    private String number;
    private Integer priority;
    private Timestamp date_of_conclusion;
    private Float penalty_rate;
    private BigDecimal threshold_amount;
    private String interest_rate_type;
    private Float tax_rate;
}
