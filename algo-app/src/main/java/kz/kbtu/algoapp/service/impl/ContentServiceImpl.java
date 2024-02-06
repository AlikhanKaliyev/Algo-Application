package kz.kbtu.algoapp.service.impl;

import kz.kbtu.algoapp.dto.Content.ContentDto;
import kz.kbtu.algoapp.dto.Content.CreateContentDto;
import kz.kbtu.algoapp.dto.Content.UpdateContentDto;
import kz.kbtu.algoapp.entity.Content;
import kz.kbtu.algoapp.entity.SubTopic;
import kz.kbtu.algoapp.exception.AppException;
import kz.kbtu.algoapp.mapper.impl.ContentMapper;
import kz.kbtu.algoapp.repository.ContentRepository;
import kz.kbtu.algoapp.repository.SubTopicRepository;
import kz.kbtu.algoapp.service.ContentService;
import kz.kbtu.algoapp.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;
    private final SubTopicRepository subTopicRepository;
    private final ContentMapper contentMapper;

    @Override
    public ContentDto createContent(CreateContentDto createContentDto) {
        Content content = new Content();
        SubTopic subTopic = subTopicRepository.findById(createContentDto.getSubTopicId())
                .orElseThrow(() -> new AppException("no subtopic with such an id"));
        content.setSubTopic(subTopic);
        validateContent(createContentDto.getContent());
        content.setContent(createContentDto.getContent());
        content = contentRepository.save(content);
        return contentMapper.entityToDto(content);
    }

    @Override
    public ContentDto getContentBySubTopicId(String id) {
        Content content = contentRepository.findContentBySubTopicId(id)
                .orElseThrow(() -> new AppException("subtopic doesn't exist or there is no content with such a subtopic"));
        return contentMapper.entityToDto(content);
    }

    @Override
    public ContentDto updateContentBySybTopicId(String id, UpdateContentDto updateContentDto) {
        Content content = contentRepository.findContentBySubTopicId(id)
                .orElseThrow(() -> new AppException("subtopic doesn't exist or there is no content with such a subtopic"));
        validateContent(updateContentDto.getContent());
        content.setContent(updateContentDto.getContent());
        content = contentRepository.save(content);
        return contentMapper.entityToDto(content);
    }

    private void validateContent(List<Map<String, Object>> content) {
        for (Map<String, Object> object : content) {
            if(object.get("type").equals("code")) {
                Map<String, Object> text = (Map<String, Object>) object.get("text");
                for (Map.Entry<String, Object> code : text.entrySet()) {
                    if(!Utils.validateBase64((String) code.getValue())) {
                        throw new AppException("invalid base64 code");
                    }
                }
            }
        }
    }
}
