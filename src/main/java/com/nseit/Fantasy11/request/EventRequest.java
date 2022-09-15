package com.nseit.Fantasy11.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRequest {
    private Integer eventId;
    private Integer matchId;
    private String eventName;
}