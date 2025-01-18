package com.sothrose.assetflow_alert_service.repository;

import com.sothrose.assetflow_alert_service.model.Alert;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends MongoRepository<Alert, String> {

  List<Alert> findAllByUserId(Long userId);
}
