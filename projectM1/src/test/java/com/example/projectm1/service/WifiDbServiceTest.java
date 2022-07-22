package com.example.projectm1.service;

import com.example.projectm1.dto.WifiDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

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

    @DisplayName("2. search near 20")
    @Test
    public void test_2() {
        // 1
        long before = System.currentTimeMillis();

        List<WifiDto> list = wifiDbService.getListForCurDis();


        //TODO lat, lnt 받아와서 넣기
        double lat = 126.97083534068472;
        double lnt = 37.55515236979944;

        // distance 계산을 위한 데이터 받아오기
        for (WifiDto dto : list) {
            dto.setDistance(WifiDto.curDis(lat, lnt,
                    dto.getLAT(), dto.getLNT()));
        }

        long after = System.currentTimeMillis();
        System.out.printf("%.2f" + "초 걸렸습니다.\n", (after - before) / (float) 1000);
        System.out.println(list.size());
        System.out.println();


        // 2
        before = System.currentTimeMillis();

        // 20개 추리기 위한 정렬
        Collections.sort(list);

        // 20 개 추린것만 db 에 업데이트
        String WORK_DTTM = LocalDate.now() + " " + LocalTime.now().withNano(0) + ".0";
        for (int i = 0; i < 20; i++) {
            WifiDto wifiDto = list.get(i);
            wifiDto.setWORK_DTTM(WORK_DTTM);
            wifiDbService.updateDistance(wifiDto);
        }

        after = System.currentTimeMillis();
        System.out.printf("%.2f" + "초 걸렸습니다.\n", (after - before) / (float) 1000);


        // 3
        before = System.currentTimeMillis();

        // 보여줄 20 개 데이터 가져옴
        List<WifiDto> listForShow20 = wifiDbService.getListForShow20(WORK_DTTM);
        int k = 0;
        for (WifiDto w : listForShow20) {
            System.out.println(k);
            System.out.println(w.toString());
            k++;
        }

        after = System.currentTimeMillis();
        System.out.printf("%.2f" + "초 걸렸습니다.\n", (after - before) / (float) 1000);

    }
}
