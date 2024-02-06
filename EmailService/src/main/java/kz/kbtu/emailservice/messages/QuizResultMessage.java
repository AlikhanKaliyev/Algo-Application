package kz.kbtu.emailservice.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizResultMessage {
    private Map<String, String> topicName;

    private Integer correctAnswers;

    private Integer numberOfQuestions;

    private String username;
    private String userEmail;
}
