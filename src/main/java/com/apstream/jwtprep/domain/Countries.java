package com.apstream.jwtprep.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Countries {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    private String shortname;

    private String name;

    private int phonecode;


}
