package org.example.library.mongo;

import org.example.library.entity.ReadDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReadDetailRepository extends MongoRepository<ReadDetail, String> {
  @Query("{uid:'?0', aid:'?1'}")
  List<ReadDetail> findByUidAndAid(String uid, String aid);
}
