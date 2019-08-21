package bd.edu.seu.frontendnavigation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginToken {
    private String username;
    private String fullname;
    private String role;
}
