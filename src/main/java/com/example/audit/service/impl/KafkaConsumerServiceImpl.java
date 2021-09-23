package com.example.audit.service.impl;

import com.example.audit.core.dto.LogDto;
import com.example.audit.service.KafkaConsumerService;
import com.example.audit.service.LogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Component
public class KafkaConsumerServiceImpl implements KafkaConsumerService {
   private final LogsService logsService;

    @Override
    @KafkaListener(topics = "spring-audit", groupId = "probation-audit", containerFactory = "kafkaListenerContainerFactory")
    public void consume(final LogDto logDto) {
        logsService.createLog(logDto);
    }
}
