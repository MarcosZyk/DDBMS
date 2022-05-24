package org.example.library.mongo;

import org.example.library.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ArticleRepository extends MongoRepository<Article, String> {
  @Query("{aid:'?0'}")
  Article findByAid(String aid);
}
