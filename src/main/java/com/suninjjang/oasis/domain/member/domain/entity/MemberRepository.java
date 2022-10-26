package com.suninjjang.oasis.domain.member.domain.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member,String> {

}
