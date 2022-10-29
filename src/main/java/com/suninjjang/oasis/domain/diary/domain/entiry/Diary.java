package com.suninjjang.oasis.domain.diary.domain.entiry;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "diary")
@Data
public class Diary {
    private String title;
    private String content;
    private String mood;
    private String createDate;
    private String wirter;

}
