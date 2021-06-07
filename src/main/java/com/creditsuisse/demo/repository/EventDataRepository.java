package com.creditsuisse.demo.repository;

import com.creditsuisse.demo.model.EventDataDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDataRepository extends JpaRepository<EventDataDb, String> {
    EventDataDb findAllById(String id);
}
