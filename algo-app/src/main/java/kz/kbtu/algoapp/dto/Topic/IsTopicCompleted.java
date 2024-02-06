package kz.kbtu.algoapp.dto.Topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsTopicCompleted {
    private String id;

    @JsonProperty("is-done")
    private Boolean isDone;
}
