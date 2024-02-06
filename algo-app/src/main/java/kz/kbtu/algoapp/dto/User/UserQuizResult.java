package kz.kbtu.algoapp.dto.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQuizResult {
    private List<UserQuestionCorrectness> result;
    @JsonProperty("questions-number")
    private Integer numberOfQuestions;

    @JsonProperty("correct-answers")
    private Integer correctAnswers;

    private Double percentage;
}
