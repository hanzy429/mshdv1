package com.earthquake.managementPlatform.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnOff {
    private String name;
    private boolean status;
}
