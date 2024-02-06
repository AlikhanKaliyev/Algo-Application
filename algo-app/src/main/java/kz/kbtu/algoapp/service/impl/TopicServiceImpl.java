package kz.kbtu.algoapp.service.impl;

import kz.kbtu.algoapp.dto.Topic.CreateTopicDto;
import kz.kbtu.algoapp.dto.Topic.TopicDto;
import kz.kbtu.algoapp.dto.Topic.UpdateTopicDto;
import kz.kbtu.algoapp.entity.Quiz;
import kz.kbtu.algoapp.entity.SubTopic;
import kz.kbtu.algoapp.entity.Topic;
import kz.kbtu.algoapp.exception.AppException;
import kz.kbtu.algoapp.mapper.impl.TopicMapper;
import kz.kbtu.algoapp.repository.QuizRepository;
import kz.kbtu.algoapp.repository.SubTopicRepository;
import kz.kbtu.algoapp.repository.TopicRepository;
import kz.kbtu.algoapp.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final SubTopicRepository subTopicRepository;
    private final QuizRepository quizRepository;
    private final TopicMapper topicMapper;

    @Override
    public TopicDto createTopic(CreateTopicDto createTopicDto) {
        Topic topic = new Topic();
        topic.setTitle(createTopicDto.getTitle());
        List<SubTopic> subTopicList = createTopicDto.getSubTopics()
                .stream().map(id -> subTopicRepository.findById(id)
                        .orElseThrow(() -> new AppException("no subtopic with id: " + id)))
                .collect(Collectors.toList());
        topic.setSubTopics(subTopicList);
        topic = topicRepository.save(topic);
        return topicMapper.entityToDto(topic);
    }

    @Override
    public TopicDto getTopic(String id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new AppException("No topic with such an id: " + id));
        return topicMapper.entityToDto(topic);
    }

    @Override
    public List<TopicDto> getAllTopics() {
        return topicRepository.findAll()
                .stream().map(topicMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TopicDto updateTopic(String id, UpdateTopicDto updateTopicDto) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new AppException("no topic with id:" + id));
        topic.setTitle(updateTopicDto.getTitle());
        List<SubTopic> subTopicList = updateTopicDto.getSubTopics()
                .stream().map(localId -> subTopicRepository.findById(localId)
                        .orElseThrow(() -> new AppException("no subtopic with id:" + localId)))
                .collect(Collectors.toList());
        topic.setSubTopics(subTopicList);
        topic = topicRepository.save(topic);
        return topicMapper.entityToDto(topic);
    }

    @Override
    public TopicDto assignQuizToTopic(String topicId, String quizId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new AppException("no topic with id:" + topicId));
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new AppException("no quiz with id:" + quizId));
        topic.setQuiz(quiz);
        topic = topicRepository.save(topic);
        return topicMapper.entityToDto(topic);
    }
}
