package com.sothrose.assetflow_alert_service.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.sothrose.assetflow_alert_service.model.AlertDto;
import com.sothrose.assetflow_alert_service.service.AlertService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/v1/assetflow/alerts")
public class AlertController {

  private final AlertService alertService;

  @GetMapping(path = "/{userId}", produces = APPLICATION_JSON_VALUE)
  public List<AlertDto> getAllAlertsForUser(@PathVariable Long userId) {
    return alertService.fetchAllAlertsForUser(userId);
  }
}
