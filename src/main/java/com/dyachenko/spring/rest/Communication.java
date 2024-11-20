package com.dyachenko.spring.rest;

import com.dyachenko.spring.rest.models.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {
    private final RestTemplate restTemplate;
    private final String url = "http://94.198.50.185:7081/api/users";

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public HttpHeaders getHeaders() {
        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET, null
                        , new ParameterizedTypeReference<String>() {
                        });
        String cookie = response.getHeaders().get("Set-Cookie").stream().collect(Collectors.joining());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        return headers;
    }

    public User getUserByID(HttpHeaders headers, Long id) {
        HttpEntity httpEntity = new HttpEntity<>(headers);
        ResponseEntity<List<User>> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity
                , new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = response.getBody();
        User userByID = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);

        return userByID;
    }

    public String saveUser(HttpHeaders headers) {
        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 32);
        HttpEntity httpEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        return response.getBody();
    }

    public String updateUser(HttpHeaders headers, User user) {
        user.setName("Thomas");
        user.setLastName("Shelby");
        HttpEntity httpEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
        return response.getBody();
    }

    public String deleteUser(HttpHeaders headers, User user) {
        HttpEntity httpEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(url + "/" + user.getId(), HttpMethod.DELETE, httpEntity, String.class);
        return response.getBody();
    }
}
