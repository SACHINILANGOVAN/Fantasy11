package com.nseit.Fantasy11.controller;

import com.nseit.Fantasy11.model.Player;
import com.nseit.Fantasy11.request.PlayerRequest;
import com.nseit.Fantasy11.response.APIResponse;
import com.nseit.Fantasy11.response.SuccessResponse;
import com.nseit.Fantasy11.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = {"http://localhost:3001/"})

@RequestMapping("/api/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private APIResponse apiResponse;

    //    @Secured({Role.ROLE_ADMIN})
    @PostMapping
    public ResponseEntity<APIResponse> addDish(@RequestBody PlayerRequest playerRequest) {
        Player addedPlayer = playerService.addPlayer(playerRequest);
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(addedPlayer);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    //    @Secured({Role.ROLE_ADMIN})
    @PutMapping
    public ResponseEntity<APIResponse> updatePlayer(@RequestBody PlayerRequest playerRequest) {
        Player updatedPlayer = playerService.updatePlayer(playerRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(updatedPlayer);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    //    @Secured({Role.ROLE_ADMIN})
    @DeleteMapping("/{playerId}")
    public ResponseEntity<APIResponse> deletePlayer(@PathVariable Integer playerId) {
        playerService.deletePlayer(playerId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(new SuccessResponse());
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    //    @Secured({Role.ROLE_ADMIN, Role.ROLE_USER})
    @GetMapping("/all")
    public ResponseEntity<APIResponse> viewAllPlayer() {
        List<Player> players = playerService.viewAllPlayer();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(players);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
