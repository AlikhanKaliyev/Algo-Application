package kz.kbtu.algoapp.entity;

import kz.kbtu.algoapp.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    private String id;

    private QuestionType type;

    private Map<String, Object> content;

    @DBRef
    private List<Answer> answers;

    @DBRef
    private List<Answer> correct;
}

