package com.coffeeshop.orderservice.controller;

import com.coffeeshop.orderservice.common.Payment;
import com.coffeeshop.orderservice.common.TransactionRequest;
import com.coffeeshop.orderservice.common.TransactionResponse;
import com.coffeeshop.orderservice.entity.Order;
import com.coffeeshop.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("/order")
    public ResponseEntity<TransactionResponse> doOrder(@RequestBody TransactionRequest request) {
        return orderService.saveOrder(request);
    }
}
