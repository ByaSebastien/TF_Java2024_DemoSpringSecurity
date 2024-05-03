package be.bstorm.tf_java2024_demospringsecurity.il.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private byte[] secret = "Yabadabadooooooooooooooooooooooooooo".getBytes(StandardCharsets.UTF_8);

    public SecretKey getSecretKey(){
        return new SecretKeySpec(secret,"HmacSHA256");
    }

    public int getExpireAt(){
        return 86400; // 1Day
    }
}
