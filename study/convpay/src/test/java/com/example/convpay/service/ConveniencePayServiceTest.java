package com.example.convpay.service;

import com.example.convpay.dto.PayRequest;
import com.example.convpay.dto.PayResponse;
import com.example.convpay.type.ConvenienceType;
import com.example.convpay.type.PayResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConveniencePayServiceTest {
    ConveniencePayService conveniencePayService = new ConveniencePayService();



    @DisplayName("1. pay FAIL")
    @Test
    public void test_2(){
        //given
        PayRequest payRequest = new PayRequest(ConvenienceType.G25, 100);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.FAIL, payResponse.getPayResult());
        assertEquals(100, payResponse.getPaidAmount());
    }

    @DisplayName("2. pay SUCCESS")
    @Test
    public void test_1(){
        //given
        PayRequest payRequest = new PayRequest(ConvenienceType.G25, 100);

        //when
        PayResponse payResponse = conveniencePayService.pay(payRequest);

        //then
        assertEquals(PayResult.SUCCESS, payResponse.getPayResult());
        assertEquals(100, payResponse.getPaidAmount());
    }

}