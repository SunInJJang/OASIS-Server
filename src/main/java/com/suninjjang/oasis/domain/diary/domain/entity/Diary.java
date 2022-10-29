package com.suninjjang.oasis.domain.diary.domain.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "Diary")
@Data
public class Diary {

    private Long contentId;

    private String title;

    private String mood;

    private String writer;

}
