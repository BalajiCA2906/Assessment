package com.coffeeshop.orderservice.controller;

import com.coffeeshop.orderservice.common.Payment;
import com.coffeeshop.orderservice.common.TransactionRequest;
import com.coffeeshop.orderservice.common.TransactionResponse;
import com.coffeeshop.orderservice.entity.Order;
import com.coffeeshop.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/saveOrder")
    public TransactionResponse doOrder(@RequestBody TransactionRequest request) {
        TransactionResponse transactionResponse = orderService.saveOrder(request);
        return transactionResponse;
    }
}
