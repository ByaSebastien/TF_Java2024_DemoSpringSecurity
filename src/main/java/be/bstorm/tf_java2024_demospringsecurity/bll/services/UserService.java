package be.bstorm.tf_java2024_demospringsecurity.bll.services;

import be.bstorm.tf_java2024_demospringsecurity.domain.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User login(User u);
}
