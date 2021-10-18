package com.coffeeshop.paymentservice.service;

import com.coffeeshop.paymentservice.entity.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest {

    @Test
    void checkPromoIsSuccessFullOrFailed() {
        PaymentService paymentService = new PaymentService();
        BigDecimal promoAmount = new BigDecimal(2.00);
        Payment p = new Payment();
        p.setAmount(BigDecimal.valueOf(8.00));
        p.setPromoCode("CAFE2OFF");
        Assertions.assertTrue(paymentService.applyPromoCode(p));
        Assertions.assertTrue(p.getMessage().contains("Promo Applied Successfully"));
        Assertions.assertTrue(paymentService.checkWallet(p));
    }

}