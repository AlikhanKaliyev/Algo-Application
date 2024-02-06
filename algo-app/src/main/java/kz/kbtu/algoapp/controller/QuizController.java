package kz.kbtu.algoapp.controller;

import jakarta.validation.Valid;
import kz.kbtu.algoapp.dto.Quiz.CreateQuizDto;
import kz.kbtu.algoapp.dto.Quiz.QuizDto;
import kz.kbtu.algoapp.dto.Quiz.UpdateQuizDto;
import kz.kbtu.algoapp.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<QuizDto> createQuiz(@Valid @RequestBody CreateQuizDto createQuizDto) {
        QuizDto quizDto = quizService.createQuiz(createQuizDto);
        return new ResponseEntity<>(quizDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<QuizDto> updateQuiz(@RequestParam String id, @Valid @RequestBody UpdateQuizDto updateQuizDto) {
        QuizDto quizDto = quizService.updateQuiz(id, updateQuizDto);
        return new ResponseEntity<>(quizDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuizById(@RequestParam String id) {
        QuizDto quizDto = quizService.getQuizById(id);
        return new ResponseEntity<>(quizDto, HttpStatus.OK);
    }
}
