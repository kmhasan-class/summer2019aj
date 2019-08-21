package bd.edu.seu.frontendnavigation.service;

import bd.edu.seu.frontendnavigation.model.LoginToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTest {
    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void authenticate_kmh_abcd1234() {
        LoginToken loginToken = authenticationService.authenticate("kmh", "abcd1234");
        assertEquals("kmh", loginToken.getUsername());
        assertEquals("Monirul Hasan", loginToken.getFullname());
        assertEquals("faculty", loginToken.getRole());
    }

    @Test
    public void authenticate_2012000000089_xyz123() {
        LoginToken loginToken = authenticationService.authenticate("2012000000089", "xyz123");
        assertEquals("2012000000089", loginToken.getUsername());
        assertEquals("Md. Masum", loginToken.getFullname());
        assertEquals("student", loginToken.getRole());
    }

    @Test
    public void authenticate_incorrect() {
        LoginToken loginToken = authenticationService.authenticate("kmh", "abcd1234xx");
        assertNull(loginToken.getUsername());
        assertNull(loginToken.getFullname());
        assertEquals("norole", loginToken.getRole());
    }
}
