package com.sothrose.assetflow_alert_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sothrose.assetflow_alert_service.model.PortfolioUpdatedEvent;
import com.sothrose.assetflow_alert_service.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeadLetterQueueConsumer {

  private final AlertRepository alertRepository;
  private final ObjectMapper dlqObjectMapper;

  @KafkaListener(
      topics = "${kafka.listener.dead-letter-topic}",
      groupId = "alert-service-dlq-group")
  public void handleFailedMessages(ConsumerRecord<String, String> record, Acknowledgment ack) {
    try {
      var event = dlqObjectMapper.readValue(record.value(), PortfolioUpdatedEvent.class);
      log.info("Processing an event from DLQ: [{}]", event);
      alertRepository.save(event.toAlert());
      ack.acknowledge();
    } catch (Exception e) {
      log.error("Failed to process DLQ message: [{}]", e.getMessage());
    }
  }
}
