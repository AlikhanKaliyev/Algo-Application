package kz.kbtu.algoapp.service.impl;

import kz.kbtu.algoapp.service.RabbitMQService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQServiceImpl implements RabbitMQService {

    private final RabbitTemplate rabbitTemplate;

    private final TopicExchange exchange;

    @Override
    public void sendMessage(String route, Object objectToSend) {
        rabbitTemplate.convertAndSend(exchange.getName(), route, objectToSend);
    }
}
