package kz.kbtu.algoapp.service;

import kz.kbtu.algoapp.dto.User.UserStatus;
import org.springframework.security.core.Authentication;

public interface UserStatusService {
    UserStatus getUserStatus(Authentication authentication);
}
