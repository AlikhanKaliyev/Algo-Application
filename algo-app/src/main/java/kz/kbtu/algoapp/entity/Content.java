package kz.kbtu.algoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Content {
    @Id
    private String id;

    @DBRef
    private SubTopic subTopic;

    private List<Map<String, Object>> content;
}
