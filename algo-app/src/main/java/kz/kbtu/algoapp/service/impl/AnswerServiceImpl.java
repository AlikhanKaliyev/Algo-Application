package kz.kbtu.algoapp.service.impl;

import kz.kbtu.algoapp.dto.Answer.AnswerDto;
import kz.kbtu.algoapp.dto.Answer.CreateAnswerDto;
import kz.kbtu.algoapp.dto.Answer.UpdateAnswerDto;
import kz.kbtu.algoapp.entity.Answer;
import kz.kbtu.algoapp.exception.AppException;
import kz.kbtu.algoapp.mapper.impl.AnswerMapper;
import kz.kbtu.algoapp.repository.AnswerRepository;
import kz.kbtu.algoapp.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Override
    public AnswerDto createAnswer(CreateAnswerDto createAnswerDto) {
        Answer answer = new Answer();
        answer.setContent(createAnswerDto.getContent());
        answer = answerRepository.save(answer);
        return answerMapper.entityToDto(answer);
    }

    @Override
    public AnswerDto updateAnswer(String id, UpdateAnswerDto updateAnswerDto) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new AppException("no answer with such an id"));
        answer.setContent(updateAnswerDto.getContent());
        answer = answerRepository.save(answer);
        return answerMapper.entityToDto(answer);
    }

    @Override
    public AnswerDto getAnswerById(String id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new AppException("no answer  with such an id"));
        return answerMapper.entityToDto(answer);
    }
}
