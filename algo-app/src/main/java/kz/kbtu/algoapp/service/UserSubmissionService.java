package kz.kbtu.algoapp.service;

import kz.kbtu.algoapp.dto.User.UserQuizResult;
import kz.kbtu.algoapp.dto.User.UserSubmissionDto;
import org.springframework.security.core.Authentication;

public interface UserSubmissionService {
    UserQuizResult submitAnswer(String quizId, UserSubmissionDto userSubmissionDto, Authentication authentication);
    UserQuizResult getLastResultForQuiz(String quizId, Authentication authentication);
}
