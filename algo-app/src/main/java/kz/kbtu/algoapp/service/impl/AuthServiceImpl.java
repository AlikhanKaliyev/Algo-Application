package kz.kbtu.algoapp.service.impl;

import kz.kbtu.algoapp.dto.User.JwtResponse;
import kz.kbtu.algoapp.dto.User.LoginDto;
import kz.kbtu.algoapp.dto.User.RegistrationDto;
import kz.kbtu.algoapp.dto.Common.SuccessMessage;
import kz.kbtu.algoapp.entity.User;
import kz.kbtu.algoapp.exception.AppException;
import kz.kbtu.algoapp.repository.UserRepository;
import kz.kbtu.algoapp.service.AuthService;
import kz.kbtu.algoapp.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public SuccessMessage registerUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());

        if(userRepository.findByEmail(registrationDto.getEmail()).isPresent()) throw new AppException("User with such email already exists");

        user.setEmail(registrationDto.getEmail());

        String password = registrationDto.getPassword();
        String passwordConfirm = registrationDto.getPasswordConfirm();

        if (!password.equals(passwordConfirm)) throw new AppException("Passwords are not match");
        user.setPassword(passwordEncoder.encode(password));

        if (registrationDto.getRoles() == null) registrationDto.setRoles("USER");
        user.setRoles("ROLE" + "_" + registrationDto.getRoles());

        userRepository.save(user);
        return new SuccessMessage("User is registered");
    }

    @Override
    public JwtResponse loginUser(LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        return new JwtResponse(jwtService.generateToken(loginDto.getEmail()));
    }

    @Override
    public SuccessMessage grandAdminRole(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("No user with such an email"));
        List<String> roles = new ArrayList<>(Arrays.asList(user.getRoles().split(",")));
        if (roles.contains("ROLE_ADMIN")) throw new AppException("user already has admin privelege");
        String updatedRoles = user.getRoles() + ",ROLE_ADMIN";
        user.setRoles(updatedRoles);
        userRepository.save(user);
        return new SuccessMessage("Admin role granted");
    }

    @Override
    public User getUser(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new AppException("no user with such an email"));
    }


}
