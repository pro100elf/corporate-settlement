package ru.pogornev.course.taskspring2.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tpp_product_register")
public class TppProductRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long product_id;
    private String type;
    private Long account;
    private String currency_code;
    private String state;
    private String account_number;
}
