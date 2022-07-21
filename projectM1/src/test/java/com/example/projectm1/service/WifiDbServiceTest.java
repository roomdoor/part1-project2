package com.example.projectm1.service;

import com.example.projectm1.dto.WifiDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WifiDbServiceTest {
    WifiDbService wifiDbService = new WifiDbService();

    @DisplayName("1. insert, getList")
    @Test
    public void test_1() {
        for (int i = 0; i < 5; i++) {
            wifiDbService.insert(WifiDto.builder()
                    .X_SWIFI_MGR_NO("1")
                    .build());
        }

        System.out.println(wifiDbService.getListForCurDis());
    }

    @DisplayName("2. $END#")
    @Test
    public void test_2(){
        //given
        //when
        //then
    }
}