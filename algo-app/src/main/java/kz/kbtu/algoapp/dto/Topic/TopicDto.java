package kz.kbtu.algoapp.dto.Topic;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import kz.kbtu.algoapp.dto.Quiz.QuizDto;
import kz.kbtu.algoapp.dto.SubTopic.SubTopicDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicDto {
     private String id;
     private Map<String, String> title;

     @JsonProperty("sub-topics")
     private List<SubTopicDto> subTopics;

     @JsonProperty("quiz-id")
     private String quizId;
}
