package ru.netology._7_springboot_hw2_1_authorizationservice.service;


import org.springframework.stereotype.Service;
import ru.netology._7_springboot_hw2_1_authorizationservice.exeption.InvalidCredentials;
import ru.netology._7_springboot_hw2_1_authorizationservice.exeption.UnauthorizedUser;
import ru.netology._7_springboot_hw2_1_authorizationservice.model.Authorities;
import ru.netology._7_springboot_hw2_1_authorizationservice.repository.UserRepository;

import java.util.List;

@Service
public class AuthorizationService {
    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
