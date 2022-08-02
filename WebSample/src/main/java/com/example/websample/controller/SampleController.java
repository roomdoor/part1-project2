package com.example.websample.controller;

import com.example.websample.Exception.ErrorCode;
import com.example.websample.Exception.WebSampleException;
import com.example.websample.dto.ErrorResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestController()
public class SampleController {

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String id) throws IllegalAccessException, SQLIntegrityConstraintViolationException {
        log.info("Get some order information : " + id);

        if (id.equals("500")) {
            throw new WebSampleException(
                    ErrorCode.TOO_BIG_ID_ERROR
                    , "500 is too big orderId."
            );
        }

        if (id.equals("3")) {
            throw new WebSampleException(
                    ErrorCode.TOO_SMALL_ID_ERROR
                    , "3 is too small orderId."
            );
        }

        if (id.equals("4")) {
            throw new SQLIntegrityConstraintViolationException(
                    "Duplicated insertion was tired"
            );
        }


        return "oderId : " + id + ", orderAmount : 100";
    }

    @GetMapping("/order")
    public String getOrderWithRequestParam(
            @RequestParam(value = "orderId", required = false, defaultValue = "defaultId") String id,
            @RequestParam("orderAmount") Integer amount) {

        log.info("Get some order information : " + id + ", amount : " + amount);
        return "oderId : " + id + ", orderAmount : " + amount;
    }

    @DeleteMapping("/order/{orderId}")
    public String getOrderWithRequestParam(@PathVariable("orderId") String id) {
        log.info("Deleted orderId information : " + id);
        return "deleted oderId : " + id + ";";
    }

    @PostMapping("/order")
    public String createOrder(
            @RequestBody CreateOrderRequest createOrderRequest, @RequestHeader String userAccountId) {
        log.info("Create order : " + createOrderRequest + ", userAccountId : " + userAccountId);
        return "order created -> oderId : " + createOrderRequest.getOrderId() + ", orderAmount : " + createOrderRequest.getOrderAmount();
    }

    @PutMapping("/order")
    public String createOrder() {
        log.info("Create order");
        return "order created -> oderId : 1, orderAmount : 100";
    }


    @Data
    public static class CreateOrderRequest {
        private String orderId;
        private Integer orderAmount;
    }


}
