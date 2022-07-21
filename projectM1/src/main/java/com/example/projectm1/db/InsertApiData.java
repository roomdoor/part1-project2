package com.example.projectm1.db;

import com.example.projectm1.API.GetApiData;
import com.example.projectm1.dto.WifiDto;
import com.example.projectm1.service.WifiDbService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class InsertApiData {
    public static void main(String[] args) {
        WifiDbService wifidbService = new WifiDbService();

        long before = System.currentTimeMillis();

        int count = 0;
        JSONArray dataArr = new JSONArray();

        try {
            dataArr = GetApiData.apiDataGet();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        long after = System.currentTimeMillis();
        System.out.println(dataArr.size());
        System.out.printf("%.2f" + "초 걸렸습니다.\n", (after - before) / (float) 1000);


        before = System.currentTimeMillis();

        for (Object value : dataArr) {
            JSONObject o = (JSONObject) value;
            Gson gsonBuilder = new GsonBuilder().create();
            WifiDto wifiDto = gsonBuilder.fromJson(o.toString(), WifiDto.class);
            if (wifidbService.findMGR_NO(wifiDto.getX_SWIFI_MGR_NO()) == null) {
                wifidbService.insert(wifiDto);
                count++;
            } else {
                System.out.println("이미 데이터를 받아 왔습니다.");
                break;
            }
        }

        after = System.currentTimeMillis();
        System.out.println(count + "개의 데이터를 가져왔습니다.");
        System.out.printf("%.2f" + "초 걸렸습니다.\n", (after - before) / (float) 1000);
    }
}
