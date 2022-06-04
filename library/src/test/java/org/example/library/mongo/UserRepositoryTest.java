package org.example.library.mongo;

import org.example.library.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
  @Autowired private UserRepository userRepo;

  @Test
  void findByUid() {
    User user = userRepo.findByUid("14");
    assertEquals("14", user.getUid());
  }

  @Test
  void findByPreferTagOrderByCredits() {
    List<User> users = userRepo.findByPreferTagOrderByCredits("tags10");
    int lastCredit = Integer.MAX_VALUE;
    for (User user : users) {
      assertEquals("tags10", user.getPreferTags());
      assertTrue(lastCredit >= user.getObtainedCredits());
      lastCredit = user.getObtainedCredits();
    }
  }
}
