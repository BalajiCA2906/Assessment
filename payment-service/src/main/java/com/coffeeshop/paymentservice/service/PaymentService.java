package com.coffeeshop.paymentservice.service;

import com.coffeeshop.paymentservice.entity.Payment;
import com.coffeeshop.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;


    public Payment doPayment(Payment payment) {
        boolean isPaymentRequired = true;
        if( payment.getPromoCode() != null){
            isPaymentRequired = applyPromoCode(payment);
        }
        if( isPaymentRequired && checkWallet(payment))
        payment.setPaymentStatus(getPaymentstatus()?"Success":"Fails");
        else
            payment.setPaymentStatus("Success");
        payment.setTransactionId(UUID.randomUUID().toString());
        return paymentRepository.save(payment);
    }

    public boolean checkWallet(Payment payment){
        BigDecimal walletBalance = new BigDecimal(5.00);
        payment.setMessage(" Wallet Debited.");
       return amountDetuction(payment, walletBalance);
    }

    private boolean getPaymentstatus() {
        return new Random().nextBoolean();
    }
    public boolean applyPromoCode(Payment payment) {
        if (payment.getPromoCode().equals("CAFE2OFF")) {
            BigDecimal promoOff = new BigDecimal(2.00);
            payment.setMessage(" Promo Applied Successfully.");
            return amountDetuction(payment,promoOff);
        }
        else {
            payment.setMessage(" Promo Code Not Valid.");
        }
        return true;
    }

    public boolean amountDetuction(Payment payment, BigDecimal deductionAmount) {
        if (payment.getAmount().compareTo(deductionAmount) == 1) {
            payment.setAmount(payment.getAmount().subtract(deductionAmount));
            return true;
        } else if (payment.getAmount().compareTo(deductionAmount) <= 0) {
            payment.setAmount(new BigDecimal(0.00));
            return false;
        }
        return true;
    }

//    public List<Payment> getPaymentByOrderID(int orderId) {
//        return paymentRepository.getByOrderId(orderId);
//    }
}
