package com.example.websample.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController()
public class SampleController {

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String id) throws IllegalAccessException {
        log.info("Get some order information : " + id);

        if (id.equals("500")) {
            throw new IllegalAccessException("500 is not valid orderId.");
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
