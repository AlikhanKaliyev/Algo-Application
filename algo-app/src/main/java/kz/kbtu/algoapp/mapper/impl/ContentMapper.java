package kz.kbtu.algoapp.mapper.impl;

import kz.kbtu.algoapp.dto.Content.ContentDto;
import kz.kbtu.algoapp.entity.Content;
import kz.kbtu.algoapp.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ContentMapper implements Mapper<Content, ContentDto> {

    @Override
    public ContentDto entityToDto(Content content) {
        ContentDto contentDto = new ContentDto();
        contentDto.setId(content.getId());
        contentDto.setContent(content.getContent());
        return contentDto;
    }
}
