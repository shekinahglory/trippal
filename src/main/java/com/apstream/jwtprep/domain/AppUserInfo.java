package com.apstream.jwtprep.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserInfo extends AppUser {


    private String username;
    private String email;
    private String imageUrl;
    private String gender;
    private String state;
    private String city;
    private Date birthDate;
    private String drinker;
    private String smoker;
    private String about;
    private String interest;
    private String haveKids;
    private String wantKids;
    private String job;
    private int income;
    private String education;
}
