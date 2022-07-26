package com.example.projectm1.dto;

import com.example.projectm1.carculater.DistanceCur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WifiDto implements Comparable<WifiDto> {
    public double distance;
    public String X_SWIFI_MGR_NO;       // 관리 번호
    public String X_SWIFI_WRDOFC;       // 자치구
    public String X_SWIFI_MAIN_NM;      // 와이파이 명
    public String X_SWIFI_ADRES1;       // 주소
    public String X_SWIFI_ADRES2;       // 상세주소
    public String X_SWIFI_INSTL_FLOOR;  // 설치 층
    public String X_SWIFI_INSTL_TY;     // 설치 유형
    public String X_SWIFI_INSTL_MBY;    // 설치 기관
    public String X_SWIFI_SVC_SE;       // 서비스 구분
    public String X_SWIFI_CMCWR;        // 망종류
    public String X_SWIFI_CNSTC_YEAR;   // 설치 년도
    public String X_SWIFI_INOUT_DOOR;   // 실내외 구분
    public String X_SWIFI_REMARS3;      // 접속 환경
    public String LAT;          // X 위도
    public String LNT;          // Y 경도
    public String WORK_DTTM;    // 작업 일자


    public static double curDis(double x1, double y1, String x2, String y2) {
        double dis = DistanceCur.distance(x1, y1, Double.parseDouble(x2), Double.parseDouble(y2));
        return Math.round((dis * 1000)) / 1000.0;
    }

    @Override
    public int compareTo(WifiDto o) {
        return Double.compare(this.getDistance(), o.getDistance());
    }

}
