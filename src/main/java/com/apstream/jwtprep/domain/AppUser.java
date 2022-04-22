package com.apstream.jwtprep.domain;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String email;
    private String password;
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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value= FetchMode.SELECT)
    private Set<ImageUrls> imagesUrls;


//
//    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, mappedBy = "imageId")
//    private List<ImageUrls> urlImages;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();


    public AppUser(String username, String password) {

        this.username = username;
        this.password = password;


    }
}
