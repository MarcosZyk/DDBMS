package org.example.library.mongo;

import org.example.library.entity.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RankRepositoryTest {
  @Autowired private RankRepository rankRepository;

  @Test
  void findByTime() {
    Rank rank = rankRepository.findByTime("year:2017-day:268");
    assertEquals("year:2017-day:268", rank.getTimestamp());
    assertEquals("daily", rank.getTemporalGranularity());
  }

  @Test
  void findByTemporalGranularity() {
    List<Rank> ranks = rankRepository.findByTemporalGranularity("monthly");
    assertEquals(5, ranks.size());
  }
}
