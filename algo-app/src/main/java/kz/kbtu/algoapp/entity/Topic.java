package kz.kbtu.algoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@Document(collection = "topics")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Topic {
    @Id
    private String id;

    private Map<String, String> title;
    @DBRef
    @Field("sub_topics")
    private List<SubTopic> subTopics;

    @DBRef
    private Quiz quiz;
}
