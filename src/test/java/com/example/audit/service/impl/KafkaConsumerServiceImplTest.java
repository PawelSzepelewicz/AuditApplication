package com.example.audit.service.impl;

import com.example.audit.AuditApplication;
import com.example.audit.core.dto.LogDto;
import com.example.audit.service.LogsService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@EmbeddedKafka(
        partitions = 1, topics = {"spring-audit"},
        brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"}
)
@EnableKafka
@DirtiesContext
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {AuditApplication.class},
        properties = "spring.kafka.bootstrap-servers=${kafka.server}")
class KafkaConsumerServiceImplTest {
    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;
    @Autowired
    private ConsumerFactory<String, LogDto> consumerFactory;
    private Producer<String, LogDto> producer;
    private Consumer<String, LogDto> consumer;
    @Mock
    private LogsService service;
    @SpyBean
    private KafkaConsumerServiceImpl consumerService;


    @BeforeEach
    void set() {
        consumerService = new KafkaConsumerServiceImpl(service);
    }

    @BeforeAll
    void setUp() {
        Map<String, Object> configs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
        producer = new DefaultKafkaProducerFactory<String, LogDto>(configs,
                new StringSerializer(), new JsonSerializer<>()).createProducer();

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("0", "true", embeddedKafkaBroker);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new DefaultKafkaConsumerFactory<String, LogDto>(consumerProps,
                new StringDeserializer(), new JsonDeserializer<>(LogDto.class, true)).createConsumer();
        consumer.subscribe(Collections.singleton("spring-audit"));

        Consumer<String, LogDto> kafkaConsumer = consumerFactory.createConsumer();
        kafkaConsumer.subscribe(Collections.singleton("spring-audit"));
    }

    @AfterAll
    void tearDown() {
        producer.close();
    }

    @Test
    void consume() {
        var time = LocalDateTime.now();
        LogDto log = new LogDto("Pasha", "Pasha", "Deleted", time);
        producer.send(new ProducerRecord<>("spring-audit", log));
        ConsumerRecord<String, LogDto> singleRecord = KafkaTestUtils.getSingleRecord(consumer, "spring-audit");
        assertThat(singleRecord).isNotNull();
        assertThat(singleRecord.value()).isEqualTo(log);
        verify(service).createLog(log);
        producer.flush();
    }
}
