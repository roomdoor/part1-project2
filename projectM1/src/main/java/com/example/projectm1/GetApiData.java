package com.example.projectm1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetApiData {
    public static void main(String[] args) throws IOException, ParseException {
        URL url = new URL("http://openapi.seoul.go.kr:8088/716d68435073696837306d42417157/json/TbPublicWifiInfo/1/10/20220301");

        BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        String result = bf.readLine();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
        JSONObject TbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
        System.out.println("totalNum = " + TbPublicWifiInfo.get("list_total_count"));;
        JSONArray dataArr = (JSONArray) TbPublicWifiInfo.get("row");
        System.out.println("dataArr = " + dataArr);;


// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.


    }
}
