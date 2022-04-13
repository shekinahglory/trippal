package com.apstream.jwtprep.domain;


import lombok.Data;

import javax.persistence.*;


public class ImageUrls {

    @Id
    private Long imageId;

    private String url;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private AppUser appUser;



}
