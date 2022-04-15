package com.apstream.jwtprep.services;

import com.apstream.jwtprep.domain.AppUser;
import com.apstream.jwtprep.domain.Role;

import java.util.List;

public interface UserService {

    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser (String username);
    List<AppUser> getUsers();
    String checkUserByEmail(String email);
    String checkUserByUsername(String username);


}