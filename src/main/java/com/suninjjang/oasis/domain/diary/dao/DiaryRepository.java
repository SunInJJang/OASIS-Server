package com.suninjjang.oasis.domain.diary.dao;

import com.suninjjang.oasis.domain.diary.domain.entiry.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends MongoRepository <Diary,Long> {

}
