package com.example.projectm1.service;

import com.example.projectm1.dto.WIfiSearchHistoryDto;
import com.example.projectm1.dto.WifiDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WifiDbService {
    private static final String url = "jdbc:mariadb://localhost:3306/wifidb";
    private static final String dbUserId = "wifi_project";
    private static final String dbPassword = "wifi1234";


    // insert open Api data in DB (WifiInfo)
    public static int insertInDB() {
        WifiDbService wifidbService = new WifiDbService();

        int count = 0;
        JSONArray dataArr = new JSONArray();

        try {
            dataArr = wifidbService.apiDataGet();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        JSONObject o = (JSONObject) dataArr.get(0);
        if (wifidbService.findMGR_NO(String.valueOf(o.get("X_SWIFI_MGR_NO"))) != null) {
            return 0;
        }

        for (int i = 0; i <= dataArr.size() / 1000; i++) {
            count += wifidbService.insert(dataArr, i * 1000);
        }
        return count;
    }

    // get list distance 계산을 위한
    public List<WifiDto> getListForCurDis() {
        List<WifiDto> list = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "select MGR_NO, LAT, LNT, WORK_DTTM from wifiInfo;";

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                list.add(WifiDto.builder()
                        .X_SWIFI_MGR_NO(rs.getString("MGR_NO"))
                        .LAT(rs.getString("LAT"))
                        .LNT(rs.getString("LNT"))
                        .WORK_DTTM(rs.getString("WORK_DTTM"))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return list;
    }

    // get list 근처 20 개 표현
    public List<WifiDto> getListForShow20(String WORK_DTTM) {
        List<WifiDto> list = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "select DISTANCE, MGR_NO, WRDOFC, MAIN_NM, " +
                    "ADRES1, ADRES2, INSTL_FLOOR, INSTL_TY, " +
                    "INSTL_MBY, SVC_SE, CMCWR, CNSTC_YEAR, " +
                    "INOUT_DOOR, REMARS3, LAT, LNT, WORK_DTTM " +
                    "from wifiInfo " +
                    "where WORK_DTTM = ? " +
                    "order by DISTANCE;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, WORK_DTTM);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                list.add(WifiDto.builder()
                        .distance(Double.parseDouble(rs.getString("DISTANCE")))
                        .X_SWIFI_MGR_NO(rs.getString("MGR_NO"))
                        .X_SWIFI_WRDOFC(rs.getString("WRDOFC"))
                        .X_SWIFI_MAIN_NM(rs.getString("MAIN_NM"))
                        .X_SWIFI_ADRES1(rs.getString("ADRES1"))
                        .X_SWIFI_ADRES2(rs.getString("ADRES2"))
                        .X_SWIFI_INSTL_FLOOR(rs.getString("INSTL_FLOOR"))
                        .X_SWIFI_INSTL_TY(rs.getString("INSTL_TY"))
                        .X_SWIFI_INSTL_MBY(rs.getString("INSTL_MBY"))
                        .X_SWIFI_SVC_SE(rs.getString("SVC_SE"))
                        .X_SWIFI_CMCWR(rs.getString("CMCWR"))
                        .X_SWIFI_CNSTC_YEAR(rs.getString("CNSTC_YEAR"))
                        .X_SWIFI_INOUT_DOOR(rs.getString("INOUT_DOOR"))
                        .X_SWIFI_REMARS3(rs.getString("REMARS3"))
                        .LAT(rs.getString("LAT"))
                        .LNT(rs.getString("LNT"))
                        .WORK_DTTM(rs.getString("WORK_DTTM"))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return list;
    }

    // search lat, lnt and give 20 개 sort list
    // and save WifiSearchHistory
    public List<WifiDto> searchLocation(double lat, double lnt) {
        WifiDbService wifiDbService = new WifiDbService();
        WifiSearchHistoryService wifiSearchHistoryService = new WifiSearchHistoryService();
        List<WifiDto> list = wifiDbService.getListForCurDis();

        if (list.size() == 0) {
            return null;
        }

        // 1
        // distance 계산을 위한 데이터 받아오기
        for (WifiDto dto : list) {
            dto.setDistance(WifiDto.curDis(lat, lnt,
                    dto.getLAT(), dto.getLNT()));
        }

        // 20개 추리기 위한 정렬
        Collections.sort(list);

        // 2
        // 20 개 추린것만 db 에 업데이트
        String WORK_DTTM = LocalDate.now() + " " + LocalTime.now().withNano(0) + ".0";
        for (int i = 0; i < 20; i++) {
            WifiDto wifiDto = list.get(i);
            wifiDto.setWORK_DTTM(WORK_DTTM);
            wifiDbService.updateDistance(wifiDto);
        }

        // 3 WifiSearchHistory insert

        wifiSearchHistoryService.insert(WIfiSearchHistoryDto.builder()
                // 음 어떻게 넣지???
                .lat(lat)
                .lnt(lnt)
                .time(WORK_DTTM)
                .build());

        // 4
        // 보여줄 20 개 데이터 가져옴
        return wifiDbService.getListForShow20(WORK_DTTM);
    }

    // db pk 있는지 확인
    public WifiDto findMGR_NO(String MGR_NO) {
        WifiDto wifiDto = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "select MGR_NO " +
                    "from wifiInfo " +
                    "where MGR_NO = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MGR_NO);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String mgr_no = rs.getString("MGR_NO");
                wifiDto = new WifiDto();
                wifiDto.setX_SWIFI_MGR_NO(mgr_no);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return wifiDto;
    }

    // wifi db 에 1000개 씩  입력 (입력 시간 줄이려고) / 한번 입력할 때마다 select 이루어져서 그런듯?
    public int insert(JSONArray dataArray, int start) {
        int count = 0;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            StringBuilder sql = new StringBuilder();
            sql.append("insert into wifiInfo (" +
                    "MGR_NO, WRDOFC, MAIN_NM, ADRES1, " +
                    "ADRES2, INSTL_FLOOR, INSTL_TY, INSTL_MBY, " +
                    "SVC_SE, CMCWR, CNSTC_YEAR, INOUT_DOOR, " +
                    "REMARS3, LAT, LNT, WORK_DTTM) " +
                    "VALUES ");
            for (int k = 0; k < 1000 && (k + start) < dataArray.size(); k++) {
                sql.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?), ");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.deleteCharAt(sql.length() - 1);
            sql.append(";");
            preparedStatement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < 1000 && (i + start) < dataArray.size(); i++) {
                count++;
                JSONObject o = (JSONObject) dataArray.get(i + start);
                Gson gsonBuilder = new GsonBuilder().create();
                WifiDto wifiDto = gsonBuilder.fromJson(o.toString(), WifiDto.class);
                int num = (i) * 16;
                preparedStatement.setString(1 + num, wifiDto.X_SWIFI_MGR_NO);
                preparedStatement.setString(2 + num, wifiDto.X_SWIFI_WRDOFC);
                preparedStatement.setString(3 + num, wifiDto.X_SWIFI_MAIN_NM);
                preparedStatement.setString(4 + num, wifiDto.X_SWIFI_ADRES1);
                preparedStatement.setString(5 + num, wifiDto.X_SWIFI_ADRES2);
                preparedStatement.setString(6 + num, wifiDto.X_SWIFI_INSTL_FLOOR);
                preparedStatement.setString(7 + num, wifiDto.X_SWIFI_INSTL_TY);
                preparedStatement.setString(8 + num, wifiDto.X_SWIFI_INSTL_MBY);
                preparedStatement.setString(9 + num, wifiDto.X_SWIFI_SVC_SE);
                preparedStatement.setString(10 + num, wifiDto.X_SWIFI_CMCWR);
                preparedStatement.setString(11 + num, wifiDto.X_SWIFI_CNSTC_YEAR);
                preparedStatement.setString(12 + num, wifiDto.X_SWIFI_INOUT_DOOR);
                preparedStatement.setString(13 + num, wifiDto.X_SWIFI_REMARS3);
                preparedStatement.setString(14 + num, wifiDto.LAT);
                preparedStatement.setString(15 + num, wifiDto.LNT);
                preparedStatement.setString(16 + num, wifiDto.WORK_DTTM);
            }

            int affected = preparedStatement.executeUpdate();

        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count;
    }

    // distance update
    public void updateDistance(WifiDto wifiDto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);
            String sql = "update wifiInfo " +
                    "set DISTANCE = ? " +
                    ", WORK_DTTM = ? " +
                    "where MGR_NO = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(wifiDto.getDistance()));
            preparedStatement.setString(2, wifiDto.getWORK_DTTM());
            preparedStatement.setString(3, wifiDto.getX_SWIFI_MGR_NO());

            int affected = preparedStatement.executeUpdate();

            if (affected <= 0) {
                System.out.println("수정 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // api data 가져오기 위한 함수 3개
    // api data 가져오기 위한 함수 아래의 2개 함수를 한번에 넣고 돌리는 함수
    // @return api 데이터 모두 JSONArray 로 합친 것
    public JSONArray apiDataGet() throws IOException, ParseException {
        JSONObject jsonObject = getJSONParser(1, 1);
        JSONObject TbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
        long totalCount = (long) TbPublicWifiInfo.get("list_total_count");
        JSONArray dataArr = new JSONArray();

        for (int i = 0; i < totalCount / 1000; i++) {
            int start = i * 1000 + 1;
            int end = (i + 1) * 1000;
            addArray(start, end, dataArr);
        }

        long start = totalCount - totalCount % 1000 + 1;
        addArray(start, totalCount, dataArr);
        return dataArr;
    }

    // 받아오는 데이터 index 시작점과 끝점을 정해서 데이터 받아오는 함수
    // @return dataArr 에 받아온 json 데이터 1000개 합쳐줌
    public static void addArray(long start, long end, JSONArray dataArr) throws IOException, ParseException {
        JSONObject jsonObject = getJSONParser(start, end);
        JSONObject TbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
        JSONArray temp = (JSONArray) TbPublicWifiInfo.get("row");
        dataArr.addAll(temp);
    }

    // 데이터 1000개씩 받을 수 있어서 1000개씩 받는 url 만드는 함수
    // @return json 으로 된 1000개의 데이터 array
    public static JSONObject getJSONParser(long start, long end) throws IOException, ParseException {
        URL url = new URL("http://openapi.seoul.go.kr:8088" +
                "/624b73765873696832347671705478/json" +
                "/TbPublicWifiInfo/" + start + "/" + end);
        BufferedReader bf = new BufferedReader(
                new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        String result = bf.readLine();

        return (JSONObject) new JSONParser().parse(result);
    }

    // testConnection
    public static void testConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "insert into wifiInfo (MGR_NO) " +
                    "VALUES (?);";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "123");

            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.isClosed();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}

