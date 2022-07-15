package com.example.convpay.service;

import com.example.convpay.dto.PayCancelRequest;
import com.example.convpay.dto.PayCancelResponse;
import com.example.convpay.dto.PayRequest;
import com.example.convpay.dto.PayResponse;
import com.example.convpay.type.*;

public class ConveniencePayService {    // 편결이

    private final MoneyAdapter moneyAdapter = new MoneyAdapter();
    private final CardAdapter cardAdapter = new CardAdapter();
    private PaymentInterface paymentInterface;
    private final DiscountInterface discountInterface = new DiscountByPayMethode();

    // 결제
    public PayResponse pay(PayRequest payRequest) {
        if (payRequest.getPayMethodType() == PayMethodType.CARD) {
            paymentInterface = cardAdapter;
        } else if (payRequest.getPayMethodType() == PayMethodType.MONEY) {
            paymentInterface = moneyAdapter;
        }

        Integer discountedAmount = discountInterface.getDiscountedAmount(payRequest);
        PaymentResult paymentResult = paymentInterface.paymentResult(discountedAmount);

        // fail case 1
        if (paymentResult == PaymentResult.PAYMENT_FAIL) {
            return new PayResponse(PayResult.FAIL, 0);
        }
        // fail case 2
        // fail case 3
        // fail case 4

        // success case
        return new PayResponse(PayResult.SUCCESS, discountedAmount);
    }


    // 결제 취소
    public PayCancelResponse payCancel(PayCancelRequest payCancelRequest) {
        MoneyUseCancelResult moneyUseCancelResult = moneyAdapter.useCancel(payCancelRequest.getPayCancelAmount());
        if (payCancelRequest.getPayMethodType() == PayMethodType.CARD) {
            paymentInterface = cardAdapter;
        } else if (payCancelRequest.getPayMethodType() == PayMethodType.MONEY) {
            paymentInterface = moneyAdapter;
        }

        CancelPaymentResult cancelPaymentResult = paymentInterface.paymentCancelResult(payCancelRequest.getPayCancelAmount());

        if (cancelPaymentResult == CancelPaymentResult.CANCEL_PAYMENT_FAIL) {
            return new PayCancelResponse(PayCancelResult.CANCEL_FAIL, 0);
        }

        return new PayCancelResponse(PayCancelResult.CANCEL_SUCCESS, payCancelRequest.getPayCancelAmount());
    }
}
