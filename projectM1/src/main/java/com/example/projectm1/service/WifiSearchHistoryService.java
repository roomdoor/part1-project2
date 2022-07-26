package com.example.projectm1.service;

import com.example.projectm1.dto.WIfiSearchHistoryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiSearchHistoryService {
    private static final String url = "jdbc:mariadb://localhost:3306/wifidb";
    private static final String dbUserId = "wifi_project";
    private static final String dbPassword = "wifi1234";

    // db get list
    public List<WIfiSearchHistoryDto> getList() {
        List<WIfiSearchHistoryDto> list = new ArrayList<>();
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

            String sql = "select id, lat, lnt, time " +
                    "from wifiSearchHistory;";

            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                list.add(WIfiSearchHistoryDto.builder()
                        .Id(rs.getInt("Id"))
                        .lat(rs.getDouble("lat"))
                        .lnt(rs.getDouble("lnt"))
                        .time(rs.getString("time"))
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

    // wifi db 에 입력
    public void insert(WIfiSearchHistoryDto wIfiSearchHistoryDto) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "insert into wifiSearchHistory\n" +
                    "(lat, lnt, time) VALUES (?,?,?);";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(wIfiSearchHistoryDto.getLat()));
            preparedStatement.setString(2, String.valueOf(wIfiSearchHistoryDto.getLnt()));
            preparedStatement.setString(3, wIfiSearchHistoryDto.getTime());

            int affected = preparedStatement.executeUpdate();

            if (affected < 0) {
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

    // find id for delete
    public int findId(double lat, double lnt) {
        int id = 0;
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

            String sql = "select id " +
                    "from wifiSearchHistory " +
                    "where lat = ? " +
                    "and lnt = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, lat);
            preparedStatement.setDouble(2, lnt);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
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

        return id;
    }

    // History 삭제
    public void delete(int id) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            String sql = "delete from wifiSearchHistory " +
                    "where Id = ?;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(id));

            int affected = preparedStatement.executeUpdate();

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
