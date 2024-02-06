package kz.kbtu.algoapp.mapper.impl;

import kz.kbtu.algoapp.dto.Answer.AnswerDto;
import kz.kbtu.algoapp.entity.Answer;
import kz.kbtu.algoapp.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper implements Mapper<Answer, AnswerDto> {
    @Override
    public AnswerDto entityToDto(Answer answer) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(answer.getId());
        answerDto.setContent(answer.getContent());
        return answerDto;
    }
}
