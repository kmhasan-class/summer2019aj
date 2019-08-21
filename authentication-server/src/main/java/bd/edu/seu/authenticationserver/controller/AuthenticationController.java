package bd.edu.seu.authenticationserver.controller;

import bd.edu.seu.authenticationserver.model.LoginToken;
import bd.edu.seu.authenticationserver.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("")
    @ResponseBody
    public LoginToken authenticate(@RequestParam String username, @RequestParam String password) {
        return authenticationService.authenticate(username, password);
    }
}
