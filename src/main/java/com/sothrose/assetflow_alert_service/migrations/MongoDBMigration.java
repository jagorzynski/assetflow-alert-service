package com.sothrose.assetflow_alert_service.migrations;

import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@RequiredArgsConstructor
@ChangeUnit(id = "mongo-db-initialization", order = "001", author = "sothrose")
public class MongoDBMigration {

  public static final String ALERTS_COLLECTION = "alerts";
  public static final String USER_ID_FIELD = "userId";

  private final MongoTemplate mongoTemplate;

  @Execution
  public void changeSet() {
    if (!mongoTemplate.collectionExists(ALERTS_COLLECTION)) {
      mongoTemplate.createCollection(ALERTS_COLLECTION);
    }

    mongoTemplate
        .indexOps(ALERTS_COLLECTION)
        .ensureIndex(new Index().on(USER_ID_FIELD, Sort.Direction.ASC).unique());
  }

  @RollbackExecution
  public void rollback() {
    mongoTemplate.dropCollection(ALERTS_COLLECTION);
  }
}
