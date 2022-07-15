package com.example.convpay.service;

import com.example.convpay.type.CancelPaymentResult;
import com.example.convpay.type.CardUseCancelResult;
import com.example.convpay.type.CardUseResult;
import com.example.convpay.type.PaymentResult;

public class CardAdapter implements PaymentInterface{
    // 1. 인증
    public void authorization() {
        System.out.println("authorization success");
    }

    // 2. 승인
    public void approval() {
        System.out.println("approval success");
    }

    // 3. 매입
    public CardUseResult capture(Integer payAmount) {
        // 실패
        if (payAmount > 100) {
            return CardUseResult.USE_FAIL;
        }

        // 성공
        return CardUseResult.USE_SUCCESS;
    }

    // 4. 매입 취소
    public CardUseCancelResult cancelCapture(Integer payCancelAmount) {
        // 실패
        if (payCancelAmount < 1000) {
            return CardUseCancelResult.USE_CANCEL_FAIL;
        }

        // 성공
        return CardUseCancelResult.USE_CANCEL_SUCCESS;
    }

    @Override
    public PaymentResult paymentResult(Integer payAmount) {
        authorization();
        approval();
        CardUseResult cardUseResult = capture(payAmount);

        if (cardUseResult == CardUseResult.USE_FAIL) {
            return PaymentResult.PAYMENT_FAIL;
        }
        return PaymentResult.PAYMENT_SUCCESS;
    }

    @Override
    public CancelPaymentResult paymentCancelResult(Integer payCancelAmount) {
        CardUseCancelResult cardUseCancelResult = cancelCapture(payCancelAmount);

        if (cardUseCancelResult == CardUseCancelResult.USE_CANCEL_FAIL) {
            return CancelPaymentResult.CANCEL_PAYMENT_FAIL;
        }

        return CancelPaymentResult.CANCEL_PAYMENT_SUCCESS;
    }
}
