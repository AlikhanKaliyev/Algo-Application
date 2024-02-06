package kz.kbtu.algoapp.service.impl;

import kz.kbtu.algoapp.config.security.AppUserDetails;
import kz.kbtu.algoapp.entity.User;
import kz.kbtu.algoapp.exception.AppException;
import kz.kbtu.algoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userInfo = userRepository.findByEmail(email);
        return userInfo.map(AppUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user is not found"));

    }
}
