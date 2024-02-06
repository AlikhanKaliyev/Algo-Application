package kz.kbtu.algoapp.dto.Question;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kz.kbtu.algoapp.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionDto {
    @NotNull
    private QuestionType type;

    @NotNull
    private Map<String, Object> content;

    @NotNull
    private List<String> answers;

    @NotNull
    private List<String> correct;
}
