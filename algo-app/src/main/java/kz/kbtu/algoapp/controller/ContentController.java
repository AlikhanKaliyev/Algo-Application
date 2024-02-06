package kz.kbtu.algoapp.controller;

import jakarta.validation.Valid;
import kz.kbtu.algoapp.dto.Content.ContentDto;
import kz.kbtu.algoapp.dto.Content.CreateContentDto;
import kz.kbtu.algoapp.dto.Content.UpdateContentDto;
import kz.kbtu.algoapp.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contents")
@CrossOrigin("*")
public class ContentController {
    private final ContentService contentService;

    @PreAuthorize( "hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<ContentDto> createContent(@Valid @RequestBody CreateContentDto createContentDto) {
        ContentDto contentDto = contentService.createContent(createContentDto);
        return new ResponseEntity(contentDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentDto> getContentBySubTopicId(@PathVariable String id) {
        ContentDto contentDto = contentService.getContentBySubTopicId(id);
        return new ResponseEntity(contentDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ContentDto> updateContentBySubTopicId(@PathVariable String id, @Valid @RequestBody UpdateContentDto updateContentDto) {
        ContentDto contentDto = contentService.updateContentBySybTopicId(id, updateContentDto);
        return new ResponseEntity(contentDto, HttpStatus.OK);
    }
}
