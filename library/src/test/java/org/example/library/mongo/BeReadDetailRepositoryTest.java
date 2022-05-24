package org.example.library.mongo;

import org.example.library.entity.BeReadDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeReadDetailRepositoryTest {
  @Autowired private BeReadDetailRepository beReadDetailRepo;

  @Test
  void findByAid() {
    BeReadDetail beReadDetail = beReadDetailRepo.findByAid("3652");
    assertEquals("3652", beReadDetail.getAid());
  }
}
