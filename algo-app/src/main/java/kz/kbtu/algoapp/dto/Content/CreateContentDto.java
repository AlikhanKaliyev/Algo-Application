package kz.kbtu.algoapp.dto.Content;

import com.fasterxml.jackson.annotation .JsonProperty;
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
public class CreateContentDto {
    @NotNull
    @NotBlank
    @JsonProperty("sub-topic-id")
    private String subTopicId;

    @NotNull
    private List<Map<String, Object>> content;
}
