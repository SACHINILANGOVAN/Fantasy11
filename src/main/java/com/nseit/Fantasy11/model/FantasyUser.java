package com.nseit.Fantasy11.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fantasyUsers")
public class FantasyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private String name;
    private String password;

    @OneToMany(mappedBy = "fantasyUsers", cascade = CascadeType.ALL)
    private Set<SelectedPlayer> selectedPlayers;

    @ManyToMany
    @JsonIgnore
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JoinTable(joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "roleId")})
    private Set<Role> roles;

    public FantasyUser(String name, String password) {
        this.name = name;
        this.password = password;
    }
}


