package com.coffeeshop.paymentservice.controller;

import com.coffeeshop.paymentservice.entity.Payment;
import com.coffeeshop.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment){
        return paymentService.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public List<Payment> getPayment(@PathVariable int orderId){
        return paymentService.getPaymentByOrderID(orderId);
    }
}
