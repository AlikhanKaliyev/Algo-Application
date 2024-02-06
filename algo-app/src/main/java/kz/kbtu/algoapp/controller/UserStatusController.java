package kz.kbtu.algoapp.controller;

import kz.kbtu.algoapp.dto.User.UserStatus;
import kz.kbtu.algoapp.service.UserStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-status")
@RequiredArgsConstructor
public class UserStatusController {
    private final UserStatusService userStatusService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping()
    public ResponseEntity<UserStatus> getUserStatus(Authentication authentication) {
        UserStatus userStatus = userStatusService.getUserStatus(authentication);
        return new ResponseEntity<>(userStatus, HttpStatus.OK);
    }
}
