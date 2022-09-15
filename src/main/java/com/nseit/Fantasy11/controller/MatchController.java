package com.nseit.Fantasy11.controller;

import com.nseit.Fantasy11.exception.UnableToInsertException;
import com.nseit.Fantasy11.exception.UnableToUpdateException;
import com.nseit.Fantasy11.model.Match;
import com.nseit.Fantasy11.response.APIResponse;
import com.nseit.Fantasy11.response.SuccessResponse;
import com.nseit.Fantasy11.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController

@CrossOrigin(value = {"http://localhost:3001/"})

@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private APIResponse apiResponse;

    //    @Secured({Role.ROLE_ADMIN})
    @PostMapping
    public ResponseEntity<APIResponse> addMatch(@RequestBody Match match) {
        Match addedMatch = matchService.addMatch(match);
        if (addedMatch == null) {
            throw new UnableToInsertException("Unable to insert Match");
        }
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(addedMatch);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    //    @Secured({Role.ROLE_ADMIN})
    @PutMapping
    public ResponseEntity<APIResponse> updateMatch(@RequestBody Match match) {
        Match updatedMatch = matchService.updateMatch(match);
        if (updatedMatch == null) {
            throw new UnableToUpdateException("Unable to update Match");
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(updatedMatch);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    //
//    @Secured({Role.ROLE_ADMIN})
    @DeleteMapping("/{matchId}")
    public ResponseEntity<APIResponse> deleteMatch(@PathVariable Integer matchId) {
        matchService.deleteMatch(matchId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(new SuccessResponse());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //    @Secured({Role.ROLE_ADMIN, Role.ROLE_USER})
    @GetMapping("/all")
    public ResponseEntity<APIResponse> viewAllMatch() {
        List<Match> matches = matchService.viewAllMatches();
        if (matches == null) {
            throw new ResourceNotFoundException("Unable to view Matches");
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(matches);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}



