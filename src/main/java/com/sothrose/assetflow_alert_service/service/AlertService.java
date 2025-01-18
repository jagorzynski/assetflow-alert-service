package com.sothrose.assetflow_alert_service.service;

import static java.lang.String.format;

import com.sothrose.assetflow_alert_service.model.Alert;
import com.sothrose.assetflow_alert_service.model.AlertDto;
import com.sothrose.assetflow_alert_service.model.PortfolioUpdatedEvent;
import com.sothrose.assetflow_alert_service.repository.AlertRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AlertService {

  private final AlertRepository alertRepository;

  @KafkaListener(topics = "${kafka.topic.portfolio-updates}", groupId = "alert-service-group")
  public void handlePortfolioUpdate(PortfolioUpdatedEvent event, Acknowledgment ack) {
    try {
      log.info("Received an event for processing: [{}]", event);
      alertRepository.save(event.toAlert());
      ack.acknowledge();
    } catch (Exception e) {
      throw new RecoverableDataAccessException(
          format("Processing an event failed: [%s]", e.getMessage()), e);
    }
  }

  public List<AlertDto> fetchAllAlertsForUser(Long userId) {
    return alertRepository.findAllByUserId(userId).stream().map(Alert::toAlertDto).toList();
  }
}
