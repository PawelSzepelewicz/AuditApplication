package com.example.audit.service;

import com.example.audit.core.dto.LogDto;

public interface KafkaConsumerService {
    void consume(LogDto logDto);
}
