package kz.kbtu.algoapp.dto.SubTopic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSubTopicDto {
    @NotNull
    private Map<String, String> title;
}
