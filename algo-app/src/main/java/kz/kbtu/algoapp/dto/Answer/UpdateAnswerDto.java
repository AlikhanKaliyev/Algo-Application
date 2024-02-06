package kz.kbtu.algoapp.dto.Answer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAnswerDto {
    @NotNull
    private Map<String, Object> content;
}
