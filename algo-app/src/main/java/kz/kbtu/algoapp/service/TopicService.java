package kz.kbtu.algoapp.service;

import kz.kbtu.algoapp.dto.Topic.CreateTopicDto;
import kz.kbtu.algoapp.dto.Topic.TopicDto;
import kz.kbtu.algoapp.dto.Topic.UpdateTopicDto;

import java.util.List;

public interface TopicService {
    TopicDto createTopic(CreateTopicDto createTopicDto);
    TopicDto getTopic(String id);
    List<TopicDto> getAllTopics();
    TopicDto updateTopic(String id, UpdateTopicDto updateTopicDto);
    TopicDto assignQuizToTopic(String topicId, String quizId);
}
