package com.coffeeshop.orderservice.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private String paymentStatus;
    private String transactionId;
    private int orderId;
    private BigDecimal amount;
    private String promoCode;
    private String message;
}

