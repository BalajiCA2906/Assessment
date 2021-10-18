package com.coffeeshop.paymentservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="PAYMENT_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private String paymentStatus;
    private String transactionId;
    @Id
    private int orderId;
    private BigDecimal amount;
    private String promoCode;
    private String promoCodeMessage;
    private String message;
}
