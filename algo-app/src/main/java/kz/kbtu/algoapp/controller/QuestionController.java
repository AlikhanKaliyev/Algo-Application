package kz.kbtu.algoapp.controller;

import jakarta.validation.Valid;
import kz.kbtu.algoapp.dto.Question.CreateQuestionDto;
import kz.kbtu.algoapp.dto.Question.QuestionDto;
import kz.kbtu.algoapp.dto.Question.UpdateQuestionDto;
import kz.kbtu.algoapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<QuestionDto> createQuestion(@Valid @RequestBody CreateQuestionDto createQuestionDto) {
        QuestionDto questionDto = questionService.createQuestion(createQuestionDto);
        return new ResponseEntity<>(questionDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<QuestionDto> updateQuestion(@RequestParam String id, @Valid @RequestBody UpdateQuestionDto updateQuestionDto) {
        QuestionDto questionDto = questionService.updateQuestion(id, updateQuestionDto);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@RequestParam String id) {
        QuestionDto questionDto = questionService.getQuestionById(id);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }
}

