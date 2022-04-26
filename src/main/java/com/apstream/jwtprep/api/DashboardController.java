package com.apstream.jwtprep.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dashboard")
public class DashboardController {





   @RequestMapping("/token")
    public String afterAccountXCreated(){
        return
                "token";
    }


    public void getUserInfo(){

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

