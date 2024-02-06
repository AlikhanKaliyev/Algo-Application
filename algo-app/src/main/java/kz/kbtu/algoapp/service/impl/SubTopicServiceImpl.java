package kz.kbtu.algoapp.service.impl;

import kz.kbtu.algoapp.dto.SubTopic.CreateSubTopicDto;
import kz.kbtu.algoapp.dto.SubTopic.SubTopicDto;
import kz.kbtu.algoapp.dto.SubTopic.UpdateSubTopicDto;
import kz.kbtu.algoapp.entity.SubTopic;
import kz.kbtu.algoapp.exception.AppException;
import kz.kbtu.algoapp.mapper.impl.SubTopicMapper;
import kz.kbtu.algoapp.repository.SubTopicRepository;
import kz.kbtu.algoapp.service.SubTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubTopicServiceImpl implements SubTopicService {
    private final SubTopicRepository subTopicRepository;
    private final SubTopicMapper subTopicMapper;
    @Override
    public SubTopicDto createSubTopic(CreateSubTopicDto createSubTopicDTO) {
        SubTopic subTopic = new SubTopic();
        subTopic.setTitle(createSubTopicDTO.getTitle());
        subTopic = subTopicRepository.save(subTopic);
        return subTopicMapper.entityToDto(subTopic);
    }

    @Override
    public SubTopicDto getSubTopic(String id) {
        SubTopic subTopic = subTopicRepository.findById(id)
                .orElseThrow(() -> new AppException("no subtopic with such an id " + id));
        return subTopicMapper.entityToDto(subTopic);
    }

    @Override
    public SubTopicDto updateSubTopic(String id, UpdateSubTopicDto updateSubTopicDto) {
        SubTopic subTopic = subTopicRepository.findById(id)
                .orElseThrow(() -> new AppException("no subtopic with such an id: " + id));
        subTopic.setTitle(updateSubTopicDto.getTitle());
        subTopic = subTopicRepository.save(subTopic);
        return subTopicMapper.entityToDto(subTopic);
    }
}
