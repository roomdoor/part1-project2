package com.example.projectm1.db;

import com.example.projectm1.dto.WifiDto;
import java.sql.*;

public class WIfiService {

    // wifi db 에 입력
    public void insert(WifiDto wifiDto) {
        String url = "jdbc:mariadb://localhost:3306/wifidb";
        String dbUserId = "wifi_project";
        String dbPassword = "wifi1234";

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

            String sql = "insert into wifiInfo (MGR_NO, WRDOFC, MAIN_NM, ADRES1," +
                    " ADRES2, INSTL_FLOOR, INSTL_TY, INSTL_MBY," +
                    " SVC_SE, CMCWR, CNSTC_YEAR, INOUT_DOOR, " +
                    "REMARS3, LAT, LNT, WORK_DTTM) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wifiDto.MGR_NO);
            preparedStatement.setString(2, wifiDto.WRDOFC);
            preparedStatement.setString(3, wifiDto.MAIN_NM);
            preparedStatement.setString(4, wifiDto.ADRES1);
            preparedStatement.setString(5, wifiDto.ADRES2);
            preparedStatement.setString(6, wifiDto.INSTL_FLOOR);
            preparedStatement.setString(7, wifiDto.INSTL_TY);
            preparedStatement.setString(8, wifiDto.INSTL_MBY);
            preparedStatement.setString(9, wifiDto.SVC_SE);
            preparedStatement.setString(10, wifiDto.CMCWR);
            preparedStatement.setString(11, wifiDto.CNSTC_YEAR);
            preparedStatement.setString(12, wifiDto.INOUT_DOOR);
            preparedStatement.setString(13, wifiDto.REMARS3);
            preparedStatement.setString(14, wifiDto.LAT);
            preparedStatement.setString(15, wifiDto.LNT);
            preparedStatement.setString(16, wifiDto.WORK_DTTM);

            int affeced = preparedStatement.executeUpdate();

            if (affeced < 0) {
                System.out.println("저장 실패");
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
}
