package com.apstream.jwtprep.repository;

import com.apstream.jwtprep.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<AppUser, Long> {


    AppUser findByEmail(String email);
    AppUser findByUsername(String username);


    List<AppUser> findAllByState(String state);
}
