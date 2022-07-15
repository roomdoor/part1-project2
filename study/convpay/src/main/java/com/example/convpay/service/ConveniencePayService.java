package com.example.convpay.service;

import com.example.convpay.dto.PayCancelRequest;
import com.example.convpay.dto.PayCancelResponse;
import com.example.convpay.dto.PayRequest;
import com.example.convpay.dto.PayResponse;
import com.example.convpay.type.PayResult;

public class ConveniencePayService {
    public PayResponse pay(PayRequest payRequest) {
        return new PayResponse(PayResult.SUCCESS, 100);
    }

    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {
        return new PayCancelResponse();
    }
}
