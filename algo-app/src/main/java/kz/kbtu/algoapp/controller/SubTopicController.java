package kz.kbtu.algoapp.controller;

import jakarta.validation.Valid;
import kz.kbtu.algoapp.dto.SubTopic.CreateSubTopicDto;
import kz.kbtu.algoapp.dto.SubTopic.SubTopicDto;
import kz.kbtu.algoapp.dto.SubTopic.UpdateSubTopicDto;
import kz.kbtu.algoapp.service.SubTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sub-topics")
public class SubTopicController {
    private final SubTopicService subTopicService;

    @PreAuthorize( "hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<SubTopicDto> createSubTopic(@Valid @RequestBody CreateSubTopicDto createSubTopicDto) {
        SubTopicDto subTopicDto = subTopicService.createSubTopic(createSubTopicDto);
        return new ResponseEntity(subTopicDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubTopicDto> getSubTopic(@PathVariable String id) {
        SubTopicDto subTopicDto = subTopicService.getSubTopic(id);
        return new ResponseEntity(subTopicDto, HttpStatus.OK);
    }

    @PreAuthorize( "hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<SubTopicDto> updateSubTopic(@PathVariable String id,@Valid @RequestBody UpdateSubTopicDto updateSubTopicDto) {
        SubTopicDto subTopicDto = subTopicService.updateSubTopic(id, updateSubTopicDto);
        return new ResponseEntity(subTopicDto, HttpStatus.OK);
    }

}
