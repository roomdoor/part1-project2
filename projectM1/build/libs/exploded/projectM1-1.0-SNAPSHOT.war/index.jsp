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
            height: 30px;
            text-align: center;
            background-color: #04AA6D;
            color: white;
        }

        td {
            border: solid 1px #ddd;
            height: 30px;
            text-align: left;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        p {
            text-align: center;
        }
    </style>
</head>
<body>
<%
    WifiDbService wifiDbService = new WifiDbService();
    List<WifiDto> wifiDtoList = new ArrayList<>();
    Double lat = 0.0;
    Double lnt = 0.0;
    if (request.getParameter("lat") != null
            && request.getParameter("lnt") != null
            && !request.getParameter("lat").equals("")
            && !request.getParameter("lnt").equals("")) {
        lat = Double.parseDouble(request.getParameter("lat"));
        lnt = Double.parseDouble(request.getParameter("lnt"));
        wifiDtoList = wifiDbService.searchLocation(lat, lnt);
    }
%>

<h1>와이파이 정보 구하기</h1>
<p1><b>
    <a href="index.jsp"> 홈</a> |
    <a href="getHistoryList.jsp"> 위치 히스토리 목록</a> |
    <a href="getApiData.jsp"> Open Api 와이파이 정보 가져오기</a>
</b></p1>

<p1>
    <form method="post" action="index.jsp">
        <label for="lat">LAT </label>
        <input type="text" id="lat" name="lat" value="<%=lat%>">
        <label for="lnt">LNT </label>
        <input type="text" id="lnt" name="lnt" value="<%=lnt%>">
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
        if (wifiDtoList == null){
    %>
    <tr>
        <td colspan="17">
            <p>"Open Api 와이파이 정보 가져오기" 를 먼저 실행해 주세요</p>
        </td>
    </tr>
    <%
    } else if (wifiDtoList.size() != 0) {
        for (WifiDto w : wifiDtoList) {
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
    } else {
    %>
    <tr>
        <td colspan="17">
            <p>위치 정보를 검색 후에 조회해 주세요</p>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>