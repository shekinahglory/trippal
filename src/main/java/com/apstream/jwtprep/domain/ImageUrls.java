package com.apstream.jwtprep.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImageUrls {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DATA")
    @SequenceGenerator(sequenceName = "my_seq", allocationSize = 1, name = "SEQ_DATA")
    private Long imageId;

    private String url;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUser owner;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    private AppUser appUser;



}
