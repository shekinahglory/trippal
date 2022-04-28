package com.apstream.jwtprep.api;


import com.apstream.jwtprep.domain.AppUser;
import com.apstream.jwtprep.domain.AppUserInfo;
import com.apstream.jwtprep.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/dashboard")
public class DashboardController {


    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/token")
    public String afterAccountXCreated(){
        return
                "token";
    }


    @GetMapping("userinfo")
    public AppUser getUserInfo(@RequestParam String username){
        AppUser loggedInUser = userService.getUser(username);
        return loggedInUser;
    }

    @GetMapping("connection/states")
    public List<AppUser> getAllUsersByState(@RequestParam String state){
        List<AppUser> result = userService.getAllUsersInState(state);
        return result;
    }

    public void getUserImages(){

    }

    public String mainDashBoard(){
       return "main value";
    }

    public String updateProfileImages(){
	    String codeFromWindow = "testing";
        return "image updated";
    }
}

