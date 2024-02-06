package kz.kbtu.emailservice.controller;

import kz.kbtu.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final EmailService emailService;

    @GetMapping("/test")
    public String sendMessage() {
        emailService.sendSimpleMessage("alikhankaliyev20@gmail.com", "smtp", "Alikhan is cool");
        return "it works";
    }
}
