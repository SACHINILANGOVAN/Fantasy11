package com.nseit.Fantasy11.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;
    private String eventName;

    @ManyToMany
    @JoinTable(name = "event_match", joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "eventId"),
            inverseJoinColumns = @JoinColumn(name = "match_id", referencedColumnName = "matchId"))
    private Set<Match> matches;

    @OneToMany(mappedBy = "events", cascade = CascadeType.ALL)
    private Set<SelectedPlayer> selectedPlayers;


}
