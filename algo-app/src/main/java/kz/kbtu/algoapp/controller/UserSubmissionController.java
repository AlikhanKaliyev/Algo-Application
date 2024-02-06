package kz.kbtu.algoapp.controller;

import jakarta.validation.Valid;
import kz.kbtu.algoapp.dto.User.UserQuizResult;
import kz.kbtu.algoapp.dto.User.UserSubmissionDto;
import kz.kbtu.algoapp.service.UserSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-interaction")
@RequiredArgsConstructor
public class UserSubmissionController {
    private final UserSubmissionService userSubmissionService;

    @PostMapping("/quiz/{quizId}")
    public ResponseEntity<UserQuizResult> submitAnswer(@PathVariable String quizId, @Valid @RequestBody UserSubmissionDto userSubmissionDto, Authentication authentication) {
        UserQuizResult userQuizResult = userSubmissionService.submitAnswer(quizId, userSubmissionDto, authentication);
        return new ResponseEntity<>(userQuizResult, HttpStatus.OK);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<UserQuizResult> getLastResultForQuiz(@PathVariable String quizId, Authentication authentication) {
        UserQuizResult userQuizResult = userSubmissionService.getLastResultForQuiz(quizId, authentication);
        return new ResponseEntity<>(userQuizResult, HttpStatus.OK);
    }
}
