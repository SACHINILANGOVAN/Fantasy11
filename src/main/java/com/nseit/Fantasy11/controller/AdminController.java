package com.nseit.Fantasy11.controller;

import com.nseit.Fantasy11.model.Event;
import com.nseit.Fantasy11.model.Match;
import com.nseit.Fantasy11.response.APIResponse;
import com.nseit.Fantasy11.service.EventService;
import com.nseit.Fantasy11.service.MatchService;
import org.apache.catalina.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/match")
    public ResponseEntity<APIResponse> addCategory(@RequestBody Match match) {
        APIResponse apiResponse = new APIResponse();
        Match cat = matchService.addMatch(match);
        if (cat == null) {
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(cat);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
