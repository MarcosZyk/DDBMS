package org.example.library.mongo;

import org.example.library.entity.ReadDetail;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReadDetailRepository extends MongoRepository<ReadDetail, String> {
  @Cacheable(cacheNames = "ReadDetailByUid", key = "#uid")
  @Query("{uid:'?0'}")
  List<ReadDetail> findByUid(String uid);

  @Cacheable(cacheNames = "ReadDetailByAid", key = "#aid")
  @Query("{aid:'?0'}")
  List<ReadDetail> findByAid(String aid);

  @Cacheable(cacheNames = "ReadDetailByUidAndAid", key = "#uid - #aid")
  @Query("{uid:'?0', aid:'?1'}")
  List<ReadDetail> findByUidAndAid(String uid, String aid);
}
