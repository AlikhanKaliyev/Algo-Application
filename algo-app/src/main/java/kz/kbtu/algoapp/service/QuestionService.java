package kz.kbtu.algoapp.service;

import kz.kbtu.algoapp.dto.Question.CreateQuestionDto;
import kz.kbtu.algoapp.dto.Question.QuestionDto;
import kz.kbtu.algoapp.dto.Question.UpdateQuestionDto;

public interface QuestionService {
    QuestionDto createQuestion(CreateQuestionDto createQuestionDto);

    QuestionDto updateQuestion(String id, UpdateQuestionDto updateQuestionDto);

    QuestionDto getQuestionById(String id);
}
