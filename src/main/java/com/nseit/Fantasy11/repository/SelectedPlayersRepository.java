package com.nseit.Fantasy11.repository;

import com.nseit.Fantasy11.model.Player;
import com.nseit.Fantasy11.model.SelectedPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedPlayersRepository extends JpaRepository<SelectedPlayer, Integer> {

}