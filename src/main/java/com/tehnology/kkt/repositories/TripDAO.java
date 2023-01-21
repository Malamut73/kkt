package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripDAO extends JpaRepository<Trip, Long> {
}
