package com.example.audit.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogDto {
    private String objectName;
    private String subjectName;
    private String message;
    private String action;
    private LocalDateTime actionTime;
}
