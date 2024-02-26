package lt.springinaction.tacocloud.controller;

import lt.springinaction.tacocloud.repository.UserRepository;
import lt.springinaction.tacocloud.security.RegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    Logger logger = Logger.getLogger(RegistrationController.class.getName());

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        logger.info("RegistrController: Get registration form");
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form){
        logger.info("RegistrController: Process registration");
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/design";
    }

    @PostMapping("/process_login")
    public String processLogin(){
        logger.info("RegistrController: Process login");
//        userRepository.save(form.toUser(passwordEncoder));
        return "/design";
    }
}
