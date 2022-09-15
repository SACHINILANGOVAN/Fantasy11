package com.nseit.Fantasy11.repository;

import com.nseit.Fantasy11.model.FantasyUser;
import com.nseit.Fantasy11.service.FantasyUserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FantasyUserRepository extends JpaRepository<FantasyUser, Integer> {
    FantasyUser findByUserName(String name);

}
