package lt.springinaction.tacocloud.security;

import lt.springinaction.tacocloud.repository.UserRepository;
import lt.springinaction.tacocloud.tacos.UserAccount;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.logging.Logger;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    Logger logger = Logger.getLogger(SecurityConfig.class.getName());

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo){
        return username -> {
            UserAccount user = userRepo.findByUsername(username);
            if (user!=null) return user;

            throw new UsernameNotFoundException("User " + username + " not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        logger.info("FilterChain");
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/orders", "/design").hasRole("USER")
                        .requestMatchers("/", "/**").permitAll())
                .httpBasic(Customizer.withDefaults())
                .formLogin().loginPage("/login")

                .loginProcessingUrl("/register/process_login")
                .defaultSuccessUrl("/design", true)
                .usernameParameter("user")
                .passwordParameter("pwd")
                .failureUrl("/login?error")
                .permitAll();
        httpSecurity.oauth2Login((login) -> login.loginPage("/login"));
        httpSecurity.logout((logout) -> logout.logoutSuccessUrl("/").permitAll());
        return httpSecurity.build();
    }
}
