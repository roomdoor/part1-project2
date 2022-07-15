package com.example.projectm1.db;

import com.example.projectm1.dto.WifiDto;

public class WIfiMain {
    public static void main(String[] args) {
        WIfiService wIfiService = new WIfiService();
        WifiDto wifiDto = new WifiDto();
        wIfiService.insert(wifiDto);
    }
}
