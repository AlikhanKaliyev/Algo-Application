package kz.kbtu.algoapp.service;


import kz.kbtu.algoapp.dto.SubTopic.CreateSubTopicDto;
import kz.kbtu.algoapp.dto.SubTopic.SubTopicDto;
import kz.kbtu.algoapp.dto.SubTopic.UpdateSubTopicDto;


public interface SubTopicService {
    SubTopicDto createSubTopic(CreateSubTopicDto createSubTopicDTO);
    SubTopicDto getSubTopic(String id);
    SubTopicDto updateSubTopic(String id, UpdateSubTopicDto updateSubTopicDto);
}
