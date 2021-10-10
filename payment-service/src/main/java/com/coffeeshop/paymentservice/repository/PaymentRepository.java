package com.coffeeshop.paymentservice.repository;

import com.coffeeshop.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    List <Payment> getByOrderId(int orderId);
}
