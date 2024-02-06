package kz.kbtu.algoapp.service.impl;

import kz.kbtu.algoapp.dto.Question.CreateQuestionDto;
import kz.kbtu.algoapp.dto.Question.QuestionDto;
import kz.kbtu.algoapp.dto.Question.UpdateQuestionDto;
import kz.kbtu.algoapp.entity.Answer;
import kz.kbtu.algoapp.entity.Question;
import kz.kbtu.algoapp.exception.AppException;
import kz.kbtu.algoapp.mapper.impl.QuestionMapper;
import kz.kbtu.algoapp.repository.AnswerRepository;
import kz.kbtu.algoapp.repository.QuestionRepository;
import kz.kbtu.algoapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final QuestionMapper questionMapper;

    @Override
    public QuestionDto createQuestion(CreateQuestionDto createQuestionDto) {
        Question question = new Question();
        question.setType(createQuestionDto.getType());
        question.setContent(createQuestionDto.getContent());
        List<Answer> answerList = createQuestionDto.getAnswers()
                        .stream()
                .map(answerId -> answerRepository.findById(answerId).orElseThrow(() -> new AppException("no answer with such an id")))
                        .collect(Collectors.toList());
        List<Answer> correctList = createQuestionDto.getCorrect()
                .       stream()
                .map(answerId -> answerRepository.findById(answerId).orElseThrow(() -> new AppException("no answer with such an id")))
                .collect(Collectors.toList());
        question.setAnswers(answerList);
        question.setCorrect(correctList);
        question = questionRepository.save(question);
        return questionMapper.entityToDto(question);
    }

    @Override
    public QuestionDto updateQuestion(String id, UpdateQuestionDto updateQuestionDto) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new AppException("no answer with such an id"));
        question.setType(updateQuestionDto.getType());
        question.setContent(updateQuestionDto.getContent());
        List<Answer> answerList = updateQuestionDto.getAnswers()
                .stream()
                .map(answerId -> answerRepository.findById(answerId).orElseThrow(() -> new AppException("no answer with such an id")))
                .collect(Collectors.toList());
        List<Answer> correctList = updateQuestionDto.getCorrect()
                .       stream()
                .map(answerId -> answerRepository.findById(answerId).orElseThrow(() -> new AppException("no answer with such an id")))
                .collect(Collectors.toList());
        question.setAnswers(answerList);
        question.setCorrect(correctList);
        question = questionRepository.save(question);
        return questionMapper.entityToDto(question);
    }

    @Override
    public QuestionDto getQuestionById(String id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new AppException("no answer with such an id"));
        return questionMapper.entityToDto(question);
    }
}
