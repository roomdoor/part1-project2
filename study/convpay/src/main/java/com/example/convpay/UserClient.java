package com.example.convpay;

import com.example.convpay.dto.PayCancelRequest;
import com.example.convpay.dto.PayCancelResponse;
import com.example.convpay.dto.PayRequest;
import com.example.convpay.dto.PayResponse;
import com.example.convpay.service.ConveniencePayService;
import com.example.convpay.type.ConvenienceType;
import com.example.convpay.type.PayMethodType;

// 사용자
public class UserClient {
    public static void main(String[] args) {
        // 사용자 -> 편결이 -> 머니어뎁터
        ConveniencePayService conveniencePayService = new ConveniencePayService();

        PayResponse payResponse = conveniencePayService.pay(
                new PayRequest(PayMethodType.MONEY, ConvenienceType.G25, 1000));
        System.out.println(payResponse.toString());

        System.out.println("____________________________________________");
        PayCancelResponse payCancelResponse = conveniencePayService.payCancel(
                new PayCancelRequest(PayMethodType.CARD, ConvenienceType.G25, 100));
        System.out.println(payCancelResponse.toString());

    }
}
