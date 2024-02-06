package kz.kbtu.algoapp.service;


import kz.kbtu.algoapp.dto.User.JwtResponse;
import kz.kbtu.algoapp.dto.User.LoginDto;
import kz.kbtu.algoapp.dto.User.RegistrationDto;
import kz.kbtu.algoapp.dto.Common.SuccessMessage;
import kz.kbtu.algoapp.entity.User;
import kz.kbtu.algoapp.exception.AppException;
import org.springframework.security.core.Authentication;

public interface AuthService {
    SuccessMessage registerUser(RegistrationDto registrationDto);

    JwtResponse loginUser(LoginDto loginDto);

    SuccessMessage grandAdminRole(String email);

    User getUser(Authentication authentication);
}
