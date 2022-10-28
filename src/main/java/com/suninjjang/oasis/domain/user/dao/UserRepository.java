package com.suninjjang.oasis.domain.user.dao;

import com.suninjjang.oasis.domain.user.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    public User findByUsername(String username);
    public List<User> findAllByUsername(String username);
}
