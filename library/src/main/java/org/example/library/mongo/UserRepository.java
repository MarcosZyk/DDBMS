package org.example.library.mongo;

import org.example.library.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
  @Query("{uid:'?0'}")
  User findByUid(String uid);

  @Query(value = "{preferTags:'?0'}", sort = "{obtainedCredits: -1}")
  List<User> findByPreferTagOrderByCredits(String tag);
}
