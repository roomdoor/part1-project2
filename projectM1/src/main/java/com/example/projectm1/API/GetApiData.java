package com.example.projectm1.API;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetApiData {
    public static void main(String[] args) throws IOException, ParseException {
        JSONObject jsonObject = getJSONParser(1, 1);
        JSONObject TbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
        long totalCount = (long) TbPublicWifiInfo.get("list_total_count");
        System.out.println("totalCount = " + totalCount);

        JSONArray dataArr = new JSONArray();

        for (int i = 0; i < totalCount / 1000; i++) {
            int start = i * 1000 + 1;
            int end = (i + 1) * 1000;
            addArray(start, end, dataArr);
        }

        long start = totalCount - totalCount % 1000 + 1;
        addArray(start, totalCount, dataArr);
        System.out.println(dataArr.size());
    }

    public static void addArray(long start, long end, JSONArray dataArr) throws IOException, ParseException {
        JSONObject jsonObject = getJSONParser(start, end);
        JSONObject TbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
        JSONArray temp = (JSONArray) TbPublicWifiInfo.get("row");
        dataArr.addAll(temp);

    }

    public static JSONObject getJSONParser(long start, long end) throws IOException, ParseException {
        URL url = new URL("http://openapi.seoul.go.kr:8088" +
                "/716d68435073696837306d42417157" +
                "/json/TbPublicWifiInfo/" + start + "/" + end + "/20220301");

        BufferedReader bf = new BufferedReader(
                new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        String result = bf.readLine();

        return (JSONObject) new JSONParser().parse(result);
    }
}
