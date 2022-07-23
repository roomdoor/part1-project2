<%@ page import="com.example.projectm1.service.WifiSearchHistoryService" %>
<%@ page import="com.example.projectm1.dto.WIfiSearchHistoryDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: isihwa
  Date: 2022/07/21
  Time: 8:58 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>위치 히스토리 목록</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            border: solid 1px #ddd;
            height: 50px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }

        td {
            border: solid 1px #ddd;
            height: 50px;
            text-align: left;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<%
    WifiSearchHistoryService wifiSearchHistoryService = new WifiSearchHistoryService();
    List<WIfiSearchHistoryDto> wIfiSearchHistoryDtoList = wifiSearchHistoryService.getList();

%>

<h1>위치 히스토리 목록</h1>
<p><b><a href="index.jsp" target="_blank"> 홈</a></b> |
    <a href="getHistoryList.jsp" target="_blank"> 위치 히스토리 목록</a> |
    <a href="getApiData.jsp" target="_blank"> Open Api 와이파이 정보 가져오기</a>
</p>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <tr>
            <%
            for (WIfiSearchHistoryDto w: wIfiSearchHistoryDtoList) {
        %>
    <tr>
        <td>
            <%=w.getId()%>
        </td>
        <td>
            <%=w.getLat()%>
        </td>
        <td>
            <%=w.getLnt()%>
        </td>
        <td>
            <%=w.getTime()%>
        </td>
        <td>
            <input type="submit" value="삭제">
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>
