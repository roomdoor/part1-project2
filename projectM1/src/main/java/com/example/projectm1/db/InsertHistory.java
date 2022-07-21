package com.example.projectm1.db;

import com.example.projectm1.dto.WIfiSearchHistoryDto;
import com.example.projectm1.service.WifiSearchHistoryService;

public class InsertHistory {
    public static void main(String[] args) {
        WifiSearchHistoryService wifiSearchHistoryService = new WifiSearchHistoryService();

        double lat = 126.97083534068472;
        double lnt = 37.55515236979944;
        int id = 0;
        String time = "";
        WIfiSearchHistoryDto wIfiSearchHistoryDto =
                WIfiSearchHistoryDto.builder()
                        .Id(id)
                        .lat(lat)
                        .lnt(lnt)
                        .time(time)
                        .build();

        wifiSearchHistoryService.insert(wIfiSearchHistoryDto);
    }
}
