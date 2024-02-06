package kz.kbtu.algoapp.dto.Quiz;

import kz.kbtu.algoapp.dto.Question.QuestionDto;
import kz.kbtu.algoapp.dto.Topic.TopicDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private String id;

    private List<QuestionDto> questions;
}
