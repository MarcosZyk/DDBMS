package org.example.library.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@AllArgsConstructor
@Document("rank")
public class Rank {
  @Id private String mongoId;

  // format is year:%Y-month:%m, year:%Y-week:%U, year:%Y-day:%j
  @Field("timestamp")
  private String timestamp;

  @Field("temporal_granularity")
  private String temporalGranularity;

  @Field("articleAidList")
  private List<String> articleAids;

  @Field("articleTotalScoreList")
  private List<Integer> articleTotalScores;
}
