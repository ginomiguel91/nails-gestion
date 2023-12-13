package com.sis.nailsgestion;

import com.sis.nailsgestion.config.RsaKeyConfigProperties;
import com.sis.nailsgestion.user.User;
import com.sis.nailsgestion.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableConfigurationProperties(RsaKeyConfigProperties.class)
@SpringBootApplication
public class NailsgestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(NailsgestionApplication.class, args);
    }
    @Bean
    public CommandLineRunner initializeUser(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return args -> {

            User user = new User();
            user.setUsername("exampleuser");
            user.setEmail("example@gmail.com");
            user.setPassword(passwordEncoder.encode("examplepassword"));

            // Save the user to the database
            userRepository.save(user);

        };
    }
}
