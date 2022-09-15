package com.nseit.Fantasy11.service;

import com.nseit.Fantasy11.controller.ResourceNotFoundException;
import com.nseit.Fantasy11.exception.ResourceAlreadyExistException;
import com.nseit.Fantasy11.model.Match;
import com.nseit.Fantasy11.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MatchService {
    @Autowired
    public MatchRepository matchRepository;

    public Match addMatch(Match match) {
        boolean isExists = matchRepository.findByMatchName(match.getMatchName()).isPresent();
        if (isExists)
            throw new ResourceAlreadyExistException("Match already exists.");
        return matchRepository.save(match);
    }
    public List<Match> viewAllMatches() {
        return matchRepository.findAll();
    }


    public void deleteMatch(Integer matchId) {
        matchRepository.findById(matchId).orElseThrow(()
                -> new ResourceNotFoundException("Invalid match id :" + matchId));
        matchRepository.deleteById(matchId);
    }

    public  Match updateMatch(Match match) {
        if (match.getMatchId() != null && match.getMatchId() > 0) {
            matchRepository.findById(match.getMatchId()).orElseThrow(() ->
                    new ResourceNotFoundException("No match with id:" + match.getMatchId()));

            return matchRepository.save(match);
        } else {
            throw new ResourceNotFoundException("Invalid MatchId");
        }

    }


}