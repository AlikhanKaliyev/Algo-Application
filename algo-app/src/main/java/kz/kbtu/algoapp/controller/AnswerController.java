package kz.kbtu.algoapp.controller;

import jakarta.validation.Valid;
import kz.kbtu.algoapp.dto.Answer.AnswerDto;
import kz.kbtu.algoapp.dto.Answer.CreateAnswerDto;
import kz.kbtu.algoapp.dto.Answer.UpdateAnswerDto;
import kz.kbtu.algoapp.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answers")
public class AnswerController {
    private final AnswerService answerService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<AnswerDto> createAnswer(@Valid @RequestBody CreateAnswerDto createAnswerDto) {
        AnswerDto answerDto = answerService.createAnswer(createAnswerDto);
        return new ResponseEntity<>(answerDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AnswerDto> updateAnswer(@RequestParam String id, @Valid @RequestBody UpdateAnswerDto updateAnswerDto) {
        AnswerDto answerDto = answerService.updateAnswer(id, updateAnswerDto);
        return new ResponseEntity<>(answerDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerDto> getAnswerById(@RequestParam String id) {
        AnswerDto answerDto = answerService.getAnswerById(id);
        return new ResponseEntity<>(answerDto, HttpStatus.OK);
    }
}
