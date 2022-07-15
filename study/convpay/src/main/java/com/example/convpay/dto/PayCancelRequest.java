package com.example.convpay.dto;

import com.example.convpay.type.ConvenienceType;

public class PayCancelRequest {
    // 편의점 종류
    ConvenienceType convenienceType;

    // 결제 금액
    Integer cancelPayAmount;

    public PayCancelRequest(ConvenienceType convenienceType, Integer cancelPayAmount) {
        this.convenienceType = convenienceType;
        this.cancelPayAmount = cancelPayAmount;
    }

    public ConvenienceType getConvenienceType() {
        return convenienceType;
    }

    public void setConvenienceType(ConvenienceType convenienceType) {
        this.convenienceType = convenienceType;
    }

    public Integer getCancelPayAmount() {
        return cancelPayAmount;
    }

    public void setCancelPayAmount(Integer cancelPayAmount) {
        this.cancelPayAmount = cancelPayAmount;
    }
}
