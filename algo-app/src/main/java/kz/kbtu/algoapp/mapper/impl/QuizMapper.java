package kz.kbtu.algoapp.mapper.impl;

import kz.kbtu.algoapp.dto.Question.QuestionDto;
import kz.kbtu.algoapp.dto.Quiz.QuizDto;
import kz.kbtu.algoapp.entity.Quiz;
import kz.kbtu.algoapp.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuizMapper implements Mapper<Quiz, QuizDto> {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public QuizDto entityToDto(Quiz quiz) {
        QuizDto quizDto = new QuizDto();
        quizDto.setId(quiz.getId());
        List<QuestionDto> questionDtoList = quiz.getQuestions()
                .stream().map(question -> {
                    QuestionDto questionDto = questionMapper.entityToDto(question);
                    questionDto.setCorrect(null);
                    return questionDto;
                })
                .collect(Collectors.toList());
        quizDto.setQuestions(questionDtoList);
        return quizDto;
    }
}
