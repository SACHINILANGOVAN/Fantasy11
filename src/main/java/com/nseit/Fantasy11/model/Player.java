package com.nseit.Fantasy11.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer playerId;
    private String playerName;

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private File file;

    @ManyToMany
    @JoinTable(name = "player_match",joinColumns = @JoinColumn(name = "player_Id",referencedColumnName = "playerId"),
            inverseJoinColumns = @JoinColumn(name = "match_Id",referencedColumnName = "matchId"))
    private Set<Match> matches;

    @OneToMany(mappedBy = "players",cascade = CascadeType.ALL)
    private Set<SelectedPlayer> selectedPlayers;

}
