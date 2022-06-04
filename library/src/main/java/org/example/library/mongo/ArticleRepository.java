package org.example.library.mongo;

import org.example.library.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, String> {
  @Query("{aid:'?0'}")
  Article findByAid(String aid);

  @Query("{articleTags:'?0'}")
  List<Article> findByTag(String tag);

  @Query("{category:'?0', articleTags:'?1'}")
  List<Article> findByCategoryAndTag(String category, String tag);
}
