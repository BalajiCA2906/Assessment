package com.coffeeshop.paymentservice.controller;

import com.coffeeshop.paymentservice.entity.Payment;
import com.coffeeshop.paymentservice.exceptions.OrderNotFound;
import com.coffeeshop.paymentservice.repository.PaymentRepository;
import com.coffeeshop.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment){
        return paymentService.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment getPaymentDetails(@PathVariable int orderId){
        return this.paymentRepository.findById(orderId).orElseThrow(() ->
                new OrderNotFound("Payment not Found with Order Id :" + orderId));
    }
}
