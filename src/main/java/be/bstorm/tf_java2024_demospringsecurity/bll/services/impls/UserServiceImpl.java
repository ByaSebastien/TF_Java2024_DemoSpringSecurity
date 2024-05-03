package be.bstorm.tf_java2024_demospringsecurity.bll.services.impls;

import be.bstorm.tf_java2024_demospringsecurity.bll.services.UserService;
import be.bstorm.tf_java2024_demospringsecurity.dal.repositories.UserRepository;
import be.bstorm.tf_java2024_demospringsecurity.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username).orElseThrow(() ->
             new UsernameNotFoundException("User with name " + username + " not found")
        );
    }

    @Override
    public User login(User u) {
        User existingUser = userRepository.getUserByUsername(u.getUsername()).orElseThrow();
        if(!passwordEncoder.matches(u.getPassword(), existingUser.getPassword())){
            throw new RuntimeException("Wrong password");
        }
        return existingUser;
    }
}
