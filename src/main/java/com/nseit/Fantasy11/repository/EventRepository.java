package com.nseit.Fantasy11.repository;

import com.nseit.Fantasy11.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    Optional<Event> findByEventName(String eventName);
}

