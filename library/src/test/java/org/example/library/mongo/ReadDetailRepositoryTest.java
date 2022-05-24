package org.example.library.mongo;

import org.example.library.entity.ReadDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReadDetailRepositoryTest {
  @Autowired private ReadDetailRepository readDetailRepo;

  @Test
  void findByUidAndAid() {
    List<ReadDetail> readDetails = readDetailRepo.findByUidAndAid("677", "7495");
    assertEquals(1, readDetails.size());
    assertEquals("677", readDetails.get(0).getUid());
    assertEquals("7495", readDetails.get(0).getAid());
  }
}
