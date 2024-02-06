package kz.kbtu.algoapp.service;

import kz.kbtu.algoapp.dto.Quiz.CreateQuizDto;
import kz.kbtu.algoapp.dto.Quiz.QuizDto;
import kz.kbtu.algoapp.dto.Quiz.UpdateQuizDto;

public interface QuizService {
    QuizDto createQuiz(CreateQuizDto createQuizDto);

    QuizDto updateQuiz(String id, UpdateQuizDto updateQuizDto);

    QuizDto getQuizById(String id);
}
