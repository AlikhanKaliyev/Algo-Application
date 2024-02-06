package kz.kbtu.algoapp.service;


public interface RabbitMQService {
    void sendMessage(String route, Object objectToSend);
}
