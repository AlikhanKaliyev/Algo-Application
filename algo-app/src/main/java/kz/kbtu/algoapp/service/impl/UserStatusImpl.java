package kz.kbtu.algoapp.service.impl;

import kz.kbtu.algoapp.dto.Topic.IsTopicCompleted;
import kz.kbtu.algoapp.dto.User.UserStatus;
import kz.kbtu.algoapp.entity.Quiz;
import kz.kbtu.algoapp.entity.Topic;
import kz.kbtu.algoapp.entity.User;
import kz.kbtu.algoapp.entity.UserSubmission;
import kz.kbtu.algoapp.repository.TopicRepository;
import kz.kbtu.algoapp.repository.UserSubmissionRepository;
import kz.kbtu.algoapp.service.AuthService;
import kz.kbtu.algoapp.service.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStatusImpl implements UserStatusService {

    private final TopicRepository topicRepository;

    private final UserSubmissionRepository userSubmissionRepository;

    private final AuthService authService;
    @Override
    public UserStatus getUserStatus(Authentication authentication) {
        List<Topic> topics = topicRepository.findAll();
        int numberOfTopics = topics.size();
        User user = authService.getUser(authentication);
        List<IsTopicCompleted> topicsCompleted = new ArrayList<>();

        for (int i = 0; i < numberOfTopics; i++) {
            Topic currentTopic = topics.get(i);
            Quiz currentQuiz = currentTopic.getQuiz();
            List<UserSubmission> userSubmissionList = userSubmissionRepository.findUserSubmissionByUserAndQuiz(user.getId(), currentQuiz.getId());

            Boolean isTopicDoneByUser = userSubmissionList.isEmpty() ? false : true;

            IsTopicCompleted isTopicCompleted = new IsTopicCompleted();
            isTopicCompleted.setId(currentTopic.getId());
            isTopicCompleted.setIsDone(isTopicDoneByUser);

            topicsCompleted.add(isTopicCompleted);
        }

        UserStatus userStatus = new UserStatus();
        userStatus.setTopicsCompleted(topicsCompleted);

        return userStatus;
    }
}
