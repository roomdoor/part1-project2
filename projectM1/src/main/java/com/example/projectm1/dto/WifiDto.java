package com.example.projectm1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WifiDto {
    public String MGR_NO;       // 관리 번호
    public String WRDOFC;       // 자치구
    public String MAIN_NM;      // 와이파이 명
    public String ADRES1;       // 주소
    public String ADRES2;       // 상세주소
    public String INSTL_FLOOR;  // 설치 층
    public String INSTL_TY;     // 설치 유형
    public String INSTL_MBY;    // 설치 기관
    public String SVC_SE;       // 서비스 구분
    public String CMCWR;        // 망종류
    public String CNSTC_YEAR;   // 설치 년도
    public String INOUT_DOOR;   // 실내외 구분
    public String REMARS3;      // 접속 환경
    public String LAT;          // X
    public String LNT;          // Y
    public String WORK_DTTM;    // 작업 일자

//    public void pas
}
