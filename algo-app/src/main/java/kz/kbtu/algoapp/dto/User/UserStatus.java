package kz.kbtu.algoapp.dto.User;

import kz.kbtu.algoapp.dto.Topic.IsTopicCompleted;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserStatus {
    private List<IsTopicCompleted> topicsCompleted;
}
