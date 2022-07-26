package com.example.projectm1.service;

import com.example.projectm1.dto.WIfiSearchHistoryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class WifiSearchHistoryServiceTest {
    WifiSearchHistoryService wifiSearchHistoryService = new WifiSearchHistoryService();

    @DisplayName("1. insert, get list, delete")
    @Test
    public void test_1() {
        for (int i = 0; i < 5; i++) {
            wifiSearchHistoryService.insert(WIfiSearchHistoryDto.builder()
                    .lat(i)
                    .lnt(i)
                    .time(LocalDateTime.now().toString())
                    .build());
        }
        List<WIfiSearchHistoryDto> list = wifiSearchHistoryService.getList();
        System.out.println(list);
        Assertions.assertEquals(5, list.size());

//        for (int i = 1; i <= 5; i++) {
//            wifiSearchHistoryService.delete(i);
//        }

        list = wifiSearchHistoryService.getList();
        System.out.println(list);
        Assertions.assertEquals(0, list.size());
    }

    @DisplayName("2. find id, delete")
    @Test
    public void test_2() {
        int id = wifiSearchHistoryService.findId(0, 0);
        wifiSearchHistoryService.delete(id);
    }
}