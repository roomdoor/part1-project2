package com.example.projectm1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WIfiSearchHistoryDto {
    int Id;
    double lat;
    double lnt;
    String time;
}
