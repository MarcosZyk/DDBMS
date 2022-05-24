package org.example.library.mongo;

import org.example.library.entity.Rank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RankRepository extends MongoRepository<Rank, String> {
  @Query("{timestamp:'?0'}")
  Rank findByTime(String time);

  @Query("{temporal_granularity:'?0'}")
  List<Rank> findByTemporalGranularity(String temporalGranularity);
}
