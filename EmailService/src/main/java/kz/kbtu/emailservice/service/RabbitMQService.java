package kz.kbtu.emailservice.service;

import kz.kbtu.emailservice.messages.QuizResultMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQService {


    private final EmailService emailService;

    @RabbitListener(queues = "queue.Quiz")
    public void sendQuizResult(QuizResultMessage quizResultMessage) {
        log.info("Message for queue.Quiz is received: {}", quizResultMessage);
        try {
            String subject = formatSubject(quizResultMessage);
            String text = formatEmailBody(quizResultMessage);
            emailService.sendSimpleMessage(quizResultMessage.getUserEmail(), subject, text);
        } catch (Exception e) {
            log.error("Error sending quiz result email", e);
        }
    }

    private String formatSubject(QuizResultMessage quizResultMessage) {
        String quizTopic = quizResultMessage.getTopicName().get("en");
        return "Your quiz result for topic " + quizTopic;
    }

    private String formatEmailBody(QuizResultMessage quizResultMessage) {
        String quizTopic = quizResultMessage.getTopicName().get("en");
        return String.format(
                "Dear %s, You have received score: %d/%d for the topic: %s. " +
                        "\nWe are very appreciated that you use our app.",
                quizResultMessage.getUsername(),
                quizResultMessage.getCorrectAnswers(),
                quizResultMessage.getNumberOfQuestions(),
                quizTopic);
    }
}
