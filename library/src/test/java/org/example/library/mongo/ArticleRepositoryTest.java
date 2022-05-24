package org.example.library.mongo;

import org.example.library.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryTest {
  @Autowired private ArticleRepository articleRepo;

  @Test
  void findByAid() {
    Article article = articleRepo.findByAid("3");
    assertEquals("3", article.getAid());
  }
}
