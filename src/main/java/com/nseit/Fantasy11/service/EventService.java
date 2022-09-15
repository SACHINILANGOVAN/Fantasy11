package com.nseit.Fantasy11.service;

import com.nseit.Fantasy11.controller.ResourceNotFoundException;
import com.nseit.Fantasy11.exception.ResourceAlreadyExistException;
import com.nseit.Fantasy11.model.Event;
import com.nseit.Fantasy11.model.Match;
import com.nseit.Fantasy11.repository.EventRepository;
import com.nseit.Fantasy11.repository.MatchRepository;
import com.nseit.Fantasy11.request.EventRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private MatchRepository matchRepository;

    public Event addEvent(EventRequest eventRequest) {
        Event event = new Event();
        BeanUtils.copyProperties(eventRequest, event);

        Match match = matchRepository.findById(eventRequest.getMatchId())
                .orElseThrow(() -> new ResourceNotFoundException("Event with id "
                        + eventRequest.getEventId()));
//        event.setMatch(match);

        boolean isMatchExists = eventRepository.findByEventName(eventRequest.getEventName()).isPresent();
        if (isMatchExists)
            throw new ResourceAlreadyExistException("Match already exists.");

        return eventRepository.save(event);
    }

    public Event updateEvent(EventRequest eventRequest) {
        Event event = new Event();
        BeanUtils.copyProperties(eventRequest, event);
        if (eventRequest.getEventId() == null)
            throw new ResourceNotFoundException("No event with id "
                    + eventRequest.getEventName());
        Match match = matchRepository.findById(eventRequest.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("No Match with Match id "
                        + eventRequest.getEventName()));
//        event.setMatch(match);
        return eventRepository.save(event);
    }

    public void deleteEvent(Integer eventId) {
        eventRepository.findById(eventId).orElseThrow(() ->
                new ResourceNotFoundException("No event with id "
                        + eventId));
        eventRepository.deleteById(eventId);
    }


    public Event findEventById(Integer eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find event with id " + eventId));
    }

    public List<Event> viewAllEvent() {
        return eventRepository.findAll();
    }
}
