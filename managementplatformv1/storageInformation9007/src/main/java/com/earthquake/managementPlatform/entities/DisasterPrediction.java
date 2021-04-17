package com.earthquake.managementPlatform.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisasterPrediction {
    private String D_ID;
    private String S_ID;
    private String date;
    private String grade;
    private String intensity;
    private String type;
    private String picture;
}
