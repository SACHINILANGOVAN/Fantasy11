package com.nseit.Fantasy11.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerRequest {
    private Integer playerId;
    private Integer eventId;
    private String playerName;
}
