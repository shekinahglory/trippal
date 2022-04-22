package com.apstream.jwtprep.api;


import com.apstream.jwtprep.configuration.aws.FileStorage;
import com.apstream.jwtprep.domain.AppUser;
import com.apstream.jwtprep.domain.ImageUrls;
import com.apstream.jwtprep.domain.Role;
import com.apstream.jwtprep.domain.UserReceived;
import com.apstream.jwtprep.services.UserService;
import com.apstream.jwtprep.utilities.TokenCreation;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/signup")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class MainController {


    private final UserService userService;
    private final FileStorage awsImageService;
    private final TokenCreation tokenCreation;

    public MainController(UserService userService, FileStorage awsImageService, TokenCreation tokenCreation) {
        this.userService = userService;
        this.awsImageService = awsImageService;
        this.tokenCreation = tokenCreation;
    }



    @PostMapping("/user/save/images")
    public String saveImages(@RequestParam MultipartFile file) throws IOException {
       String res = awsImageService.upLoadFile(file);
       AppUser user = userService.getUser(file.getOriginalFilename());
       if(user != null && res != null){
           ImageUrls image = new ImageUrls();
           image.setOwner(user);
           image.setUrl(res);
            if(user.getImageUrl() == null){
                user.setImageUrl(res);
            }
           userService.saveImage(image);
           log.info("rec {}", file.getOriginalFilename());
           return tokenCreation.createToken(user);
       } else {
           return "error occurred" + user + " " + res;
       }



    }


    @PostMapping("/user/save")
    public String saveUser(@RequestBody AppUser userReceived) throws IOException {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
//        return ResponseEntity.created(uri).body(tokenCreation.createToken(userService.saveUser(userReceived)));
        AppUser user = new AppUser();
        if(userReceived != null && userReceived.getUsername() != null){
            user = userService.saveUser(userReceived);
        }
       if (user.getUsername() != null){
           return user.getUsername();
       }else{
           return  "failed creation";
       }

    }

    @PostMapping("/user/checkUsernameExist")
    public String checkUsername(@RequestParam String username){
        return userService.checkUserByUsername(username);
    }

    @PostMapping("/user/checkEmailExist")
    public String checkEmail(@RequestParam String email){
        return userService.checkUserByEmail(email);
    }


    @PostMapping("/role/save")
    public ResponseEntity<Role> saveROle(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm){
        userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUser(){
        return ResponseEntity.ok().body(userService.getUsers());
    }


    @GetMapping("/token/refresh")
    public void refreshToken (HttpServletRequest request, HttpServletResponse response) throws IOException {

        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try {

                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AppUser user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() +10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
//        response.setHeader("access_token", access_token);
//        response.setHeader("refresh_token", refresh_token);

                Map<String, String> tokens = new HashMap<>();

                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception e){

                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error  = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), error);

            }
        }
    }


}

@Data
class RoleToUserForm{

    private String username;
    private String roleName;
}
