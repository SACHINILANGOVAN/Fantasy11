package com.nseit.Fantasy11.controller;

import com.nseit.Fantasy11.model.Event;
import com.nseit.Fantasy11.request.EventRequest;
import com.nseit.Fantasy11.response.APIResponse;
import com.nseit.Fantasy11.response.SuccessResponse;
import com.nseit.Fantasy11.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:3001/"})

@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private APIResponse apiResponse;

    //    @Secured({Role.ROLE_ADMIN})
    @PostMapping
    public ResponseEntity<APIResponse> addDish(@RequestBody EventRequest eventRequest) {
        Event addedEvent = eventService.addEvent(eventRequest);
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(addedEvent);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    //    @Secured({Role.ROLE_ADMIN})
    @PutMapping
    public ResponseEntity<APIResponse> updateEvent(@RequestBody EventRequest eventRequest) {
        Event updatedEvent = eventService.updateEvent(eventRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(updatedEvent);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    //    @Secured({Role.ROLE_ADMIN})
    @DeleteMapping("/{eventId}")
    public ResponseEntity<APIResponse> deleteEvent(@PathVariable Integer eventId) {
        eventService.deleteEvent(eventId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(new SuccessResponse());
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    //    @Secured({Role.ROLE_ADMIN, Role.ROLE_USER})
    @GetMapping("/all")
    public ResponseEntity<APIResponse> viewAllEvent() {
        List<Event> events = eventService.viewAllEvent();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(events);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


}