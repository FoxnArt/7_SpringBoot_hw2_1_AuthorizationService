package controller;

import exeption.InvalidCredentials;
import exeption.UnauthorizedUser;
import model.Authorities;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

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
