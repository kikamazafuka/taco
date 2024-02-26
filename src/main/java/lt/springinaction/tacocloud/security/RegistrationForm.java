package lt.springinaction.tacocloud.security;

import lombok.Data;
import lt.springinaction.tacocloud.tacos.UserAccount;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    public UserAccount toUser(PasswordEncoder passwordEncoder) {
        return new UserAccount(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone);
    }

}
