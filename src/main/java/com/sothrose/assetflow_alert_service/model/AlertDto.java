package com.sothrose.assetflow_alert_service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlertDto {
  private String id;
  private String portfolioId;
  private Long userId;
  private String haveName;
  private BigDecimal haveValue;
  private String owesName;
  private BigDecimal owesValue;
  private LocalDateTime timestamp;
  private ActionType actionType;
}
