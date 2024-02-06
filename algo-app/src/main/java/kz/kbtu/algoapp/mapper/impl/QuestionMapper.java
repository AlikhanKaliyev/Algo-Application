package kz.kbtu.algoapp.mapper.impl;

import kz.kbtu.algoapp.dto.Answer.AnswerDto;
import kz.kbtu.algoapp.dto.Question.QuestionDto;
import kz.kbtu.algoapp.entity.Question;
import kz.kbtu.algoapp.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper implements Mapper<Question, QuestionDto> {

    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public QuestionDto entityToDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(question.getId());
        questionDto.setType(question.getType());
        questionDto.setContent(question.getContent());
        List<AnswerDto> answerDtoList = question.getAnswers()
                .stream().map(answerMapper :: entityToDto)
                .collect(Collectors.toList());
        List<AnswerDto> correctDtoList = question.getCorrect()
                .stream().map(answerMapper :: entityToDto)
                .collect(Collectors.toList());
        questionDto.setAnswers(answerDtoList);
        questionDto.setCorrect(correctDtoList);
        return questionDto;
    }
}
