package ru.netology._7_springboot_hw2_1_authorizationservice.repository;


import org.springframework.stereotype.Repository;
import ru.netology._7_springboot_hw2_1_authorizationservice.model.Authorities;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private final ConcurrentHashMap<String, List<Authorities>> userAuthorities;
    private final ConcurrentHashMap<String, String> userCredentials;


    public UserRepository() {
        this.userAuthorities = new ConcurrentHashMap<>();

        this.userAuthorities.put("Ivanov", List.of(Authorities.values()));
        this.userAuthorities.put("Petrov", List.of(Authorities.READ));

        this.userCredentials = new ConcurrentHashMap<>();

        this.userCredentials.put("Ivanov", "12345");
        this.userCredentials.put("Petrov", "67890");

    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        if (userAuthorities.containsKey(user) && userCredentials.get(user).equals(password))  {
            return userAuthorities.getOrDefault(user, Collections.emptyList());
        }
        return Collections.emptyList();
    }
}

