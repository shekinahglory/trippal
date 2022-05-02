package com.apstream.jwtprep.services;

import com.apstream.jwtprep.domain.AppUser;
import com.apstream.jwtprep.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {



    private final UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        AppUser user = userRepo.findByEmail(username);
        if (user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not fond in the database");
        } else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
