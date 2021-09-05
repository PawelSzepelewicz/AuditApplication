package com.example.audit.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogInfoDto {
    private String subjectName;
    private String action;
    private String objectName;
    private String actionMonth;
    private Integer actionDay;
    private Integer actionHour;
    private Integer actionMinute;
}
