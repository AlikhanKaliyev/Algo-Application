package kz.kbtu.algoapp.controller;

import jakarta.validation.Valid;
import kz.kbtu.algoapp.dto.Topic.CreateTopicDto;
import kz.kbtu.algoapp.dto.Topic.TopicDto;
import kz.kbtu.algoapp.dto.Topic.UpdateTopicDto;
import kz.kbtu.algoapp.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topics")
@CrossOrigin("*")
public class TopicController {
    private final TopicService topicService;

    @PreAuthorize( "hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<TopicDto> createTopic(@RequestBody CreateTopicDto createTopicDto) {
        TopicDto topicDto = topicService.createTopic(createTopicDto);
        return new ResponseEntity(topicDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDto> getTopic(@PathVariable String id) {
        TopicDto topicDto = topicService.getTopic(id);
        return new ResponseEntity(topicDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<TopicDto>> getAllTopics() {
        List<TopicDto> topicDtoList = topicService.getAllTopics();
        return new ResponseEntity<>(topicDtoList, HttpStatus.OK);
    }

    @PreAuthorize( "hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TopicDto> updateTopic(@PathVariable String id, @Valid @RequestBody UpdateTopicDto updateTopicDto) {
        TopicDto topicDto = topicService.updateTopic(id, updateTopicDto);
        return new ResponseEntity(topicDto, HttpStatus.OK);
    }

    @PreAuthorize( "hasRole('ADMIN')")
    @PutMapping("")
    public ResponseEntity<TopicDto> assignQuizToTopic(@RequestParam String topicId, @RequestParam String quizId) {
        TopicDto topicDto = topicService.assignQuizToTopic(topicId, quizId);
        return new ResponseEntity(topicDto, HttpStatus.OK);
    }
}
