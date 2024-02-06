package kz.kbtu.algoapp.dto.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuestionCorrectness {
    @JsonProperty("question-id")
    private String questionId;

    @JsonProperty("user-answers")
    private Set<String> userAnswers;

    @JsonProperty("correct-answers")
    private Set<String> correctAnswers;

    @JsonProperty("is-correct")
    private Boolean isCorrect = false;
}
