package com.suninjjang.oasis.domain.user.dao;

import com.suninjjang.oasis.domain.user.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String>{
    Optional<User> findUserById(String id);
   List<User> findAllByUsername(String username);

   boolean existsUserById(String id);
}