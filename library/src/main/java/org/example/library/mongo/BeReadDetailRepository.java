package org.example.library.mongo;

import org.example.library.entity.BeReadDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface BeReadDetailRepository extends MongoRepository<BeReadDetail, String> {
  @Query("{aid:'?0'}")
  BeReadDetail findByAid(String aid);
}
