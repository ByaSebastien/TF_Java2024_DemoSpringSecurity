package be.bstorm.tf_java2024_demospringsecurity.api.dtos;

import be.bstorm.tf_java2024_demospringsecurity.domain.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserTokenDTO {

    private Long id;
    private String username;
    private String token;



    public static UserTokenDTO fromEntity(User u){

        return UserTokenDTO.builder()
                .id(u.getId())
                .username(u.getUsername())
                .build();
    }
}
