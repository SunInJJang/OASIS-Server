package com.suninjjang.oasis.domain.member.dao;

import com.suninjjang.oasis.domain.member.domain.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberRepository extends MongoRepository<Member,Long> {
    public Member findByUsername(String username);
    public List<Member> findAllByUsername(String username);
}
