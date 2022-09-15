package com.nseit.Fantasy11.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "matches")

public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matchId;
    private String matchName;

    @ManyToMany(mappedBy = "matches")
    private Set<Event> event;

    @OneToMany(mappedBy = "matches", cascade = CascadeType.ALL)
    private Set<SelectedPlayer> selectedPlayers;


}
