package com.coffeeshop.orderservice.service;

import com.coffeeshop.orderservice.common.OrderServiceResponse;
import com.coffeeshop.orderservice.common.Payment;
import com.coffeeshop.orderservice.common.TransactionRequest;
import com.coffeeshop.orderservice.common.TransactionResponse;
import com.coffeeshop.orderservice.entity.Order;
import com.coffeeshop.orderservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<TransactionResponse> saveOrder(TransactionRequest request){
        TransactionResponse transactionResponse = new TransactionResponse();
        OrderServiceResponse orderServiceResponse = new OrderServiceResponse();
        orderServiceResponse = submitOrder(request);
        transactionResponse.setData(orderServiceResponse);
        return ResponseEntity.status(HttpStatus.OK).body(transactionResponse);
    }
    public OrderServiceResponse submitOrder(TransactionRequest request){

        Order order =  request.getOrder();
        Payment payment = request.getPayment();
        if(payment != null) {
            payment.setOrderId(order.getId());
            payment.setAmount(order.getPrice());
        }
        //Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment",payment, Payment.class);
        Payment paymentResponse =paymentcall(payment);
        OrderServiceResponse orderServiceResponse = new OrderServiceResponse();
        orderServiceResponse.setOrder(order);
        orderServiceResponse.setAmount(paymentResponse.getAmount());
        orderServiceResponse.setTansactionId(payment.getTransactionId());
        orderServiceResponse.setMessage(paymentResponse.getPaymentStatus() +" "+ paymentResponse.getMessage());
        orderRepository.save(order);
        return orderServiceResponse;
        //transactionResponse;
    }

    @CircuitBreaker(name="PAYMENT-SERVICE", fallbackMethod = "paymentFallBack")
    public Payment paymentcall (Payment payment){
        //ResponseEntity.status(ExpiresFilter.XHttpServletResponse.)
        if(restTemplate == null)
            restTemplate =new RestTemplate();
        return restTemplate.postForObject("http://PAYMENT-SERVICE/payments/doPayment",payment, Payment.class);
    }

    public Payment paymentFallback (Exception ex){
        Payment payment = new Payment();
        payment.setPaymentStatus("Failed");
        payment.setPaymentStatus(UUID.randomUUID().toString());
        return payment;
    }
}
