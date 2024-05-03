package be.bstorm.tf_java2024_demospringsecurity.api.controllers.security;

import be.bstorm.tf_java2024_demospringsecurity.api.controllers.forms.LoginForm;
import be.bstorm.tf_java2024_demospringsecurity.api.dtos.UserTokenDTO;
import be.bstorm.tf_java2024_demospringsecurity.bll.services.UserService;
import be.bstorm.tf_java2024_demospringsecurity.domain.entities.User;
import be.bstorm.tf_java2024_demospringsecurity.il.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public ResponseEntity<UserTokenDTO> login(@RequestBody LoginForm form){

        User user = userService.login(form.toEntity());
        UserTokenDTO dto = UserTokenDTO.fromEntity(user);
        String token = jwtUtils.generateToken(user);
        dto.setToken(token);
        return ResponseEntity.ok(dto);
    }
}
