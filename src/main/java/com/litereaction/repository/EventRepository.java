package com.litereaction.repository;

import com.litereaction.model.Event;
import com.litereaction.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
