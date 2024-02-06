package kz.kbtu.emailservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    private final String ROUTING_QUIZ = "routing.quiz.#";

    private final String ROUTING_NEWS_LETTER = "routing.newsletter.#";

    @Bean
    Queue queueQuiz() { return new Queue("queue.Quiz", false); }

    @Bean
    Queue queueNewsLetter() { return new Queue("queue.Newsletter", false); }

    @Bean
    TopicExchange exchange() { return new TopicExchange("exchange.topic"); }

    @Bean
    Binding bindingQuiz(Queue queueQuiz, TopicExchange exchange) {
        return BindingBuilder.bind(queueQuiz).to(exchange).with(ROUTING_QUIZ);
    }

    @Bean
    Binding bindingNewsLetter(Queue queueNewsLetter, TopicExchange exchange) {
        return BindingBuilder.bind(queueNewsLetter).to(exchange).with(ROUTING_NEWS_LETTER);
    }

    @Bean
    MessageConverter messageConverter(ObjectMapper mapper) { return new Jackson2JsonMessageConverter(mapper); }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        ObjectMapper mapper = new ObjectMapper();
        rabbitTemplate.setMessageConverter(messageConverter(mapper));
        return rabbitTemplate;
    }
}
