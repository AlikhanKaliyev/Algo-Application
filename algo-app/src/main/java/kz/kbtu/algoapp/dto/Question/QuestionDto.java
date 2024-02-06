package kz.kbtu.algoapp.dto.Question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import kz.kbtu.algoapp.dto.Answer.AnswerDto;
import kz.kbtu.algoapp.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionDto {
    private String id;
    private QuestionType type;

    private Map<String, Object> content;

    private List<AnswerDto> answers;

    private List<AnswerDto> correct;
}
