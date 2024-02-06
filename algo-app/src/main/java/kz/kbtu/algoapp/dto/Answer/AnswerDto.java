package kz.kbtu.algoapp.dto.Answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private String id;
    private Map<String, Object> content;
}
