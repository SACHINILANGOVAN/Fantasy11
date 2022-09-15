package com.nseit.Fantasy11.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "selectedPlayers")

public class SelectedPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer selectedPlayerId;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private FantasyUser fantasyUsers;

    @ManyToOne
    @JoinColumn(name = "match_id", referencedColumnName = "matchId")
    private Match matches;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "playerId")
    private Player players;

    @ManyToOne
    @JoinColumn(name = "events_id", referencedColumnName = "eventId")
    private Event events;


}
