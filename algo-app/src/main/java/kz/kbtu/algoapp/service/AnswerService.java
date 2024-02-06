package kz.kbtu.algoapp.service;

import kz.kbtu.algoapp.dto.Answer.AnswerDto;
import kz.kbtu.algoapp.dto.Answer.CreateAnswerDto;
import kz.kbtu.algoapp.dto.Answer.UpdateAnswerDto;

public interface AnswerService {
    AnswerDto createAnswer(CreateAnswerDto answerDto);

    AnswerDto updateAnswer(String id, UpdateAnswerDto updateAnswerDto);

    AnswerDto getAnswerById(String id);
}
