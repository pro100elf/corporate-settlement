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
    private Long productId;
    private String type;
    private Long account;
    private String currencyCode;
    private String state;
    private String accountNumber;
}
