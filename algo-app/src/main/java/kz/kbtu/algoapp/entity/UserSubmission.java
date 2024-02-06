package kz.kbtu.algoapp.entity;

import kz.kbtu.algoapp.dto.User.UserQuizResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubmission {
    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private Quiz quiz;

    private UserQuizResult userQuizResult;
}
