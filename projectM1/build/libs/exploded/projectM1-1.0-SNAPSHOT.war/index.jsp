<%@ page import="com.example.projectm1.service.WifiDbService" %>
<%@ page import="com.example.projectm1.dto.WifiDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하</title>
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

        /*tr:nth-child(even) {background-color: #f2f2f2;}*/
    </style>
</head>
<body>
<%
    WifiDbService wifiDbService = new WifiDbService();
    List<WifiDto> wifiDtoList = new ArrayList<>();
    if (request.getParameter("lat") != null
            && request.getParameter("lnt") != null
            && !request.getParameter("lat").equals("")
            && !request.getParameter("lnt").equals("")) {
        Double lat = Double.parseDouble(request.getParameter("lat"));
        Double lnt = Double.parseDouble(request.getParameter("lnt"));
        wifiDtoList = wifiDbService.searchLocation(
                Double.parseDouble(request.getParameter("lat")
                ), Double.parseDouble(request.getParameter("lnt")));
    }
%>

<h1>와이파이 정보 구하기</h1>
<p><b><a href="index.jsp" target="_blank"> 홈</a></b> |
    <a href="getHistoryList.jsp" target="_blank"> 위치 히스토리 목록</a> |
    <a href="getApiData.jsp" target="_blank"> Open Api 와이파이 정보 가져오기</a>
</p>
<p1>
    <form method="post" action="searchWifi.jsp">
        LAT : <input type="text" name="lat">
        LNT : <input type="text" name="lnt">
        <input type="submit" value="근처 WIFi 정보 보기">
    </form>
</p1>

<table>
    <thead>
    <tr>
        <th>거리 (Km)</th>
        <th>관리 번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <tr>
            <%
                    for (WifiDto w: wifiDtoList) {
                %>
    <tr>
        <td>
            <%=w.getDistance()%>
        </td>

        <td>
            <%=w.getX_SWIFI_MGR_NO()%>
        </td>
        <td>
            <%=w.getX_SWIFI_WRDOFC()%>
        </td>
        <td>
            <%=w.getX_SWIFI_MAIN_NM()%>
        </td>
        <td>
            <%=w.getX_SWIFI_ADRES1()%>
        </td>
        <td>
            <%=w.getX_SWIFI_ADRES2()%>
        </td>
        <td>
            <%=w.getX_SWIFI_INSTL_FLOOR()%>
        </td>
        <td>
            <%=w.getX_SWIFI_INSTL_TY()%>
        </td>
        <td>
            <%=w.getX_SWIFI_INSTL_MBY()%>
        </td>
        <td>
            <%=w.getX_SWIFI_SVC_SE()%>
        </td>
        <td>
            <%=w.getX_SWIFI_CMCWR()%>
        </td>
        <td>
            <%=w.getX_SWIFI_CNSTC_YEAR()%>
        </td>
        <td>
            <%=w.getX_SWIFI_INOUT_DOOR()%>
        </td>
        <td>
            <%=w.getX_SWIFI_REMARS3()%>
        </td>
        <td>
            <%=w.getLAT()%>
        </td>
        <td>
            <%=w.getLNT()%>
        </td>
        <td>
            <%=w.getWORK_DTTM()%>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>