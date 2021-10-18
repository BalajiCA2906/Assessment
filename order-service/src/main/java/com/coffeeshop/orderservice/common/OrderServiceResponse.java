package com.coffeeshop.orderservice.common;

import com.coffeeshop.orderservice.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceResponse {

    private Order order;
    private BigDecimal amount;
    private  String TansactionId;
    private String message;
}
