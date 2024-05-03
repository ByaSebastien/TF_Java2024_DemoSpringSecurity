package be.bstorm.tf_java2024_demospringsecurity.il.utils;

import be.bstorm.tf_java2024_demospringsecurity.dal.repositories.UserRepository;
import be.bstorm.tf_java2024_demospringsecurity.domain.entities.User;
import be.bstorm.tf_java2024_demospringsecurity.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User bambino = User.builder()
                    .username("Bambino")
                    .password(passwordEncoder.encode("Test1234="))
                    .role(UserRole.ADMIN)
                    .build();
            User roger = User.builder()
                    .username("Roger")
                    .password(passwordEncoder.encode("Test1234="))
                    .role(UserRole.USER)
                    .build();

            userRepository.save(bambino);
            userRepository.save(roger);
        }
    }
}
