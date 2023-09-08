package ru.netology._7_springboot_hw2_1_authorizationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.netology._7_springboot_hw2_1_authorizationservice.exeption.InvalidCredentials;
import ru.netology._7_springboot_hw2_1_authorizationservice.exeption.UnauthorizedUser;
import ru.netology._7_springboot_hw2_1_authorizationservice.model.Authorities;
import ru.netology._7_springboot_hw2_1_authorizationservice.service.AuthorizationService;
import java.util.List;


@RestController
public class AuthorizationController {
    private final AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler(InvalidCredentials.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String resolveValidationException(InvalidCredentials e) {
        return e.getMessage();
    }

    @ExceptionHandler(UnauthorizedUser.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String resolveValidationException(UnauthorizedUser e) {
        return e.getMessage();
    }
}
