package com.apstream.jwtprep.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dashboard")
public class DashboardController {






    public String afterAccountXCreated(){
        return "token";
    }

    public String updateProfileImages(){
        return "image updated";
    }
}
