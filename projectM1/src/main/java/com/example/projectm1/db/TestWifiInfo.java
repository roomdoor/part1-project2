package com.example.projectm1.db;

import com.example.projectm1.dto.WifiDto;
import com.example.projectm1.service.WifiDbService;

import java.util.List;

public class TestWifiInfo {
    public static void main(String[] args) {
        WifiDbService wifidbService = new WifiDbService();
        WifiDbService.testConnection();

        List<WifiDto> list = wifidbService.getListForCurDis();
        System.out.println(list.toString());

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setDistance(i * i);
            wifidbService.updateDistance(list.get(i));
        }
    }

}
