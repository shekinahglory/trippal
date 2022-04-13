package com.apstream.jwtprep.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connections")
public class ConnectionController {




    @GetMapping("/main")
    public String getConnection(){
         return "connected";
    }

    @GetMapping("/user")
    public String roleBased(){
        return "verified";
    }
}
