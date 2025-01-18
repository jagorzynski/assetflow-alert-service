package com.sothrose.assetflow_alert_service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "alerts")
public class Alert {
  @Id private String id;
  private String portfolioId;
  private Long userId;
  private String haveName;
  private BigDecimal haveValue;
  private String owesName;
  private BigDecimal owesValue;
  private LocalDateTime timestamp;
  private ActionType actionType;

  public AlertDto toAlertDto() {
    return AlertDto.builder()
        .id(id)
        .portfolioId(portfolioId)
        .userId(userId)
        .haveName(haveName)
        .haveValue(haveValue)
        .owesName(owesName)
        .owesValue(owesValue)
        .timestamp(timestamp)
        .actionType(actionType)
        .build();
  }
}
