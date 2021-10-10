package com.coffeeshop.orderservice.service;

import com.coffeeshop.orderservice.common.Payment;
import com.coffeeshop.orderservice.common.TransactionRequest;
import com.coffeeshop.orderservice.common.TransactionResponse;
import com.coffeeshop.orderservice.entity.Order;
import com.coffeeshop.orderservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest request){

        Order order =  request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

       //Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment",payment, Payment.class);
        Payment paymentResponse =paymentcall(payment);
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setOrder(order);
        transactionResponse.setAmount(paymentResponse.getAmount());
        transactionResponse.setTansactionId(payment.getTransactionId());
        transactionResponse.setMessage(paymentResponse.getPaymentStatus());
        orderRepository.save(order);
        return  transactionResponse;
    }

    @CircuitBreaker(name="payment", fallbackMethod = "paymentFallBack")
    public Payment paymentcall (Payment payment){
        return restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment",payment, Payment.class);
    }

    public Payment paymentFallback (Exception ex){
        Payment payment = new Payment();
        payment.setPaymentStatus("Failed");
        payment.setPaymentId(new Random().nextInt());
        payment.setPaymentStatus(UUID.randomUUID().toString());
        return payment;
    }
}
