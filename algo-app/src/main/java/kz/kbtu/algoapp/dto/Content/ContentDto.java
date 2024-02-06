package kz.kbtu.algoapp.dto.Content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContentDto {
    private String id;

    private List<Map<String, Object>> content;
}
