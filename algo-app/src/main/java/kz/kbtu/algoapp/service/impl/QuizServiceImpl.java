package kz.kbtu.algoapp.service.impl;

import kz.kbtu.algoapp.dto.Quiz.CreateQuizDto;
import kz.kbtu.algoapp.dto.Quiz.QuizDto;
import kz.kbtu.algoapp.dto.Quiz.UpdateQuizDto;
import kz.kbtu.algoapp.entity.Question;
import kz.kbtu.algoapp.entity.Quiz;
import kz.kbtu.algoapp.exception.AppException;
import kz.kbtu.algoapp.mapper.impl.QuizMapper;
import kz.kbtu.algoapp.repository.QuestionRepository;
import kz.kbtu.algoapp.repository.QuizRepository;
import kz.kbtu.algoapp.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    private final QuizMapper quizMapper;

    @Override
    public QuizDto createQuiz(CreateQuizDto createQuizDto) {
        Quiz quiz = new Quiz();
        List<Question> questionList = createQuizDto.getQuestions()
                .stream().map(questionId ->
                        questionRepository.findById(questionId).orElseThrow(() -> new AppException("no question with such an id:" + questionId))
                ).collect(Collectors.toList());
        quiz.setQuestions(questionList);
        quiz = quizRepository.save(quiz);
        return quizMapper.entityToDto(quiz);
    }

    @Override
    public QuizDto updateQuiz(String id, UpdateQuizDto updateQuizDto) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new AppException("no quiz with such an id"));
        List<Question> questionList = updateQuizDto.getQuestions()
                .stream().map(questionId ->
                        questionRepository.findById(questionId).orElseThrow(() -> new AppException("no question with such an id:" + questionId))
                ).collect(Collectors.toList());
        quiz.setQuestions(questionList);
        quiz = quizRepository.save(quiz);
        return quizMapper.entityToDto(quiz);
    }

    @Override
    public QuizDto getQuizById(String id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new AppException("no quiz with such an id"));
        return quizMapper.entityToDto(quiz);
    }
}
