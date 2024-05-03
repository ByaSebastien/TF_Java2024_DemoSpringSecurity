package be.bstorm.tf_java2024_demospringsecurity.api.controllers.forms;

import be.bstorm.tf_java2024_demospringsecurity.domain.entities.User;

public record LoginForm(
        String username,
        String password
) {
    public User toEntity(){
        return User.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
