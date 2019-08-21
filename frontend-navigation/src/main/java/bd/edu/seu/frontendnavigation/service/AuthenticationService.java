package bd.edu.seu.frontendnavigation.service;

import bd.edu.seu.frontendnavigation.model.LoginToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {
    @Value("${authenticateion.base-url}/auth")
    private String authUrl;

    public LoginToken authenticate(String username, String password) {
        // TODO use REST template to communicate the backend
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("username", username);
        multiValueMap.add("password", password);
        LoginToken loginToken = restTemplate.postForObject(authUrl, multiValueMap, LoginToken.class);

        return loginToken;
    }
}
