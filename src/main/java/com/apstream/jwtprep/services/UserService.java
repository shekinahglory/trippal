package com.apstream.jwtprep.services;

import com.apstream.jwtprep.domain.AppUser;
import com.apstream.jwtprep.domain.AppUserInfo;
import com.apstream.jwtprep.domain.ImageUrls;
import com.apstream.jwtprep.domain.Role;

import java.util.List;
import java.util.Set;

public interface UserService {

    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser (String username);
    List<AppUser> getUsers();
    String checkUserByEmail(String email);
    String checkUserByUsername(String username);
    String saveImage(ImageUrls imageUrl);

    Set<ImageUrls> getUserImages(String username);

    List<AppUser> getAllUsersInState(String state);

    AppUserInfo getUserInfo();


}
