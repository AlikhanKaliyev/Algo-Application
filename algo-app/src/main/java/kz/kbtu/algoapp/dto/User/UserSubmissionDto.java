package kz.kbtu.algoapp.dto.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubmissionDto {
    @NotNull
    List<List<String>> answers;
}
