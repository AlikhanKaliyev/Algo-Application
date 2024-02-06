package kz.kbtu.algoapp.dto.SubTopic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubTopicDto {
    private String id;
    private Map<String, String> title;
}
