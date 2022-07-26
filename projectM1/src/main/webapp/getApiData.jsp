<%@ page import="com.example.projectm1.service.WifiDbService" %><%--
  Created by IntelliJ IDEA.
  User: isihwa
  Date: 2022/07/21
  Time: 8:41 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Open Api 와이파이 정보 가져오기</title>
    <style>
        h1, p {
            text-align: center;
        }
    </style>
</head>
<body>
<%
    int count = WifiDbService.insertInDB();
    if (count == 0) {
%>
    <h1>이미 모든 데이터를 저장하였습니다.</h1>
<%
} else {
%>
    <h1><%=count%> 개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
<%
    }
%>
<p><b><a href="index.jsp"> 홈</a></b></p>
</body>
</html>

