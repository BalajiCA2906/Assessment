package com.coffeeshop.orderservice.common;

import com.coffeeshop.orderservice.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private OrderServiceResponse data;
}
