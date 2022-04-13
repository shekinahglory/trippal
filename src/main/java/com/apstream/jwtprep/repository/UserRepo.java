package com.apstream.jwtprep.repository;

import com.apstream.jwtprep.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Long> {


    AppUser findByEmail(String email);
    AppUser findByUsername(String username);
}
