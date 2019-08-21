package bd.edu.seu.authenticationserver.service;

import bd.edu.seu.authenticationserver.model.LoginToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    private Map<String, String> dummyAuthDatabase;
    private Map<String, LoginToken> usernameToLoginTokenMap;

    public AuthenticationService() {
        dummyAuthDatabase = new HashMap<>();
        usernameToLoginTokenMap = new HashMap<>();

        dummyAuthDatabase.put("kmh", "abcd1234");
        dummyAuthDatabase.put("2012000000089", "xyz123");

        usernameToLoginTokenMap.put("kmh",
                new LoginToken("kmh", "Monirul Hasan", "faculty"));

        usernameToLoginTokenMap.put("2012000000089",
                new LoginToken("2012000000089", "Md. Masum", "student"));
    }

    public LoginToken authenticate(String username, String password) {
        String dbpassword = dummyAuthDatabase.get(username);

        if (dbpassword == null || !dbpassword.equals(password))
            return new LoginToken(null, null, "norole");

        return usernameToLoginTokenMap.getOrDefault(username,
                new LoginToken(null, null, "norole"));
    }
}
