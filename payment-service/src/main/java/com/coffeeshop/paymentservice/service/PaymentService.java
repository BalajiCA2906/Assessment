package com.coffeeshop.paymentservice.service;

import com.coffeeshop.paymentservice.entity.Payment;
import com.coffeeshop.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;


    public Payment doPayment(Payment payment) {
        payment.setPaymentStatus(getPaymentstatus()?"Success":"Fails");
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }

    private boolean getPaymentstatus() {
        return new Random().nextBoolean();
    }

    public List<Payment> getPaymentByOrderID(int orderId) {
        return paymentRepository.getByOrderId(orderId);
    }
}
