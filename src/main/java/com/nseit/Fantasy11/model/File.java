package com.nseit.Fantasy11.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue
    private Integer id;
    private String Location;

    @OneToOne(mappedBy = "file")
    private Player player;

}