package com.apstream.jwtprep;

import com.apstream.jwtprep.domain.AppUser;
import com.apstream.jwtprep.domain.Role;
import com.apstream.jwtprep.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtprepApplication {



    private final PasswordEncoder passwordEncoder;

    public JwtprepApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(JwtprepApplication.class, args);
    }


    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
              userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

//            userService.saveUser(
//                    new AppUser(
//                            null,
//                            "Jonh",
//                            "jojo",
//                            passwordEncoder.encode("password"),
//                            new ArrayList<>()
//                    )
//
//            );
//            userService.saveUser(
//                    new AppUser(
//                            null,
//                            "Will West",
//                            "willo",
//                            passwordEncoder.encode("password"),
//                            new ArrayList<>()
//                    )
//
//            );
//            userService.saveUser(
//                    new AppUser(
//                            null,
//                            "Ted Cruz",
//                            "teddy",
//                            passwordEncoder.encode("password"),
//                            new ArrayList<>()
//                    )
//
//            );
//            userService.saveUser(
//                    new AppUser(
//                            null,
//                            "West Jones",
//                            "westjo",
//                            passwordEncoder.encode("password"),
//                            new ArrayList<>()
//                    )
//
//            );

//
//            userService.addRoleToUser("jojo", "ROLE_USER");
//            userService.addRoleToUser("willo", "ROLE_USER");
//            userService.addRoleToUser("teddy", "ROLE_MANAGER");
//            userService.addRoleToUser("westjo", "ROLE_ADMIN");
//            userService.addRoleToUser("westjo", "ROLE_SUPER_ADMIN");
//            userService.addRoleToUser("westjo", "ROLE_MANAGER");
        };
    }

}
