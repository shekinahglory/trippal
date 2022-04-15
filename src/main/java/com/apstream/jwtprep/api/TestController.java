package com.apstream.jwtprep.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {


    @GetMapping("/main")
    public String testmain(){
        return "perfect test";
    }
}
