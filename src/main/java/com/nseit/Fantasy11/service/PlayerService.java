package com.nseit.Fantasy11.service;

import com.nseit.Fantasy11.controller.ResourceNotFoundException;
import com.nseit.Fantasy11.exception.ResourceAlreadyExistException;
import com.nseit.Fantasy11.model.Event;
import com.nseit.Fantasy11.model.Player;
import com.nseit.Fantasy11.repository.EventRepository;
import com.nseit.Fantasy11.repository.FileRepository;
import com.nseit.Fantasy11.repository.PlayerRepository;
import com.nseit.Fantasy11.request.PlayerRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private FileRepository fileRepository;

    public Player addPlayer(PlayerRequest playerRequest) {
        Player player = new Player();
        BeanUtils.copyProperties(playerRequest, player);

        Event event = eventRepository.findById(playerRequest.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Player with id "
                        + playerRequest.getEventId()));
//        player.setEvent(event);

        boolean isMatchExists = eventRepository.findByEventName(playerRequest.getPlayerName()).isPresent();
        if (isMatchExists)
            throw new ResourceAlreadyExistException("Player already exists.");

        return playerRepository.save(player);
    }

    public Player updatePlayer(PlayerRequest playerRequest) {
        Player player = new Player();
        BeanUtils.copyProperties(playerRequest, player);
        if (playerRequest.getPlayerId() == null)
            throw new ResourceNotFoundException("No player with id "
                    + playerRequest.getPlayerName());
        Event event = eventRepository.findById(playerRequest.getPlayerId())
                .orElseThrow(() -> new ResourceNotFoundException("No Event with Event id "
                        + playerRequest.getPlayerName()));
//        player.setEvent(event);
        return playerRepository.save(player);
    }

    public void deletePlayer(Integer playerId) {
        playerRepository.findById(playerId).orElseThrow(() ->
                new ResourceNotFoundException("No player with id "
                        + playerId));
        playerRepository.deleteById(playerId);
    }


    public Player findPlayerById(Integer playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new ResourceNotFoundException("Unable to find event with id " + playerId));
    }

    public List<Player> viewAllPlayer() {
        return playerRepository.findAll();
    }
}
