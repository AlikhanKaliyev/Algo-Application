package kz.kbtu.algoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "subtopics")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubTopic {
    @Id
    private String id;

    private Map<String, String> title;
}
