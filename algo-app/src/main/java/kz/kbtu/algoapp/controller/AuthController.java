package kz.kbtu.algoapp.controller;

import jakarta.validation.Valid;
import kz.kbtu.algoapp.dto.User.JwtResponse;
import kz.kbtu.algoapp.dto.User.LoginDto;
import kz.kbtu.algoapp.dto.User.RegistrationDto;
import kz.kbtu.algoapp.dto.Common.SuccessMessage;
import kz.kbtu.algoapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register-user")
    public ResponseEntity<SuccessMessage> registerUser(@Valid @RequestBody RegistrationDto registrationDto) {
        SuccessMessage successMessage = authService.registerUser(registrationDto);
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody LoginDto loginDto) {
        JwtResponse jwtResponse = authService.loginUser(loginDto);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }

    @PatchMapping("/grant-admin")
    public ResponseEntity<SuccessMessage> grantAdminRole(@RequestParam String email) {
        SuccessMessage successMessage = authService.grandAdminRole(email);
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
}
