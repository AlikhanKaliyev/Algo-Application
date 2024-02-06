package kz.kbtu.algoapp.dto.Content;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContentDto {
    @NotNull
    private List<Map<String, Object>> content;
}
