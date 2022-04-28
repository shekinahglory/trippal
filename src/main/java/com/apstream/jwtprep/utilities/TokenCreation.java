package com.apstream.jwtprep.utilities;

import com.apstream.jwtprep.domain.AppUser;
import com.apstream.jwtprep.domain.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;



@Service
public class TokenCreation {




    public String createToken(AppUser user){

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() +10 * 60 * 1000))
                .withIssuer("localhost:8080/api/signup/user/save")
                .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .sign(algorithm);
        Map<String, String> tokens = new HashMap<>();

        tokens.put("access_token", access_token);
//        tokens.put("refresh_token", refresh_token);
        return access_token;
    }
}
