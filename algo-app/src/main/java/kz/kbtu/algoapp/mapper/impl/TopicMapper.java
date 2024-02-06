package kz.kbtu.algoapp.mapper.impl;

import kz.kbtu.algoapp.dto.SubTopic.SubTopicDto;
import kz.kbtu.algoapp.dto.Topic.TopicDto;
import kz.kbtu.algoapp.entity.Quiz;
import kz.kbtu.algoapp.entity.Topic;
import kz.kbtu.algoapp.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class TopicMapper implements Mapper<Topic, TopicDto> {

    private final SubTopicMapper subTopicMapper;


    @Override
    public TopicDto entityToDto(Topic topic) {
        TopicDto topicDto = new TopicDto();

        topicDto.setId(topic.getId());
        topicDto.setTitle(topic.getTitle());
        List<SubTopicDto> subTopicDtoList = topic.getSubTopics()
                .stream().map(subTopicMapper :: entityToDto)
                .collect(Collectors.toList());
        Quiz quiz = topic.getQuiz();
        topicDto.setQuizId(quiz != null ? quiz.getId() : null);
        topicDto.setSubTopics(subTopicDtoList);
        return topicDto;
    }

}
