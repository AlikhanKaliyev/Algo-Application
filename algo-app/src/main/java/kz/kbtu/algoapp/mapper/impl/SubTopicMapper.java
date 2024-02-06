package kz.kbtu.algoapp.mapper.impl;

import kz.kbtu.algoapp.dto.SubTopic.SubTopicDto;
import kz.kbtu.algoapp.entity.SubTopic;
import kz.kbtu.algoapp.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SubTopicMapper implements Mapper<SubTopic, SubTopicDto> {
    @Override
    public SubTopicDto entityToDto(SubTopic subTopic) {
        SubTopicDto subTopicDto = new SubTopicDto();

        subTopicDto.setId(subTopic.getId());
        subTopicDto.setTitle(subTopic.getTitle());

        return subTopicDto;
    }
}
