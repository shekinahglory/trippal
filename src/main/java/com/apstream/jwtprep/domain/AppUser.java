package com.apstream.jwtprep.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AppUser  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;
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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value= FetchMode.SELECT)
    @JsonManagedReference
    private Set<ImageUrls> imagesUrls;


//
//    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, mappedBy = "imageId")
//    private List<ImageUrls> urlImages;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    @JsonIgnore
    public Collection<Role> getRoles() {
        return roles;
    }

    @JsonProperty
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public AppUser(String username, String password) {




    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @JsonProperty
    public void setId(Long id) {
        this.id = id;
    }
}
