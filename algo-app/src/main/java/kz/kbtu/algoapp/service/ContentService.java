package kz.kbtu.algoapp.service;


import kz.kbtu.algoapp.dto.Content.ContentDto;
import kz.kbtu.algoapp.dto.Content.CreateContentDto;
import kz.kbtu.algoapp.dto.Content.UpdateContentDto;

public interface ContentService {
    ContentDto createContent(CreateContentDto createContentDto);

    ContentDto getContentBySubTopicId(String id);

    ContentDto updateContentBySybTopicId(String id, UpdateContentDto updateContentDto);
}
