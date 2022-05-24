package org.example.library.mongo;

import org.example.library.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
  @Query("{uid:'?0'}")
  User findByUid(String uid);
}
