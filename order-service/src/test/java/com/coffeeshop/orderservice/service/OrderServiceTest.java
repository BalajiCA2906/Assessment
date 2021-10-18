package com.coffeeshop.orderservice.service;

import com.coffeeshop.orderservice.common.OrderServiceResponse;
import com.coffeeshop.orderservice.common.Payment;
import com.coffeeshop.orderservice.common.TransactionRequest;
import com.coffeeshop.orderservice.common.TransactionResponse;
import com.coffeeshop.orderservice.entity.Order;
import com.coffeeshop.orderservice.repository.OrderRepository;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderRepository osr = Mockito.mock(OrderRepository.class);
    @Autowired
    private RestTemplate restTemplate;
    @Test
    void checkOrderMockResponse() {
        OrderServiceResponse expectedResponse = new OrderServiceResponse();
        OrderService os = new OrderService();
        TransactionRequest request = new TransactionRequest();
        Order o = new Order();
        o.setId(1005);
        o.setItem("Coffee");
        o.setPrice(BigDecimal.valueOf(8.00));
        o.setQnty(1);
        o.setCounter(1);
        request.setOrder(o);
        os.submitOrder(request);
        Payment pReq = new Payment();
        Payment pRes = new Payment();
        pRes.setPaymentStatus("Success");
        pRes.setAmount(BigDecimal.valueOf(8.00));
        Mockito.when(os.paymentcall(pReq)).thenReturn(pRes);
        expectedResponse.setAmount(BigDecimal.valueOf(8.00));
        OrderServiceResponse ActualResponse = new OrderServiceResponse();
        ActualResponse =  os.submitOrder(request);
        Assertions.assertThat(ActualResponse.getOrder().getId()).isEqualTo(expectedResponse.getOrder().getId());
        Assertions.assertThat(ActualResponse.getOrder().getPrice()).isEqualTo(expectedResponse.getOrder().getPrice());
    }
}