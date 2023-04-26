package com.sjsu.HealthConnect.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class AppSearchDTO {
    @NonNull
    Long doctorId;
    @NonNull
    Date date;
}
