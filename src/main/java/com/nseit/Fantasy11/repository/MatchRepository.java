package com.nseit.Fantasy11.repository;

import com.nseit.Fantasy11.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {
    Optional<Match> findByMatchName(String matchName);

}
