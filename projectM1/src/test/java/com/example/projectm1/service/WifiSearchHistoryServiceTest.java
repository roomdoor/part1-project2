package com.example.projectm1.service;

import com.example.projectm1.dto.WIfiSearchHistoryDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class WifiSearchHistoryServiceTest {
    WifiSearchHistoryService wifiSearchHistoryService = new WifiSearchHistoryService();

    @DisplayName("1. insert, get list")
    @Test
    public void test_1() {
        for (int i = 0; i < 5; i++) {
            wifiSearchHistoryService.insert(WIfiSearchHistoryDto.builder()
                    .Id(i)
                    .lat(i)
                    .lnt(i)
                    .time(LocalDateTime.now().toString())
                    .build());
        }

        System.out.println(wifiSearchHistoryService.getList());
    }

    @DisplayName("2. delete")
    @Test
    public void test_2() {
        for (int i = 0; i < 5; i++) {
            wifiSearchHistoryService.delete(i);
        }
        System.out.println(wifiSearchHistoryService.getList());
    }
}