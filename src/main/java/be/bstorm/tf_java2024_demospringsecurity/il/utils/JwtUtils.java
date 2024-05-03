package be.bstorm.tf_java2024_demospringsecurity.il.utils;

import be.bstorm.tf_java2024_demospringsecurity.domain.entities.User;
import be.bstorm.tf_java2024_demospringsecurity.il.configs.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private final JwtConfig config;

    private final JwtBuilder builder;

    private final JwtParser parser;

    public JwtUtils(JwtConfig config) {
        this.config = config;
        this.builder = Jwts.builder().signWith(config.getSecretKey());
        this.parser = Jwts.parserBuilder().setSigningKey(config.getSecretKey()).build();
    }

    public String generateToken(User u){
        return builder
                .setSubject(u.getUsername())
                .claim("id",u.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + config.getExpireAt() * 1000L))
                .compact();
    }

    public Claims getClaims(String token){
        return parser.parseClaimsJws(token).getBody();
    }

    public Long getId(String token){
        return getClaims(token).get("id", Long.class);
    }

    public String getUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean isValid(String token){
        Claims claims = getClaims(token);
        Date now = new Date();
        return now.after(claims.getIssuedAt()) && now.before(claims.getExpiration());
    }
}
