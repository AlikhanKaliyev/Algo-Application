package kz.kbtu.algoapp.dto.Topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTopicDto {
    @NotNull
    private Map<String, String> title;
    @JsonProperty("sub-topics")
    @NotNull
    private List<String> subTopics;
}
