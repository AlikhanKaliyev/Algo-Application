package kz.kbtu.algoapp.service.impl;

import kz.kbtu.algoapp.dto.RabbitMQ.QuizResultMessage;
import kz.kbtu.algoapp.dto.User.UserQuestionCorrectness;
import kz.kbtu.algoapp.dto.User.UserQuizResult;
import kz.kbtu.algoapp.dto.User.UserSubmissionDto;
import kz.kbtu.algoapp.entity.Quiz;
import kz.kbtu.algoapp.entity.Topic;
import kz.kbtu.algoapp.entity.User;
import kz.kbtu.algoapp.entity.UserSubmission;
import kz.kbtu.algoapp.exception.AppException;
import kz.kbtu.algoapp.repository.QuizRepository;
import kz.kbtu.algoapp.repository.TopicRepository;
import kz.kbtu.algoapp.repository.UserRepository;
import kz.kbtu.algoapp.repository.UserSubmissionRepository;
import kz.kbtu.algoapp.service.AuthService;
import kz.kbtu.algoapp.service.RabbitMQService;
import kz.kbtu.algoapp.service.UserSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSubmissionServiceImpl implements UserSubmissionService {
    private final QuizRepository quizRepository;

    private final UserSubmissionRepository userSubmissionRepository;

    private final TopicRepository topicRepository;

    private final AuthService authService;

    private final RabbitMQService rabbitMQService;

    @Override
    public UserQuizResult submitAnswer(String quizId, UserSubmissionDto userSubmissionDto, Authentication authentication) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new AppException("no quiz with such an id"));
        User user = authService.getUser(authentication);
        if (quiz.getQuestions().size() != userSubmissionDto.getAnswers().size()) throw new AppException("not all questions are answered");

        List<List<String>> correctAnswers = quiz.getQuestions()
                .stream().map(question -> {
                    List<String> correct = question.getCorrect()
                            .stream().map(answer -> answer.getId()).collect(Collectors.toList());
                    return correct;
                }).collect(Collectors.toList());
        List<List<String>> userAnswers = userSubmissionDto.getAnswers();
        List<UserQuestionCorrectness> questionCorrectnessList = new ArrayList<>();

        int numberOfQuestions = quiz.getQuestions().size();
        int numberOfCorrectAnswers = 0;

        for (int i = 0; i < numberOfQuestions; i++) {
            UserQuestionCorrectness questionCorrectness = calculateQuestionCorrectness(i, correctAnswers, userAnswers, quiz);
            if (questionCorrectness.getIsCorrect()) numberOfCorrectAnswers += 1;
            questionCorrectnessList.add(questionCorrectness);
        }

        UserQuizResult userQuizResult = new UserQuizResult();
        userQuizResult.setResult(questionCorrectnessList);
        userQuizResult.setNumberOfQuestions(numberOfQuestions);
        userQuizResult.setCorrectAnswers(numberOfCorrectAnswers);

        double percentage = ((double) numberOfCorrectAnswers) / ((double) numberOfQuestions) * 100;

        userQuizResult.setPercentage(percentage);

        UserSubmission userSubmission = new UserSubmission();
        userSubmission.setUser(user);
        userSubmission.setQuiz(quiz);
        userSubmission.setUserQuizResult(userQuizResult);

        userSubmissionRepository.save(userSubmission);

        ExecutorService executor = Executors.newSingleThreadExecutor();

        final int finalNumberOfCorrectAnswers = numberOfCorrectAnswers;

        executor.submit(() -> {
            sendMessageToRabbitBroker(user, quiz, numberOfQuestions, finalNumberOfCorrectAnswers);
        });

        executor.shutdown();

        return userQuizResult;
    }

    @Override
    public UserQuizResult getLastResultForQuiz(String quizId, Authentication authentication) {
        User user = authService.getUser(authentication);
        List<UserSubmission> userSubmissionList = userSubmissionRepository.findAll()
                .stream()
                .filter(userSubmission -> userSubmission.getUser().equals(user))
                .collect(Collectors.toList());
        if(userSubmissionList.isEmpty()) throw new AppException("no submissions are found");
        return userSubmissionList.get(userSubmissionList.size() - 1).getUserQuizResult();
    }

    private void sendMessageToRabbitBroker(User user, Quiz quiz, Integer numberOfQuestions, Integer numberOfCorrectAnswers) {
        Topic topic = topicRepository.findTopicByQuizID(quiz.getId()).orElseThrow();
        QuizResultMessage quizResultMessage = new QuizResultMessage();
        quizResultMessage.setUserEmail(user.getEmail());
        quizResultMessage.setUsername(user.getUsername());
        quizResultMessage.setCorrectAnswers(numberOfCorrectAnswers);
        quizResultMessage.setNumberOfQuestions(numberOfQuestions);
        quizResultMessage.setTopicName(topic.getTitle());
        rabbitMQService.sendMessage("routing.quiz", quizResultMessage);
    }

    private UserQuestionCorrectness calculateQuestionCorrectness(Integer index, List<List<String>> correctAnswers, List<List<String>> userAnswers, Quiz quiz) {
        Set<String> currentCorrectAnswers = new HashSet<>(correctAnswers.get(index));
        Set<String> currentUserAnswers = new HashSet<>(userAnswers.get(index));
        UserQuestionCorrectness questionCorrectness = new UserQuestionCorrectness();

        questionCorrectness.setQuestionId(quiz.getQuestions().get(index).getId());
        questionCorrectness.setCorrectAnswers(currentCorrectAnswers);
        questionCorrectness.setUserAnswers(currentUserAnswers);

        boolean result = currentCorrectAnswers.equals(currentUserAnswers);

        questionCorrectness.setIsCorrect(result);

        return questionCorrectness;
    }
}
